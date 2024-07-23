package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoException;

import java.util.Objects;

public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta{

    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        if(Objects.isNull(dados.idMedico())){
            return;
        }
        boolean medicoAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoAtivo){
            throw new ValidacaoException("Consulta nao pode ser agendada com medico inexistente");
        }
    }

}
