package br.fag.restaurante.classes.caixa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.fag.restaurante.classes.TarefaAssincrona;
import br.fag.restaurante.classes.brinde.Brinde;
import br.fag.restaurante.classes.brinde.Brindes;
import br.fag.restaurante.classes.cardapio.Cardapio;
import br.fag.restaurante.classes.cardapio.ItemCardapio;
import br.fag.restaurante.classes.cliente.Cliente;
import br.fag.restaurante.classes.configuracoes.Configuracoes;
import br.fag.restaurante.funcoes.FuncoesMatematica;
import br.fag.restaurante.variaveis.Variaveis;

import static javax.swing.JOptionPane.showMessageDialog;


/**
 * Classe para gerenciar o objeto Caixa, seuas ações e componentes visuais (SingleTon)
 */
public class Caixa {
	private static Caixa _this = null;
	private Queue<PosicaoFilaCaixa> filaCaixa = null;
	private int qtItens = 0;
	private double vlrMed = 0f;
	private double qtTotItens = 0f;
	private double vlrTotal = 0f;
	private JPanel pnFilaCaixa = null;
	private JTextField tfQtItens = null;
	private JTextField tfVlrMed = null;
	private JTextField tfQtTotItens = null;
	private JTextField tfVlTotal = null;
	private JLabel lbInfo = null;
	private Brindes brindes = null;
	private Cardapio cardapio = null;
	private Configuracoes configs;
	private TarefaAssincrona threadAtendimentoAutomatico;
	private String info;
	private int indexProxElemento = 0;
	
	/**
	 * Constructor - SingleTon (private)
	 */
	private Caixa() {
		cardapio = new Cardapio();
		atendimentoAutomatico();
	}
	
	/**
	 * GetInstancia - Singleton e synchronized - obtem a instancia unica da classe
	 * @return {Caixa} - a instancia unica da classe 
	 */
	public static synchronized Caixa getInstancia() {
		if (_this == null) {
			_this = new Caixa();
		}
		return _this;
	}
	
