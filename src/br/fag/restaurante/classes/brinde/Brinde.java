package br.fag.restaurante.classes.brinde;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.fag.restaurante.classes.cliente.Cliente;
import br.fag.restaurante.classes.configuracoes.Configuracoes;
import br.fag.restaurante.funcoes.FuncoesMatematica;
import br.fag.restaurante.variaveis.Variaveis;


/**
 * Classe para gerenciar o objeto do tipo Brinde, suas acoes e componentes visuais
*/
public class Brinde {
	private Integer cod;
	private String nome;
	private Cliente cliente;
	private JPanel pnBrinde;	
	private Configuracoes configs = null;
	
	/**
	 * Constructor
	 * @param container - o painel que contera a fila visual de brindes
	 * @param pCod - o cod do brinde
	 */
	public Brinde(JPanel container, Integer pCod) {
		this.cod = pCod;
		this.nome = Variaveis.getInstancia().getListaNomesBrindes().get(FuncoesMatematica.getInstancia().rand(0,Variaveis.getInstancia().getListaNomesBrindes().size()-1));
		pnBrinde = new JPanel();
		pnBrinde.setAlignmentY(SwingConstants.NORTH);
		pnBrinde.setBackground(Color.magenta);
		
		JLabel lbNome = new JLabel(this.cod + "-" + this.nome);
		pnBrinde.add(lbNome);
		
		GridBagConstraints ct = new GridBagConstraints();
		ct.gridx = 0;		
		int y = container.getComponentCount();
		ct.gridy = Integer.valueOf(configs.getInstancia().getConfig("qtBrindes")) - y;
		ct.insets.top = 5;
		ct.anchor = GridBagConstraints.NORTH;	
		ct.fill = GridBagConstraints.HORIZONTAL;
		
		container.add(pnBrinde,ct);
		container.revalidate();
	}
	
	/**
	 * Remove o brinde do componente visual continente
	 */
	public void remover() {
		Container parent = this.pnBrinde.getParent();
		this.pnBrinde.setVisible(false);
		this.pnBrinde.revalidate();
		if (parent != null) {
			parent.remove(this.pnBrinde);
			parent.revalidate();
			parent.getParent().revalidate();
		}
	}
	
	
	
	/*GETTERS AND SETTERS*/	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
