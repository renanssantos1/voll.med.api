package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioFuncionamentoCLinica implements ValidadorAgendamentoConsulta{

    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime dadosConsulta = dados.data();
        boolean isDomingo = dadosConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean isAntesAberturaClinica = dadosConsulta.getHour() < 7;
        boolean isClinicaFechada = dadosConsulta.getHour() > 18;

        if (isDomingo || isAntesAberturaClinica || isClinicaFechada) {
            throw new ValidacaoException("Consulta fora de horario de funcionamento");
        }
    }

}
