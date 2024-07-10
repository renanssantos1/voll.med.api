package med.voll.api.domain.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroPaciente(
        @NotBlank
        String nome,

        @Email
        @NotBlank
        String email,

        @NotBlank
        @Pattern(regexp = "^\\d{11}$")
        String cpf,

        @NotBlank
        String telefone,

        @NotBlank
        DadosEndereco endereco
) {
}
