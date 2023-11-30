package com.projeto.senac.service;

import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projeto.senac.model.Aluno;
import com.projeto.senac.repository.AlunoRepository;

@Service
public class ServiceAluno {
	@Autowired
	private AlunoRepository alunoRepository;

	public String geraMatricula(int id) {
		Date data = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int year = cal.get(Calendar.YEAR);
		String txt = "SENAC";
		return (txt + year + (id + 1));
	}

	public String cadastrarAluno(Aluno aluno) {
// Verificar se já existe um aluno com o mesmo CPF
		Aluno alunoExistente = alunoRepository.findByCpf(aluno.getCpf());
		if (alunoExistente == null) {
			Aluno aux = alunoRepository.findLastInsertedAluno();
			if (aux == null) {// primeiro aluno na base
				aluno.setMatricula(this.geraMatricula(0));
			} else
				aluno.setMatricula(this.geraMatricula(Integer.parseInt(aux.getId().toString())));
// Nenhum aluno com o mesmo CPF foi encontrado, então podemos inserir o novo aluno
			alunoRepository.save(aluno);
		} else {
// Um aluno com o mesmo CPF já existe, então você pode avisar.
			return ("Já existe um aluno com o mesmo CPF.");
		}
		return null;
	}

	public String alterarAluno(Aluno aluno, Long id) {
		// verifica se existe um aluno com o mesmo cpf
		Aluno alunoExistente = alunoRepository.findByCpf(aluno.getCpf());
		if ((alunoExistente != null && alunoExistente.getId() == id) || alunoExistente == null) {
			alunoRepository.save(aluno);
		} else {// significa que o cpf foi alterado e já é de um outro aluno no BD
			return "Já existe um aluno cadastrado com o mesmo Cpf!";
		}
		return null;
	}// fim cadastrar ALuno

}
