package br.fag.restaurante.classes.brinde;

import static javax.swing.JOptionPane.showMessageDialog;

import java.util.Stack;

import javax.swing.JPanel;

import br.fag.restaurante.classes.configuracoes.Configuracoes;
import br.fag.restaurante.variaveis.Variaveis;


/**
 * Classe para gerenciar os objetos do tipo Brinde, suas acoes e componentes visuais(SingleTon)
 */
public class Brindes {
	public static Brindes _this = null;
	private Stack<Brinde> pilhaBrindes = null;
	private JPanel pnPilhaBrindes = null;
	private Configuracoes configs = null;
	
	/**
	 * Constructor - SingleTon (private)
	 */
	private Brindes() {
		this.pilhaBrindes = new Stack<Brinde>();
		this.configs = Configuracoes.getInstancia();
	}
	
	/**
	 * GetInstancia - Singleton e synchronized - obtem a instancia unica da classe
	 * @return {Brindes} - a instancia unica da classe 
	 */
	public static synchronized Brindes getInstancia() {
		if (_this == null) {
			_this = new Brindes();
		}
		return _this;
	}
	
	/**
	 * Inicia a pilha de brindes
	 */
	public void iniciarPilha() {
		this.limparPilha();
		this.abastecerPilha();
	}
	
	/**
	 * Abastece a pilha de brindes (Re-abastece tambem, ate o o limite de itens conforme configuracoes)
	 */
	public void abastecerPilha() {
		try {
			if (this.pilhaBrindes == null) {
				this.pilhaBrindes = new Stack<Brinde>();
			}
			int qt = this.pilhaBrindes.size();
			int qtBrindes = Integer.valueOf(this.configs.getConfig("qtBrindes"));
			if (qt >= qtBrindes) {
				showMessageDialog(null, "Pilha já está cheia!");
			} else {
				for (int i = qt; i < qtBrindes; i++ ) {
					Brinde brinde = new Brinde(this.pnPilhaBrindes,i+1);		
					this.pilhaBrindes.add(brinde);
				}
			}
			System.out.println("PILHA ABASTECIDA");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * limpa a pilha de brindes e remove os componentes visuais correspondentes
	 */
	public void limparPilha() {
		if (this.pilhaBrindes != null) {
			Brinde brinde = null;
			int qt = this.pilhaBrindes.size();
			for(int i = 0; i < qt; i++) {
				brinde = this.pilhaBrindes.pop();
				brinde.remover();				
			}
		}
	}
	
	/**
	 * Obtem o objeto brinde do topo da pilha e o remove
	 * @return {Brinde} o objeto brinde removido
	 */
	public Brinde obterElTopoERemover() {
		Brinde retorno = null;
		if (this.pilhaBrindes.size() > 0) {
			retorno = this.pilhaBrindes.pop();
			retorno.remover();
		} else if (Variaveis.getInstancia().getStatusDemo() == 1 && Variaveis.getInstancia().getStatusAtendimento() == 1 && Boolean.valueOf(configs.getInstancia().getConfig("reabastecimentoAutomaticoPilhaBrindes"))) {
			this.abastecerPilha();
			retorno = this.obterElTopoERemover();
		}
		return retorno;
	}
	
	
	
	/*GETTERS AND SETTERS*/	
	public JPanel getPnPilhaBrindes() {
		return pnPilhaBrindes;
	}
	public void setPnPilhaBrindes(JPanel pnPilhaBrindes) {
		this.pnPilhaBrindes = pnPilhaBrindes;
	}
	
}
