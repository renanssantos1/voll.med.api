package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta {

    public void validar(DadosAgendamentoConsulta dados) {
        var dadaConsulta = dados.data();
        var now = LocalDateTime.now();
        var differenceMinutes = Duration.between(now, dadaConsulta).toMinutes();

        if (differenceMinutes < 30) {
            throw new ValidacaoException("Consulta deve ser agendenda com minimo de 30 minutos.");
        }
    }
}
