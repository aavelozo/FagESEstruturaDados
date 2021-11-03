package br.fag.restaurante.classes.caixa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.fag.restaurante.classes.cliente.Cliente;
import br.fag.restaurante.classes.cliente.Clientes;

import static javax.swing.JOptionPane.showMessageDialog;


/**
 * Classe para gerenciar um objeto que fica na fila do caixa, representa um cliente na fila do caixa
 */
public class PosicaoFilaCaixa {
	private Cliente cliente;
	private Caixa caixa;
	private JPanel pnPosicaoFilaCaixa = null;
	
	/**
	 * Constructor - cria o objeto visual e suas propriedades do cliente na fila do caixa
	 * @param (JPanel} container - o container da fila do caixa
	 * @param (Cliente) pCliente - o cliente a incluir na fila do caixa 
	 */
	public PosicaoFilaCaixa(JPanel container,Cliente pCliente) {
		this.cliente = pCliente;
		this.pnPosicaoFilaCaixa = new JPanel();
		BorderLayout l = new BorderLayout();
		pnPosicaoFilaCaixa.setLayout(l);
				
		JLabel lbCliente = new JLabel(this.cliente.getNome());		
		lbCliente.setHorizontalAlignment(SwingConstants.LEFT);
		pnPosicaoFilaCaixa.add(lbCliente,BorderLayout.WEST);
		
		JLabel lbValor = new JLabel(String.format("%.2f",this.cliente.getComanda().getValorTotal()));		
		lbValor.setHorizontalAlignment(SwingConstants.RIGHT);
		pnPosicaoFilaCaixa.add(lbValor,BorderLayout.EAST);
		
		pnPosicaoFilaCaixa.setAlignmentY(SwingConstants.NORTH);
		pnPosicaoFilaCaixa.setBackground(Color.orange);
		
		GridBagConstraints ct = new GridBagConstraints();
		ct.gridx = 0;		
		ct.gridy = caixa.getInstancia().getIndexProxElemento();
		ct.insets.top = 5;		
		ct.fill = GridBagConstraints.HORIZONTAL;
		ct.anchor = GridBagConstraints.NORTH;
		container.add(pnPosicaoFilaCaixa,ct);		
		container.revalidate();
	}
	
	/**
	 * remove este componente da fila do caixa
	 */
	public void remover() {
		Container parent = this.pnPosicaoFilaCaixa.getParent();
		this.pnPosicaoFilaCaixa.setVisible(false);
		this.pnPosicaoFilaCaixa.revalidate();
		if (parent != null) {
			parent.remove(this.pnPosicaoFilaCaixa);
			parent.revalidate();
			parent.getParent().revalidate();
		}
	}

	
	
	/*GETTERS AND SETTERS*/	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
