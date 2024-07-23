package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;

public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta{

    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        boolean isPacienteAtivo = repository.findByAtivoById(dados.idPaciente());
        if (!isPacienteAtivo) {
            throw new ValidacaoException("Consulta nao agendada, paciente nao ativo!");
        }
    }
}
