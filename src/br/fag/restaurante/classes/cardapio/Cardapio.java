package br.fag.restaurante.classes.cardapio;

import java.util.ArrayList;

import javax.swing.JLabel;


/**
 * Classe para gerenciar o cardapio, que tambem serve como comanda (clone do cardapio) de clientes
 */
public class Cardapio {
	private ArrayList<ItemCardapio> listaCardapio = null;
	private int qtItens = 0;
	private double qtTotal = 0;
	private double valorTotal = 0;
	private JLabel lbValorTotal = null;
	
	/**
	 * Constructor
	 */
	public Cardapio () {
		this.listaCardapio = new ArrayList<ItemCardapio>();
		this.gerarCardapio();
	}
	
	/**
	 * Gera o cardapio padrao com os itens listados no corpo deste methodo
	 */
	public void gerarCardapio() {
		this.listaCardapio.add(new ItemCardapio(0,"arroz",20));
		this.listaCardapio.add(new ItemCardapio(1,"feijão",10));
		this.listaCardapio.add(new ItemCardapio(2,"macarrão",15));
		this.listaCardapio.add(new ItemCardapio(3,"salada",5));
		this.listaCardapio.add(new ItemCardapio(4,"frango",25));
		this.listaCardapio.add(new ItemCardapio(5,"boi",50));
		this.listaCardapio.add(new ItemCardapio(6,"porco",30));
		this.listaCardapio.add(new ItemCardapio(7,"ovo",3));
		this.listaCardapio.add(new ItemCardapio(8,"coca-cola",10));
		this.listaCardapio.add(new ItemCardapio(9,"fanta",10));
		this.listaCardapio.add(new ItemCardapio(10,"guarana",10));
		this.listaCardapio.add(new ItemCardapio(11,"agua",5));
		this.listaCardapio.add(new ItemCardapio(12,"cha",5));
		this.listaCardapio.add(new ItemCardapio(13,"café",5));
	}
	
	/**
	 * Atualiza os valores (totalizadores) da comanda conforme quantidades preenchidas
	 */
	public void atualizarValores() {
		this.valorTotal = 0;
		this.qtTotal = 0;
		this.qtItens = 0;
		for(int i = 0; i < this.listaCardapio.size(); i++) {
			this.valorTotal += this.listaCardapio.get(i).getValorTotal();
			this.qtTotal += this.listaCardapio.get(i).getQt();
			if (this.listaCardapio.get(i).getQt() > 0) {
				this.qtItens++;
			}
		}
		this.atualizarLbValorTotal();
	}
	
	/**
	 * Atualiza o label, se setado, que mostra o valor total da comanda
	 */
	public void atualizarLbValorTotal() {
		if (this.lbValorTotal != null) {
			this.lbValorTotal.setText(String.format("%.2f", this.valorTotal));
			this.lbValorTotal.revalidate();
			this.lbValorTotal.getParent().revalidate();
		}
	}	
	
	
	
	
	/*GETTERS AND SETTERS*/	
	public ArrayList<ItemCardapio> getListaCardapio() {
		return listaCardapio;
	}
	public void setListaCardapio(ArrayList<ItemCardapio> listaCardapio) {
		this.listaCardapio = listaCardapio;
	}	
	public ItemCardapio getItemCardapio(int pCod) {
		for(int i = 0; i < this.listaCardapio.size(); i++) {
			if (this.listaCardapio.get(i).getCod() == pCod) {
				return this.listaCardapio.get(i);
			}
		}
		return null;
	}
	public int getQtItens() {
		return qtItens;
	}
	public void setQtItens(int qtItens) {
		this.qtItens = qtItens;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
		this.atualizarLbValorTotal();
	}	
	public double getQtTotal() {
		return qtTotal;
	}
	public void setQtTotal(double qtTotal) {
		this.qtTotal = qtTotal;
	}
	public JLabel getLbValorTotal() {
		return lbValorTotal;
	}
	public void setLbValorTotal(JLabel lbValorTotal) {
		this.lbValorTotal = lbValorTotal;
	}
}
