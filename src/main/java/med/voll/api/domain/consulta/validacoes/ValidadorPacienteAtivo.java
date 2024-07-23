package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta{

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        boolean isPacienteAtivo = repository.findByAtivoById(dados.idPaciente());
        if (!isPacienteAtivo) {
            throw new ValidacaoException("Consulta nao agendada, paciente nao ativo!");
        }
    }
}
