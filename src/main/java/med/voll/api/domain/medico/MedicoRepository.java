package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query(value = """
        SELECT * FROM Medico m 
        WHERE m.ativo = 1 
        AND m.especialidade = :especialidade 
        AND m.id NOT IN (
            SELECT c.medico_id 
            FROM Consulta c 
            WHERE c.data = :data
        ) 
        ORDER BY RAND() 
        LIMIT 1
        """, nativeQuery = true)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query ("""
        select m.ativo
        from Medico m
        where
        m.id = :id
    """)
    Boolean findAtivoById(Long id);
}
