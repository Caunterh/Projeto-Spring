package com.projeto.senac.Enum;

public enum Curso {
	MARKETING("Marketing"), ADMINISTRACAO("Administracao"), ADS("Ads"), CONTABILIDADE("Contabilidade"),
	ENFERMAGEM("Enfermagem");

	private String curso;

	private Curso(String s) {
		curso = s;
	}

}
