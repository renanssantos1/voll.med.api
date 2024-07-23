package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendamentoDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendamentoDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dadosAgendamento) {
        var dto = agenda.agendarConsulta(dadosAgendamento);
        return ResponseEntity.ok(dto);
    }

}