	/**
	 * Inclui um objeto Cliente na fila do caixa
	 * @param {Cliente} pCliente - objeto Cliente
	 */
	public void incluirClienteFila(Cliente pCliente) {
		try {
			if (Variaveis.getInstancia().getStatusAtendimento() == 1) {
				if (this.filaCaixa == null) {
					this.filaCaixa = new LinkedList<PosicaoFilaCaixa>();
					this.indexProxElemento = 0;
				}
				PosicaoFilaCaixa posFilaCx = new PosicaoFilaCaixa(this.pnFilaCaixa,pCliente);				
				this.filaCaixa.add(posFilaCx);
				this.indexProxElemento++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Atende o proximo cliente da fila
	 */
	public void atenderProximo() {
		try {
			if (Variaveis.getInstancia().getStatusAtendimento() == 1) {
				if (this.filaCaixa == null) {
					this.filaCaixa = new LinkedList<PosicaoFilaCaixa>();
				}
				if (this.filaCaixa.size() > 0) {
					Cliente cliente = null;
					Cardapio comanda = null;
					Brinde brinde = brindes.getInstancia().obterElTopoERemover();
					if (brinde != null) {
						PosicaoFilaCaixa posFilaCx = this.filaCaixa.element();
						cliente = posFilaCx.getCliente();
						cliente.setBrinde(brinde);
						this.info = "cliente " + cliente.getNome() + " gastou R$ " + String.format("%.2f",cliente.getComanda().getValorTotal()) + " e recebeu o brinde " + brinde.getNome();
						System.out.println(info);
						this.atualizarInfo();
						comanda = cliente.getComanda();
						this.vlrTotal += comanda.getValorTotal();
						this.qtTotItens += comanda.getQtTotal();						
						atualizarMixCardapio(comanda);						
						this.atualizarTotalizadores();
						posFilaCx.remover();
						this.filaCaixa.remove();
						
					} else {
						showMessageDialog(null,"Não há mais brindes na pilha!\nAbasteça a pilha de brindes antes de atender o próximo cliente.");
					}
				} else {
					showMessageDialog(null,"Não há mais clientes na fila do Caixa!");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void atualizarInfo() {
		if (this.lbInfo != null) {
			this.lbInfo.setText(this.info);
			this.lbInfo.revalidate();
			this.lbInfo.getParent().revalidate();
		}
	}

	/**
	 * Atualiza a quantidade de itens vendidos no restaurante baseado na comanda do cliente
	 * @param {Cardapio} comanda - a comanda do cliente 
	 */
	private void atualizarMixCardapio(Cardapio comanda) {
		try {
			if (this.cardapio == null) {
				this.cardapio = new Cardapio();
			}
			if (comanda != null) {
				ArrayList<ItemCardapio> itensComanda = comanda.getListaCardapio();
				ItemCardapio itemComanda = null;
				ItemCardapio itemMix = null;
				for(int i = 0; i < itensComanda.size(); i++) {
					itemComanda = itensComanda.get(i);
					if (itemComanda.getQt() > 0) {
						itemMix = this.cardapio.getItemCardapio(itemComanda.getCod());
						itemMix.setQt(itemMix.getQt() + itemComanda.getQt());
					}
				}
				ArrayList<ItemCardapio> itensMix = this.cardapio.getListaCardapio();
				this.qtItens = 0;
				for(int i = 0; i < itensMix.size();i++) {
					if (itensMix.get(i).getQt() > 0) {
						this.qtItens++;
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Atualiza os componentes visuais totalizadores gerais do restaurante com base nos valores da classe
	 */
	private void atualizarTotalizadores() {	
		try {
			if (this.tfQtTotItens != null) {
				this.tfQtTotItens.setText(String.format("%.2f", this.qtTotItens));
				this.tfQtTotItens.revalidate();
			}
			if (this.tfVlTotal != null) {
				this.tfVlTotal.setText(String.format("%.2f", this.vlrTotal));
				this.tfVlTotal.revalidate();
			}
			if (this.tfVlrMed != null) {
				if (this.qtTotItens > 0) {			
					this.tfVlrMed.setText(String.format("%.2f", this.vlrTotal / this.qtTotItens));				
				} else {
					this.tfVlrMed.setText(String.format("%.2f", this.qtTotItens));
				}
				this.tfVlrMed.revalidate();
			}
			if (this.tfQtItens != null) {
				this.tfQtItens.setText(String.valueOf(this.qtItens));
				this.tfQtItens.revalidate();
				this.tfQtItens.getParent().revalidate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	/**
	 * limpa a fila do caixa
	 */
	public void limparFila() {
		try {
			if (this.filaCaixa != null) {
				PosicaoFilaCaixa posFilaCx = null;
				int qt = this.filaCaixa.size();
				for(int i = 0; i < qt; i++) {
					posFilaCx = this.filaCaixa.element();
					posFilaCx.remover();
					this.filaCaixa.remove();
				}
				this.indexProxElemento = 0;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * limpa os valores da classe
	 */
	public void limparValores() {
		this.cardapio = new Cardapio();
		this.qtItens = 0;
		this.qtTotItens = 0;
		this.vlrMed = 0;
		this.vlrTotal = 0;
		this.atualizarTotalizadores();
	}
	
	/**
	 * processa o atendimento automatico, async para nao travar a UI
	 */
	public void processarAtendimentoAutomatico(){
		try {
			while(true) {
				Thread.sleep(FuncoesMatematica.getInstancia().rand(
					Integer.valueOf(configs.getInstancia().getConfig("freqAtendimentoAutomaticoCaixa")),
					Integer.valueOf(configs.getInstancia().getConfig("freqAtendimentoAutomaticoCaixa")) * 2
				));
				if (Variaveis.getInstancia().getStatusDemo() == 1 && Variaveis.getInstancia().getStatusAtendimento() == 1 && Boolean.valueOf(configs.getInstancia().getConfig("atendimentoAutomaticoCaixa"))) {
					if (this.filaCaixa == null) {
						this.filaCaixa = new LinkedList<PosicaoFilaCaixa>();
					}
					if (this.filaCaixa.size() > 0) {
						this.atenderProximo();
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inicia a thread para atendimento automatico
	 */
	public void atendimentoAutomatico() {
		try {			
			this.threadAtendimentoAutomatico = new TarefaAssincrona();
			this.threadAtendimentoAutomatico.setObjetoExecutar(this);
			this.threadAtendimentoAutomatico.setMetodoExecutar(this.getClass().getMethod("processarAtendimentoAutomatico"));
			this.threadAtendimentoAutomatico.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*GETTERS AND SETTERS*/	
	public JPanel getPnFilaCaixa() {
		return pnFilaCaixa;
	}
	public void setPnFilaCaixa(JPanel pnFilaCaixa) {
		this.pnFilaCaixa = pnFilaCaixa;
	}	
	public JTextField getTfQtItens() {
		return tfQtItens;
	}
	public void setTfQtItens(JTextField tfQtItens) {
		this.tfQtItens = tfQtItens;
	}
	public JTextField getTfVlrMed() {
		return tfVlrMed;
	}
	public void setTfVlrMed(JTextField tfVlrMed) {
		this.tfVlrMed = tfVlrMed;
	}
	public JTextField getTfQtTotItens() {
		return tfQtTotItens;
	}
	public void setTfQtTotItens(JTextField tfQtTotItens) {
		this.tfQtTotItens = tfQtTotItens;
	}
	public JTextField getTfVlTotal() {
		return tfVlTotal;
	}
	public void setTfVlTotal(JTextField tfVlTotal) {
		this.tfVlTotal = tfVlTotal;
	}

	public JLabel getLbInfo() {
		return lbInfo;
	}

	public void setLbInfo(JLabel lbInfo) {
		this.lbInfo = lbInfo;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Queue<PosicaoFilaCaixa> getFilaCaixa() {
		if (this.filaCaixa == null) {
			this.filaCaixa = new LinkedList<PosicaoFilaCaixa>();
		}
		return this.filaCaixa;
	}

	public void setFilaCaixa(Queue<PosicaoFilaCaixa> filaCaixa) {
		this.filaCaixa = filaCaixa;
	}

	public int getIndexProxElemento() {
		return indexProxElemento;
	}

	public void setIndexProxElemento(int indexProxElemento) {
		this.indexProxElemento = indexProxElemento;
	}	
	
	
	
}
