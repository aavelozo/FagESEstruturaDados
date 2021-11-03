package br.fag.restaurante.variaveis;

import java.util.ArrayList;

/**
 * Classe para armazenar e disponibilizar variaveis para o programa (Singleton)
 */
public class Variaveis {
	private static Variaveis _this = null;
	private Integer statusAtendimento = 0;
	private Integer statusDemo = 0;
	private ArrayList<String> listaNomesClientes = null;
	private ArrayList<String> listaNomesBrindes = null;
	
	/**
	 * Constructor - SingleTon (private)
	 */
	private Variaveis() {		
		this.preencherListaNomesClientes();
		this.preencherListaNomesBrindes();
	}
	
	/**
	 * GetInstancia - Singleton e synchronized - obtem a instancia unica da classe
	 * @return {Variaveis} - a instancia unica da classe 
	 */
	public static synchronized Variaveis getInstancia() {
		if (_this == null) {
			_this = new Variaveis();
		}
		return _this;
	}
		
	private void preencherListaNomesBrindes() {
		this.listaNomesBrindes = new ArrayList<String>();
		this.listaNomesBrindes.add("Belga");
		this.listaNomesBrindes.add("Meio Amargo");
		this.listaNomesBrindes.add("Muito Amargo");
		this.listaNomesBrindes.add("Meia Vida");
		this.listaNomesBrindes.add("Branco");
		this.listaNomesBrindes.add("Preto");
		this.listaNomesBrindes.add("Ao Leite preto");
		this.listaNomesBrindes.add("Brigadeiro");
		this.listaNomesBrindes.add("Giló");
		this.listaNomesBrindes.add("Tabuada");
		this.listaNomesBrindes.add("Carroça");
		this.listaNomesBrindes.add("Paçoca");
		this.listaNomesBrindes.add("Dente de Leite");
		this.listaNomesBrindes.add("Teta de Nega");
		this.listaNomesBrindes.add("Maria Mole");
		this.listaNomesBrindes.add("Pediu Moleque");
		this.listaNomesBrindes.add("Dente podre");
		this.listaNomesBrindes.add("Rapadura");
		this.listaNomesBrindes.add("Cana Verde");
		this.listaNomesBrindes.add("51");
		this.listaNomesBrindes.add("RED");
		this.listaNomesBrindes.add("Pinga");
		this.listaNomesBrindes.add("Morango Azul");
		this.listaNomesBrindes.add("Vômito adocicado");
		this.listaNomesBrindes.add("Restôdontê");		
	}
	
	private void preencherListaNomesClientes() {
		this.listaNomesClientes = new ArrayList<String>();
		this.listaNomesClientes.add("Alan");
		this.listaNomesClientes.add("Alencar");
		this.listaNomesClientes.add("Bruno H");
		this.listaNomesClientes.add("Bruno R");
		this.listaNomesClientes.add("Carlos");
		this.listaNomesClientes.add("Cassius");
		this.listaNomesClientes.add("Joao");
		this.listaNomesClientes.add("Jose");
		this.listaNomesClientes.add("Judas");
		this.listaNomesClientes.add("Jesus");
		this.listaNomesClientes.add("Miranda");
		this.listaNomesClientes.add("Delphi");
		this.listaNomesClientes.add("Elphos");
		this.listaNomesClientes.add("Socrates");
		this.listaNomesClientes.add("Platao");
		this.listaNomesClientes.add("Aristoteles");
		this.listaNomesClientes.add("Kant");
		this.listaNomesClientes.add("Eliane");
		this.listaNomesClientes.add("Andre");
		this.listaNomesClientes.add("Richardison");
		this.listaNomesClientes.add("Lucas");
		this.listaNomesClientes.add("Geronimo");
		this.listaNomesClientes.add("Elisa");
		this.listaNomesClientes.add("Maria");
		this.listaNomesClientes.add("Genesia");
		this.listaNomesClientes.add("Clorophila");
		this.listaNomesClientes.add("Andromeda");
		this.listaNomesClientes.add("Juliana");
		this.listaNomesClientes.add("Marta");
		this.listaNomesClientes.add("Lula");
		this.listaNomesClientes.add("Bolsonaro");
		this.listaNomesClientes.add("Freud");		
		this.listaNomesClientes.add("Java");
		this.listaNomesClientes.add("Javascript");
		this.listaNomesClientes.add("Assembler");
		this.listaNomesClientes.add("Ubuntu");
		this.listaNomesClientes.add("BROffice");
		this.listaNomesClientes.add("Bill");
		this.listaNomesClientes.add("Melinda");
		this.listaNomesClientes.add("Josefina BF");
	}

	
	
	/*GETTERS AND SETTERS*/
	public Integer getStatusAtendimento() {
		return statusAtendimento;
	}
	public void setStatusAtendimento(Integer statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
	}
	public Integer getStatusDemo() {
		return statusDemo;
	}
	public void setStatusDemo(Integer statusDemo) {
		this.statusDemo = statusDemo;
	}
	public ArrayList<String> getListaNomesClientes() {
		return listaNomesClientes;
	}
	public void setListaNomesClientes(ArrayList<String> listaNomesClientes) {
		this.listaNomesClientes = listaNomesClientes;
	}

	public ArrayList<String> getListaNomesBrindes() {
		return listaNomesBrindes;
	}

	public void setListaNomesBrindes(ArrayList<String> listaNomesBrindes) {
		this.listaNomesBrindes = listaNomesBrindes;
	}	
	
}
