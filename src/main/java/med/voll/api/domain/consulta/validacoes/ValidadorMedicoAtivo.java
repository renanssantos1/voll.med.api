package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        System.out.println("ID MEDICO" + dados.idMedico());
        if (Objects.isNull(dados.idMedico())) {
            return;
        }
        var medicoAtivo = repository.findByAtivoById(dados.idMedico());
        if (medicoAtivo == null) {
            throw new ValidacaoException("Consulta nao pode ser agendada com medico inexistente");
        }
    }

}
