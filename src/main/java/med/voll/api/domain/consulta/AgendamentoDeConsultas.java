package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendarConsulta(DadosAgendamentoConsulta dadosAgendamento){
        Paciente paciente = pacienteRepository.getReferenceById(dadosAgendamento.idPaciente());
        Medico medico = medicoRepository.getReferenceById(dadosAgendamento.idMedico());

        Consulta consulta = new Consulta(null, medico, paciente, dadosAgendamento.data());
    }
}
