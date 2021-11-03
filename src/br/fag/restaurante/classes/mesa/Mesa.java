package br.fag.restaurante.classes.mesa;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.fag.restaurante.classes.TarefaAssincrona;
import br.fag.restaurante.classes.caixa.Caixa;
import br.fag.restaurante.classes.cardapio.Cardapio;
import br.fag.restaurante.classes.cardapio.ItemCardapio;
import br.fag.restaurante.classes.cliente.Cliente;
import br.fag.restaurante.classes.configuracoes.Configuracoes;
import br.fag.restaurante.funcoes.FuncoesMatematica;
import br.fag.restaurante.telas.TelaCardapio;
import br.fag.restaurante.variaveis.Variaveis;

/**
 * Classe para gerir o objeto Mesa, com suas acoes, configuracoes e componentes visuais
 */
public class Mesa {
	private Integer cod;
	private String nome;
	private JPanel pnMesa;
	private JLabel lbMesa;
	private JLabel lbCliente;
	private JLabel lbValorTotal;
	private JButton btnEncerrarMesa;
	private JButton btnComanda;
	private String status;
	private Color cor;
	private Cliente cliente;
	private Configuracoes configs;
	private TarefaAssincrona threadConsumoCliente;
	
	/**
	 * Construtor - gera um objeto mesa e seu componente visual
	 * @param {JPanel} pContainer - o container onde a mesa deve ser incluida 
	 * @param Integer} pCod - o codigo da mesa
	 */
	public Mesa(JPanel pContainer, Integer pCod) {
		this.cod = pCod;	
		this.nome = "M-" + this.cod;
		pnMesa = new JPanel();
		int tamanho = Integer.valueOf(configs.getInstancia().getConfig("tamanhoMesa"));
		pnMesa.setPreferredSize(new Dimension(tamanho,tamanho));	
		GridBagLayout gl = new GridBagLayout();
		pnMesa.setLayout(gl);
		
		/*LABEL MESA*/
		lbMesa = new JLabel(this.nome);
		GridBagConstraints ct = new GridBagConstraints();
		ct.gridx = 0;		
		ct.gridy = 0;
		ct.anchor = GridBagConstraints.CENTER;
		lbMesa.setVerticalAlignment(SwingConstants.CENTER);
		lbMesa.setHorizontalAlignment(SwingConstants.CENTER);
		pnMesa.add(lbMesa,ct);
		
		/*LABEL CLIENTE*/
		lbCliente = new JLabel(String.valueOf(""));
		ct = new GridBagConstraints();
		ct.gridx = 0;		
		ct.gridy = 1;
		ct.anchor = GridBagConstraints.CENTER;
		lbCliente.setVerticalAlignment(SwingConstants.CENTER);
		lbCliente.setHorizontalAlignment(SwingConstants.CENTER);
		pnMesa.add(lbCliente,ct);
		
		/*LABEL VLTOTAL*/
		lbValorTotal = new JLabel("");
		ct = new GridBagConstraints();
		ct.gridx = 0;		
		ct.gridy = 2;
		ct.anchor = GridBagConstraints.CENTER;
		lbValorTotal.setVerticalAlignment(SwingConstants.CENTER);
		lbValorTotal.setHorizontalAlignment(SwingConstants.CENTER);
		pnMesa.add(lbValorTotal,ct);
		
		/*BUTTON COMANDA*/
		btnComanda = new JButton("Comanda");
		btnComanda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarComandaMesa();
			}			
		});
		btnComanda.setVisible(false);
		ct = new GridBagConstraints();
		ct.gridx = 0;		
		ct.gridy = 3;
		ct.anchor = GridBagConstraints.CENTER;
		btnComanda.setVerticalAlignment(SwingConstants.CENTER);
		btnComanda.setHorizontalAlignment(SwingConstants.CENTER);
		pnMesa.add(btnComanda,ct);
		
		/*BUTTON ENCERRAR*/
		btnEncerrarMesa = new JButton("Encerrar");
		btnEncerrarMesa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				encerrarMesa();
			}			
		});
		btnEncerrarMesa.setVisible(false);
		ct = new GridBagConstraints();
		ct.gridx = 0;		
		ct.gridy = 4;
		ct.insets.top = 5;
		ct.anchor = GridBagConstraints.CENTER;
		btnEncerrarMesa.setVerticalAlignment(SwingConstants.CENTER);
		btnEncerrarMesa.setHorizontalAlignment(SwingConstants.CENTER);
		pnMesa.add(btnEncerrarMesa,ct);
		this.setStatus("fechada");
		pContainer.add(pnMesa);
	}
	
	/**
	 * libera a mesa para ser re-utilizada
	 */
	public void liberarMesa() {
		this.setStatus("disponivel");		
		this.pnMesa.revalidate();
	}
	
	/**
	 * mostra a janela de Comanda (cardapio) do cliente que esta na mesa, permitindo editar os itens consumidos
	 */
	public void mostrarComandaMesa() {
		if (this.cliente.getComanda() == null) {
			this.cliente.setComanda(new Cardapio());
		}
		this.cliente.getComanda().setLbValorTotal(lbValorTotal);
		TelaCardapio telaComanda = new TelaCardapio(this.cliente.getComanda(),this);
		telaComanda.setVisible(true);		
	}
	
	/**
	 * "Fecha a conta" e manda o cliente para a fila do caixa
	 */
	public void encerrarMesa() {
		Caixa caixa = Caixa.getInstancia();
		caixa.incluirClienteFila(this.cliente);
		this.setStatus("disponivel");
	}
	
	/**
	 * remove o componente visual mesa do seu container
	 */
	public void remover() {
		Container parent = this.pnMesa.getParent();
		this.pnMesa.setVisible(false);
		this.pnMesa.revalidate();
		if (parent != null) {
			parent.remove(this.pnMesa);
			parent.revalidate();
			parent.getParent().revalidate();
		}
	}
	
	
	/**
	 * processa (gera) um consumo aleatorio. async (thread) para nao travar a UI
	 * @param {Cliente} cliente - o cliente referenciado na mesa
	 */
	public void processarConsumoClienteAutomatico(Cliente cliente) {
		try {
			while(true) {
				Thread.sleep(FuncoesMatematica.getInstancia().rand(
					Integer.valueOf(configs.getInstancia().getConfig("freqConsumoAutomatico")),
					Integer.valueOf(configs.getInstancia().getConfig("freqConsumoAutomatico")) * 10
				));
				if (this.cliente != null) {
					if (Variaveis.getInstancia().getStatusDemo() == 1 && Variaveis.getInstancia().getStatusAtendimento() == 1 && Boolean.valueOf(configs.getInstancia().getConfig("consumoAutomatico"))) {
						Cardapio comanda = this.cliente.getComanda();
						int indiceItemRand = FuncoesMatematica.getInstancia().rand(0,comanda.getListaCardapio().size()-1);
						ItemCardapio item = comanda.getListaCardapio().get(indiceItemRand);
						item.setQt(item.getQt() + FuncoesMatematica.getInstancia().rand(1,10));
						item.atualizarValorTotal();
						comanda.atualizarValores();
					}
					if (Variaveis.getInstancia().getStatusDemo() == 1 && Boolean.valueOf(configs.getInstancia().getConfig("encerramentoAutomaticoClientes"))) {				
						if (this.status == "ocupada" && this.cliente != null && (this.cliente.getTimeEntradaMesa() + Long.valueOf(configs.getInstancia().getConfig("tempoPermanenciaAutomaticaCliente"))) < System.currentTimeMillis()) {
							this.encerrarMesa();
						}
					}	
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * inicia a thread async para processas o consumo automaticamente
	 */
	private void consumoAutomatico() {
		try {			
			this.threadConsumoCliente = new TarefaAssincrona();
			this.threadConsumoCliente.setObjetoExecutar(this);
			this.threadConsumoCliente.setMetodoExecutar(this.getClass().getMethod("processarConsumoClienteAutomatico",Cliente.class));
			this.threadConsumoCliente.setParamsExecutar(this.cliente);
			this.threadConsumoCliente.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
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
	public JPanel getPnMesa() {
		return pnMesa;
	}
	public void setPnMesa(JPanel pnMesa) {
		this.pnMesa = pnMesa;
	}
	public String getStatus() {
		return status;
	}
	
	/**
	 * altera o status da mesa e efetua acoes correspondente a cada status.
	 * @param {String} status - o status da mesa [fechada,ocupada,disponivel]
	 */
	public void setStatus(String status) {
		this.status = status;
		switch(this.status) {
			case "fechada":
				this.cor = Color.gray;			
				this.lbCliente.setForeground(Color.black);
				this.lbValorTotal.setForeground(Color.black);
				this.lbMesa.setForeground(Color.black);
				this.btnComanda.setVisible(false);
				this.btnEncerrarMesa.setVisible(false);
				this.lbCliente.setText("");
				break;
			case "ocupada":
				this.cor = Color.blue;
				this.lbCliente.setForeground(Color.white);
				this.lbValorTotal.setForeground(Color.white);
				this.lbMesa.setForeground(Color.white);
				this.btnComanda.setVisible(true);
				this.btnEncerrarMesa.setVisible(true);
				
				break;
			case "disponivel":
				this.cliente = null;
				this.cor = Color.green;
				this.lbCliente.setForeground(Color.black);
				this.lbValorTotal.setForeground(Color.black);
				this.lbMesa.setForeground(Color.black);
				this.btnComanda.setVisible(false);
				this.btnEncerrarMesa.setVisible(false);
				this.lbCliente.setText("");
				this.lbValorTotal.setText("");
				break;
		}
		this.pnMesa.setBackground(this.cor);
		this.pnMesa.revalidate();
	}
	public Color getCor() {
		return cor;
	}
	public void setCor(Color cor) {
		this.cor = cor;
	}
	public Cliente getCliente() {
		return cliente;
	}
	
	/**
	 * seta o cliente da mesa
	 * @param {Cliente} cliente - o cliente a ser setado na mesa
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		if (this.cliente != null) {
			this.cliente.setTimeEntradaMesa(System.currentTimeMillis());
			this.setStatus("ocupada");
			this.lbCliente.setText(this.cliente.getNome());
			this.lbValorTotal.setText(String.format("%.2f", 0f));
			this.pnMesa.revalidate();
			if (this.cliente.getComanda() == null) {
				this.cliente.setComanda(new Cardapio());
			}
			this.cliente.getComanda().setLbValorTotal(lbValorTotal);
			consumoAutomatico();
		}		
	}
	
}
