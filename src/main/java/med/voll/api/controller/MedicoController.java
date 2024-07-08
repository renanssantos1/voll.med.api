package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid DadosCadastroMedico dadosMedico) {
        repository.save(new Medico(dadosMedico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> getMedicos(Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedico(@RequestBody @Valid DadosAtulizacaoMedico dadosMedico) {
        var medico = repository.getReferenceById(dadosMedico.id());
        medico.atualizarInformacoes(dadosMedico);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluirMedico();

        return ResponseEntity.noContent().build();
    }
}
