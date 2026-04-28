package br.edu.fatecpg.Tecprog2.repository;

import br.edu.fatecpg.Tecprog2.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlunoRepository
    extends JpaRepository<Aluno,Long>{
    @Query("SELECT a FROM Aluno a WHERE a.nome = :nome")
    List<Aluno> buscarPorNome(@Param("nome") String nome);

}
