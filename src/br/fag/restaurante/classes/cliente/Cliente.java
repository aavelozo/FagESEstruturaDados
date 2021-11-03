package br.fag.restaurante.classes.cliente;

import br.fag.restaurante.classes.brinde.Brinde;
import br.fag.restaurante.classes.cardapio.Cardapio;
import br.fag.restaurante.classes.mesa.Mesa;
import br.fag.restaurante.funcoes.FuncoesMatematica;
import br.fag.restaurante.variaveis.Variaveis;


/**
 * Classe para gerenciar o objeto Cliente, com suas acoes e valores
 */
public class Cliente {
	private Integer cod;
	private String nome;
	private Mesa mesa;
	private Brinde brinde;
	private Cardapio comanda;
	private Long timeEntradaMesa = null;
	
	/**
	 * Constructor
	 * @param {Integer} pCod - o codigo do cliente
	 */
	public Cliente(Integer pCod) {
		this.cod = pCod;
		this.nome = Variaveis.getInstancia().getListaNomesClientes().get(FuncoesMatematica.getInstancia().rand(0,Variaveis.getInstancia().getListaNomesClientes().size()-1));
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
	public Mesa getMesa() {
		return mesa;
	}
	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	public Brinde getBrinde() {
		return brinde;
	}
	public void setBrinde(Brinde brinde) {
		this.brinde = brinde;
	}
	public Cardapio getComanda() {
		if (this.comanda == null) {
			this.comanda = new Cardapio();
		}
		return comanda;
	}
	public void setComanda(Cardapio comanda) {
		this.comanda = comanda;
	}
	public Long getTimeEntradaMesa() {
		return timeEntradaMesa;
	}
	public void setTimeEntradaMesa(Long timeEntradaMesa) {
		this.timeEntradaMesa = timeEntradaMesa;
	}		
}
