package br.edu.fatecpg.Tecprog2.repository;

import br.edu.fatecpg.Tecprog2.model.Aluno;
import br.edu.fatecpg.Tecprog2.model.ReceitasPromocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReceitasPromocaoRepository
    extends JpaRepository<ReceitasPromocao,Long> {
    @Query("SELECT r FROM ReceitasPromocao r WHERE r.emPromocao = :emPromocao")
    List<ReceitasPromocao> BuscarPromocao(@Param("emPromocao") boolean promo);
}
