package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid DadosCadastroMedico dadosMedico){
        repository.save(new Medico(dadosMedico));
    }

    @GetMapping
    public Page<DadosListagemMedico> getMedicos(Pageable paginacao){
        return repository.findAll(paginacao)
                .map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizarMedico(@RequestBody @Valid DadosAtulizacaoMedico dadosMedico){
        var medico = repository.getReferenceById(dadosMedico.id());
        medico.atualizarInformacoes(dadosMedico);
    }
}
