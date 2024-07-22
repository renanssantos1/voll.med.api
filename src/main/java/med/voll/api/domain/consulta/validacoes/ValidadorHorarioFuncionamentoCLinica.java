package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ValidadorHorarioFuncionamentoCLinica {

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime dadosConsulta = dados.data();
        Boolean isDomingo = dadosConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        Boolean isAntesAberturaClinica = dadosConsulta.getHour() < 7;
        Boolean isClinicaFechada = dadosConsulta.getHour() > 18;

        if (isDomingo || isAntesAberturaClinica || isClinicaFechada) {
            throw new ValidacaoException("Consulta fora de horario de funcionamento");
        }
    }

}
