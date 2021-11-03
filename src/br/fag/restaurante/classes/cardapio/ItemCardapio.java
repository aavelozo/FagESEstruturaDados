package br.fag.restaurante.classes.cardapio;


/**
 * Classe de Item de Cardapio, contendo seus valores
 */
public class ItemCardapio {
	private Integer cod;
	private String nome;
	private double valor;
	private double qt;
	private double valorTotal;
	
	/**
	 * Construtor com parametros
	 * @param pCod
	 * @param pNome
	 * @param pValor
	 */
	public ItemCardapio(Integer pCod,String pNome, double pValor) {
		this.cod = pCod;
		this.nome = pNome;
		this.valor = pValor;
	}
	
	/**
	 * Atualiza o valor total do item com base na quantidade e valor do cardapio
	 */
	public void atualizarValorTotal() {
		this.valorTotal = this.qt * this.valor;
	}
	
	
	/*GETTERS AND SETTERS*/	
	public Integer getCod() {
		return cod;
	}
	public void setCod(Integer cod) {
		this.cod = cod;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getQt() {
		return qt;
	}
	public void setQt(double qt) {
		this.qt = qt;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}	
}
