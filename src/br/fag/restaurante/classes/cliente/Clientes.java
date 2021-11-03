package br.fag.restaurante.classes.cliente;

import static javax.swing.JOptionPane.showMessageDialog;

import java.util.ArrayList;

import br.fag.restaurante.classes.configuracoes.Configuracoes;
import br.fag.restaurante.classes.mesa.Mesa;
import br.fag.restaurante.classes.mesa.Mesas;


/**
 * Classe para gerenciar a lista de clientes dentro do restaurante
 */
public class Clientes {
	private static Clientes _this = null;
	private ArrayList<Cliente> listaClientes = null;
	private int codProxCliente = 1;
	private Mesas mesas = null;
	private Configuracoes configs;
	
	/**
	 * Constructor - SingleTon (private)
	 */
	private Clientes() {

	}
	
	/**
	 * GetInstancia - Singleton e synchronized - obtem a instancia unica da classe
	 * @return {Clientes} - a instancia unica da classe 
	 */
	public static synchronized Clientes getInstancia() {
		if (_this == null) {
			_this = new Clientes();
		}
		return _this;
	}

	/**
	 * instancia um novo cliente, alocando-o numa mesa, se disponvivel. Se nao houver mesas, nao sera instanciado
	 */
	public void novoCliente() {
		try {
			Mesa mesa = this.mesas.getInstancia().obterProxMesaDisponivel();
			if (mesa != null) {
				Cliente novoCliente = new Cliente(codProxCliente);
				codProxCliente++;
				novoCliente.setMesa(mesa);
				if (this.listaClientes == null) {
					this.listaClientes = new ArrayList<Cliente>();
				}
				mesa.setCliente(novoCliente);
				this.listaClientes.add(novoCliente);
			} else {
				showMessageDialog(null, "Não há mesa disponível!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/*GETTERS AND SETTERS*/	
	public int getCodProxCliente() {
		return codProxCliente;
	}
	public void setCodProxCliente(int codProxCliente) {
		this.codProxCliente = codProxCliente;
	}

	public ArrayList<Cliente> getListaClientes() {
		if (this.listaClientes == null) {
			this.listaClientes = new ArrayList<Cliente>();
		}
		return this.listaClientes;
	}

	public void setListaClientes(ArrayList<Cliente> listaClientes) {		
		this.listaClientes = listaClientes;
	}
	

}
