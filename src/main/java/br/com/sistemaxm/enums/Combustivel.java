package br.com.sistemaxm.enums;

public enum Combustivel {

	GASOLINA("G","Gasolina"), ALCOOL("A","Alcool"), DIESEL("D","Diesel"), FLEX("F","Flex");
	
	private final String key;
	private final String value;
	
	private Combustivel(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	public static Combustivel getEn(String value) {
		if(value.equals("Gasolina")) {
			return GASOLINA;
		}
		if(value.equals("Alcool")) {
			return ALCOOL;
		}
		if(value.equals("Diesel")) {
			return DIESEL;
		}
		if(value.equals("Flex")) {
			return FLEX;
		}
		return null;
	}
	@Override
	public String toString() {
		return value;
	}
}
