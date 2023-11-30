package com.projeto.senac.Enum;

public enum Status {
	ATIVO("Ativo"),
	TRANCADO("Trancado"),
	INATIVO("Inativo"),
	CANCELADO("Cancelado");
	
	private String status;
	private Status(String status) {
		this.status=status;
	}
}
