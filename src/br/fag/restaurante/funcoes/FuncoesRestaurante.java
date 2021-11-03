package br.fag.restaurante.funcoes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.fag.restaurante.classes.TarefaAssincrona;
import br.fag.restaurante.classes.cliente.Cliente;
import br.fag.restaurante.classes.cliente.Clientes;
import br.fag.restaurante.classes.configuracoes.Configuracoes;
import br.fag.restaurante.classes.mesa.Mesa;
import br.fag.restaurante.classes.mesa.Mesas;
import br.fag.restaurante.variaveis.Variaveis;

import static javax.swing.JOptionPane.showMessageDialog;


/**
 * Classe para gerir funcoes genericas do projeto Sistema Restaurante (SingleTon)
 */
public class FuncoesRestaurante {
	private static FuncoesRestaurante _this = null;
	private Mesas mesas = null;
	private Clientes clientes = null;
	private TarefaAssincrona threadEntradaClientes = null;
	private Configuracoes configs = null;
	
	/**
	 * Constructor - SingleTon (private)
	 */
	private FuncoesRestaurante() {
		this.mesas = Mesas.getInstancia();
	}
	
	/**
	 * GetInstancia - Singleton e synchronized - obtem a instancia unica da classe
	 * @return {FuncoesRestaurante} - a instancia unica da classe 
	 */
	public static synchronized FuncoesRestaurante getInstancia() {
		if (_this == null) {
			_this = new FuncoesRestaurante();			
		}
		return _this;
	}
	
	/**
	 * Inicia ou Re-incia o atendimento do restaurante
	 * @param {JPanel} pnMesas - o painel container de mesas
	 */
	public void iniciarAtendimento(JPanel pnMesas) {
		try {
			this.mesas.getInstancia().setPnMesas(pnMesas);
			configs = configs.getInstancia();
			this.mesas.getInstancia().criarMesas(pnMesas,Integer.valueOf(configs.getConfig("qtMesas")));
			this.mesas.getInstancia().liberarMesas(pnMesas);
			this.entradaClientesAutomatica(pnMesas);
			this.clientes.getInstancia().setCodProxCliente(1);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * processa a entrada automatica de clientes conforme configuracoes, chamada em thread separada para nao travar a UI
	 */
	public void processarEntradaClientesAutomatica() {
		try {			
			while(true) {
				if (Variaveis.getInstancia().getStatusDemo() == 1 && Variaveis.getInstancia().getStatusAtendimento() == 1 && Boolean.valueOf(configs.getInstancia().getConfig("entradaAutomaticaClientes"))) {
					if (this.mesas.getInstancia().obterProxMesaDisponivel() != null) {
						clientes.getInstancia().novoCliente();
					}
				}
				Thread.sleep(FuncoesMatematica.getInstancia().rand(
					Integer.valueOf(configs.getInstancia().getConfig("freqEntradaAutomaticaCliente")),
					Integer.valueOf(configs.getInstancia().getConfig("freqEntradaAutomaticaCliente")) * 10
				));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * cria e inicia a Thread para receber clientes automaticamente
	 * @param {JPanel} pnMesas - o painel container de mesas
	 */
	public void entradaClientesAutomatica(JPanel pnMesas) {
		try {			
			this.threadEntradaClientes = new TarefaAssincrona();
			this.threadEntradaClientes.setObjetoExecutar(this);
			this.threadEntradaClientes.setMetodoExecutar(this.getClass().getMethod("processarEntradaClientesAutomatica"));
			this.threadEntradaClientes.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
