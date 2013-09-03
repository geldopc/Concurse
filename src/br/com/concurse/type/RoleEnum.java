package br.com.concurse.type;

public enum RoleEnum {
	
	A("administrador"), U("usuario");

	private String label;

	RoleEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}
}
