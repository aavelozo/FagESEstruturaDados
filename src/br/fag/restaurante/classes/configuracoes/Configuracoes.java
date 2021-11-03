package br.fag.restaurante.classes.configuracoes;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Classe para gerir as configuracoes do programa
 */
public class Configuracoes {
	private static Configuracoes _this = null;
	private ArrayList<Configuracao> listaConfigs = null;
	private JPanel pnConfiguracoes = null;
	
	/**
	 * Constructor - SingleTon (private)
	 */
	private Configuracoes() {
		inicializarConfiguracoes();
	}
	
	/**
	 * GetInstancia - Singleton e synchronized - obtem a instancia unica da classe
	 * @return {Configuracoes} - a instancia unica da classe 
	 */
	public static synchronized Configuracoes getInstancia() {
		if (_this == null) {
			_this = new Configuracoes();
		}
		return _this;
	}
	
	/**
	 * inicializa (gera) as configuracoes do programa, conforme as descritas no codigo deste methodo, 
	 * NAO ALTERAR O NOME INTERNO (1º parametro) do construtor do item de configuracao, pois sao utilizados
	 * em outros processos do programa. O valor (ultimo paramtro) pode ser alterado.
	 */
	public void inicializarConfiguracoes() {
		try {
			this.listaConfigs = new ArrayList<Configuracao>();
			listaConfigs.add(new Configuracao("qtMesas","Quantidade de Mesas","Integer","20",1,Integer.MAX_VALUE-1));
			listaConfigs.add(new Configuracao("tamanhoMesa","Tamanho da Mesa","Integer","120",50,300));
			listaConfigs.add(new Configuracao("qtBrindes","Quantidade de Brindes","Integer","15",1,Integer.MAX_VALUE-1));
			listaConfigs.add(new Configuracao("entradaAutomaticaClientes","[DEMO]Entrada Automatica Clientes","Boolean","true"));
			listaConfigs.add(new Configuracao("freqEntradaAutomaticaCliente","[DEMO]Frequência de Entrada Automatica de Clientes","Integer","1000",1000,(int)(Integer.MAX_VALUE/10)));
			listaConfigs.add(new Configuracao("encerramentoAutomaticoClientes","[DEMO]Encerramento Automatico Clientes","Boolean","true"));
			listaConfigs.add(new Configuracao("tempoPermanenciaAutomaticaCliente","[DEMO]Tempo Permanência Automatica de Clientes","Integer","20000",1000,(int)(Integer.MAX_VALUE/10)));
			listaConfigs.add(new Configuracao("consumoAutomatico","[DEMO]Consumo Automatico","Boolean","true"));
			listaConfigs.add(new Configuracao("freqConsumoAutomatico","[DEMO]Frequência de Consumo Automatico","Integer","4000",1000,(int)(Integer.MAX_VALUE/10)));
			listaConfigs.add(new Configuracao("atendimentoAutomaticoCaixa","[DEMO]Atendimento Automatico Caixa","Boolean","true"));
			listaConfigs.add(new Configuracao("freqAtendimentoAutomaticoCaixa","[DEMO]Frequência de Atendimento Automatica no Caixa","Integer","3000",1000,(int)(Integer.MAX_VALUE/10)));
			listaConfigs.add(new Configuracao("reabastecimentoAutomaticoPilhaBrindes","[DEMO]Reabastecimento automatico pilha Brindes","Boolean","true"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * carrega (gera) os componentes visuais para mostrar/permitir alterar as configuracoes
	 */
	public void carregarDados() {
		for(int i = 0; i < this.listaConfigs.size(); i++) {
			this.listaConfigs.get(i).gerarComponentes(this.pnConfiguracoes);
		}
	}
	
	
	
	/*GETTERS AND SETTERS*/
	public String getConfig(String pNome) {
		for(int i = 0; i < this.listaConfigs.size(); i++) {
			if (this.listaConfigs.get(i).getNome().trim().equalsIgnoreCase(pNome)) {
				return this.listaConfigs.get(i).getValor();
			}
		}
		return null;
	}
	public ArrayList<Configuracao> getListaConfigs() {
		return listaConfigs;
	}
	public JPanel getPnConfiguracoes() {
		return pnConfiguracoes;
	}
	public void setPnConfiguracoes(JPanel pnConfiguracoes) {
		this.pnConfiguracoes = pnConfiguracoes;
	}	
}
