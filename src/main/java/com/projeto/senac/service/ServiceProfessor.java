package com.projeto.senac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.senac.model.Professor;
import com.projeto.senac.repository.ProfessorRepository;

@Service
public class ServiceProfessor {
	@Autowired
	ProfessorRepository professorRepository;

	public String cadastrarProfessor(Professor professor) {
		// verifica se existe um professor com o mesmo cpf
		Professor profExiste = professorRepository.findByCpf(professor.getCpf());
		if (profExiste == null) {
			professorRepository.save(professor);
		} // if professorExistente == null
		else {// existe um professor já com o mesmo cpf
			return "Já existe um professor cadastrado com o mesmo Cpf!";
		}
		return null;
	}// fim cadastrar professor
	
	public String alterarProfessor(Professor professor, long id) {
		
		Professor professorExistente = professorRepository.findByCpf(professor.getCpf());
		if((professorExistente != null && professorExistente.getId() == id) || professorExistente == null) {
			
			professorRepository.save(professor);
		}
		else {
			return "Já existe um professor cadastrado com esse CPF";
		}
		
		return null;
		
	}

}