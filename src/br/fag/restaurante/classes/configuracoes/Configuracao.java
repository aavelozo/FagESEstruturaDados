package br.fag.restaurante.classes.configuracoes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.fag.restaurante.classes.JTextFieldSelOnFocus;

import static javax.swing.JOptionPane.showMessageDialog;


/**
 * Classe para gerenciar um item de configuracao, com seus valores e acoes
 */
public class Configuracao implements KeyListener {
	private String nome = null;
	private String nomeVisivel = null;
	private String tipo = null;
	private String valor = null;
	private Integer valorMin = null;
	private Integer valorMax = null;
	private JPanel pnConfiguracao;
	private JComponent componenteValor;
	private JLabel lbInfo;
	
	/**
	 * Constructor
	 * @param pNome
	 * @param pNomeVisivel
	 * @param pTipo
	 * @param pValor
	 */
	public Configuracao(String pNome, String pNomeVisivel, String pTipo, String pValor) {
		this.nome = pNome;
		this.nomeVisivel = pNomeVisivel;
		this.tipo = pTipo;
		this.valor = pValor;
	}
	
	/**
	 * Constructor
	 * @param pNome
	 * @param pNomeVisivel
	 * @param pTipo
	 * @param pValor
	 */
	public Configuracao(String pNome, String pNomeVisivel, String pTipo, String pValor,Integer pValorMin, Integer pValorMax) {
		this.nome = pNome;
		this.nomeVisivel = pNomeVisivel;
		this.tipo = pTipo;
		this.valor = pValor;
		this.valorMin = pValorMin;
		this.valorMax = pValorMax;
	}
	
	/**
	 * Gera os componentes visuais das configuracoes, dentro de um container especificado
	 * @param {JPanel} pnContainer - o container que contera as configuracoes
	 */
	public void gerarComponentes(JPanel pnContainer) {
		this.pnConfiguracao = new JPanel();				
		JLabel lbConfig = new JLabel(this.nomeVisivel);		
		lbConfig.setPreferredSize(new Dimension(300,20));
		this.pnConfiguracao.add(lbConfig);
		pnConfiguracao.setAlignmentX(SwingConstants.LEFT);
		switch(this.tipo.trim().toLowerCase()) {
			case "boolean":case"bool":				
				JCheckBox check = new JCheckBox();				
				check.setSelected(Boolean.valueOf(this.valor));
				check.addChangeListener(new ChangeListener() {

					@Override
					public void stateChanged(ChangeEvent e) {
						alterouValor(e);
					}
					
				});
				this.pnConfiguracao.add(check);
				this.componenteValor = check;
				break;
			default:
				JTextFieldSelOnFocus textField = new JTextFieldSelOnFocus(this.valor);
				textField.setPreferredSize(new Dimension(200,20));
				/**
				 * Listener que atualiza o valor da configuracao quando o usuario altera-la no textField
				 */
				textField.addKeyListener(this);				
				this.pnConfiguracao.add(textField);
				this.componenteValor = textField;
				JLabel lbInfo = new JLabel("");
				this.lbInfo = lbInfo;
				this.pnConfiguracao.add(lbInfo);
				break;
		}
		GridBagConstraints ct = new GridBagConstraints();
		ct.gridx = 0;		
		ct.gridy = pnContainer.getComponentCount();
		ct.anchor = GridBagConstraints.WEST;
		ct.fill = SwingConstants.HORIZONTAL;
		pnContainer.add(this.pnConfiguracao,ct);		
		pnContainer.revalidate();
	}
	
	/**
	 * Chamada pelo listener, quando o usuario alterar o valor
	 */
	public void alterouValor(Object e) {
		try {
			if (this.componenteValor instanceof JTextField) {
				JTextField textField = (JTextField)this.componenteValor;
				this.valor = textField.getText();
				if (this.tipo.trim().toLowerCase().equalsIgnoreCase("integer")) {
					try {
						boolean temInfo = false;
						int val = Integer.valueOf(this.valor);
						if (this.valorMin != null) {
							if (val < valorMin) {
								this.valor = String.valueOf(this.valorMin);
								this.lbInfo.setText("Valor menor que o minimo("+this.valor+")");
								temInfo = true;
							}
						}
						if (this.valorMax != null) {
							if (val > valorMax) {
								this.valor = String.valueOf(this.valorMax);
								this.lbInfo.setText("Valor maior que o maximo(" + this.valor + ")");
								temInfo = true;
							}
						}
						if (!temInfo) {
							this.lbInfo.setText("");
						}
					} catch (Exception erroNum) {
						if (!this.valor.trim().equals("")) {
							this.valor = String.valueOf(this.valorMin != null ? this.valorMin : 0);					
							textField.setText(this.valor);
						} else {
							this.valor = String.valueOf(this.valorMin != null ? this.valorMin : 0);	
						}
					}
				}
			} else if (this.componenteValor instanceof JCheckBox) {
				this.valor = String.valueOf(((JCheckBox)this.componenteValor).isSelected());
			}
		} catch (Exception erro) {
			erro.printStackTrace();
			showMessageDialog(null,erro.getClass().getName() + "\n" + erro.getMessage());
		}
	}
	
	/*GETTERS AND SETTERS*/	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNomeVisivel() {
		return nomeVisivel;
	}
	public void setNomeVisivel(String nomeVisivel) {
		this.nomeVisivel = nomeVisivel;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}

	
	/**/
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		alterouValor(e);
	}
}
