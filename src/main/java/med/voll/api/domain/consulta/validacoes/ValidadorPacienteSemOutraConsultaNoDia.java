package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;

import java.time.LocalDateTime;

public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoConsulta{

    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime primeiroHorario = dados.data().withHour(7);
        LocalDateTime ultimoHorario = dados.data().withHour(18);
        boolean isPacientePossuiConsulta = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(),primeiroHorario, ultimoHorario);
        if (isPacientePossuiConsulta) {
            throw new ValidacaoException("Paciente ja possui consulta agendada para esse dia.");
        }
    }

}
