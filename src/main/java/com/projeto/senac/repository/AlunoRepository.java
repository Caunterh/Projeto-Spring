package com.projeto.senac.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.projeto.senac.model.Aluno;
import java.util.List;

public interface AlunoRepository extends CrudRepository<Aluno, Long>{
	
	@Query("SELECT a FROM Aluno a WHERE a.id= (SELECT MAX(a2.id) FROM  Aluno a2)")
	public Aluno findLastInsertedAluno();

	@Query("SELECT a FROM Aluno  a WHERE a.cpf=:cpf")
	public Aluno findByCpf(String cpf);
	
	@Query("SELECT a FROM Aluno a ORDER BY a.id")
	public List<Aluno> findAllOrderedById();
}
