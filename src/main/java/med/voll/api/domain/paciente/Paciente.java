package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.endereco.Endereco;

@Table(name="pacientes")
@Entity(name = "Paciente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of="id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    private Boolean ativo;

    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dadosPaciente){
        this.nome = dadosPaciente.nome();
        this.email = dadosPaciente.email();
        this.cpf = dadosPaciente.cpf();
        this.telefone = dadosPaciente.telefone();
        this.endereco = new Endereco(dadosPaciente.endereco());
        this.ativo = true;
    }

    public void deletarPaciente() {
        this.ativo = false;
    }

    public void atualizarDadosCadastrais(DadosAtualizacaoPaciente dadosPaciente) {
        if (dadosPaciente.nome() != null){
            this.nome = dadosPaciente.nome();
        }

        if (dadosPaciente.telefone() != null){
            this.telefone = dadosPaciente.telefone();
        }

        if(dadosPaciente.endereco() != null){
            this.endereco.atualizarInformacoes(dadosPaciente.endereco());
        }
    }
}
