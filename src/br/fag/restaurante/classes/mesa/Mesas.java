package br.fag.restaurante.classes.mesa;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Classe para gerenciar as mesas do restaurente (Singleton)
 */
public class Mesas {
	private static Mesas _this = null;
	private JPanel pnMesas = null;
	private ArrayList<Mesa> listaMesas = null;
	
	/**
	 * Constructor - SingleTon (private)
	 */
	private Mesas() {
		
	}
	
	/**
	 * GetInstancia - Singleton e synchronized - obtem a instancia unica da classe
	 * @return {Mesas} - a instancia unica da classe 
	 */
	public static synchronized Mesas getInstancia() {
		if(_this == null) {
			_this = new Mesas();
		}
		return _this;
	}
	
	/**
	 * Cria as mesas (lista e visual) conforme parametros
	 * @param {JPanel} pnMesas - o painel que contem as mesas
	 * @param {int} qtMesas - a quantidade de mesas
	 */
	public void criarMesas(JPanel pnMesas,int qtMesas) {
		try {
			this.setPnMesas(pnMesas);
			this.excluirMesas(pnMesas);
			this.listaMesas = new ArrayList<Mesa>();
			int sqrt = (int) Math.sqrt(qtMesas);			
			JPanel pnMesa = null;
			JLabel label = null;
			GridLayout lt = new GridLayout(sqrt,sqrt + 1);
			Mesa mesa = null;
			lt.setHgap(10);
			lt.setVgap(10);
			pnMesas.setLayout(lt);
			for (int i = 0; i < qtMesas; i++) {				
				mesa = new Mesa(pnMesas,i+1);				
				listaMesas.add(mesa);
			}
			pnMesas.revalidate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Obtem a proxima mesa disponivel
	 * @return {Mesa} - a mesa disponivel ou nulo se nao disponivel
	 */
	public Mesa obterProxMesaDisponivel() {
		try {
			Mesa retorno = null;
			for(int i = 0; i < this.listaMesas.size(); i++) {
				if (this.listaMesas.get(i).getStatus().equalsIgnoreCase("disponivel")) {
					retorno = this.listaMesas.get(i);
					break;
				}
			}			
			return retorno;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * libera todas as mesas para disponiveis
	 * @param {JPanel} pnMesas - o painel container de mesas
	 */
	public void liberarMesas(JPanel pnMesas) {
		try {			
			for (int i = 0; i < this.listaMesas.size(); i++) {
				this.listaMesas.get(i).liberarMesa();
			}
			pnMesas.revalidate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * exclui todas as mesas (lista e visual)
	 * @param {JPanel} pnMesas - o container de mesas
	 */
	public void excluirMesas(JPanel pnMesas) {
		try {
			if (this.listaMesas != null) {
				int qt = this.listaMesas.size()-1;
				Mesa mesa = null;
				for (int i = qt; i >= 0; i--) {
					mesa = this.listaMesas.get(i);
					mesa.remover();
					this.listaMesas.remove(i);
				}
				this.listaMesas.clear();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*GETTERS AND SETTERS*/
	public JPanel getPnMesas() {
		return pnMesas;
	}
	public void setPnMesas(JPanel pnMesas) {
		this.pnMesas = pnMesas;
	}
}
