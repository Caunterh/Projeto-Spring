package com.projeto.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.projeto.senac.model.Aluno;
import com.projeto.senac.repository.AlunoRepository;
import com.projeto.senac.service.ServiceAluno;

@Controller
public class AlunoController {
	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	ServiceAluno servicealuno;

	@GetMapping("inserirAluno")
	public ModelAndView insertAluno() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("aluno", new Aluno()); // Aluno aluno = new Aluno();
		mv.setViewName("Aluno/inserirAluno");
		return mv;
	}

	@PostMapping("inserirAluno")
	public ModelAndView inserir(Aluno aluno) {
		ModelAndView mv = new ModelAndView();
		String out = servicealuno.cadastrarAluno(aluno);
		if (out != null) {

			mv.addObject("aluno", new Aluno());
			mv.addObject("msg", out);
			mv.setViewName("Aluno/inserirAluno");
		} else {

			mv.setViewName("redirect:/listarAlunos");

		}

		mv.setViewName("redirect:/listarAlunos");
		return mv;
	}

	@GetMapping("/listarAlunos")
	public ModelAndView listarAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Aluno/listarAlunos");
		mv.addObject("alunos", alunoRepository.findAllOrderedById());
		return mv;
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterarAluno(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Aluno aluno = alunoRepository.findById(id).get();
		mv.addObject("aluno", aluno);
		mv.setViewName("Aluno/alterar");
		return mv;
	}

	@PostMapping("/alterar")
	public ModelAndView alterarAluno(Aluno aluno) {
		ModelAndView mv = new ModelAndView();
		String out = servicealuno.alterarAluno(aluno, aluno.getId());
		if (out != null) {
			mv.addObject("msg", out);
			mv.addObject("aluno", aluno);
			mv.setViewName("Aluno/alterar");
		} else {
			mv.setViewName("redirect:/listarAlunos");
		}
		return mv;
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id) {
		alunoRepository.deleteById(id);
		return "redirect:/listarExcluirAlunos";
	}

	@GetMapping("/listarAlterarAlunos")
	public ModelAndView listarAlterar() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("alunos", alunoRepository.findAllOrderedById());
		mv.setViewName("Aluno/listarAlterar");
		return mv;
	}

	@GetMapping("/listarExcluirAlunos")
	public ModelAndView listarExcluir() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("alunos", alunoRepository.findAllOrderedById());
		mv.setViewName("Aluno/listarExcluir");
		return mv;
	}
}
