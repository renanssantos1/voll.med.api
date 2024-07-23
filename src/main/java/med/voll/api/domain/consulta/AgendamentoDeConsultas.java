package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AgendamentoDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoConsulta> validators;

    public void agendarConsulta(DadosAgendamentoConsulta dadosAgendamento) {
        if (!pacienteRepository.existsById(dadosAgendamento.idPaciente())) {
            throw new ValidacaoException("Id do paciente: " + dadosAgendamento.idPaciente() + " nao existe;");
        }

        if (Objects.isNull(dadosAgendamento.idMedico()) && !medicoRepository.existsById(dadosAgendamento.idMedico())) {
            throw new ValidacaoException("Id do medico: " + dadosAgendamento.idPaciente() + " nao existe;");
        }

        validators.forEach(v -> v.validar(dadosAgendamento));

        Paciente paciente = pacienteRepository.getReferenceById(dadosAgendamento.idPaciente());
        Medico medico = escolherMedico(dadosAgendamento);

        Consulta consulta = new Consulta(null, medico, paciente, dadosAgendamento.data());
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (Objects.isNull(dados.especialidade())) {
            throw new ValidacaoException("Especialidade é obrigatoria");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

    }
}
