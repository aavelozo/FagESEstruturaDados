package br.fag.restaurante.telas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.fag.restaurante.classes.JTextFieldSelOnFocus;
import br.fag.restaurante.classes.cardapio.Cardapio;
import br.fag.restaurante.classes.cardapio.ItemCardapio;
import br.fag.restaurante.classes.mesa.Mesa;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JButton;
import java.awt.Color;


/**
 * Classe para mostrar o cardapio (comanda) do cliente na mesa
 */
public class TelaCardapio extends JFrame implements KeyListener{
	private JFrame frmTelaCardapio;
	private JPanel contentPane;
	private JPanel pnItensCardapio;
	private Cardapio cardapio = null;
	private Mesa mesa = null;
	private JTextField tfQtItens;
	private JTextField tfVlrMed;
	private JTextField tfQtTot;
	private JTextField tfVlTot;

	/**
	 * main - roda a janela
	 */
	/*public static void main(String[] args) {
		try {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TelaCardapio window = new TelaCardapio(null,null);
						window.frmTelaCardapio.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			showMessageDialog(null,e.getClass().getName() + "\n" + e.getMessage());
		}
	}*/
	
	/**
	 * Constructor
	 * @param {Cardapio} cardapio - o cardapio do cliente (comanda)
	 * @param {Mesa} mesa - a mesa do cliente
	 */
	public TelaCardapio(Cardapio cardapio,Mesa mesa) {
		initialize(cardapio,mesa);
	}
	
	/**
	 * Inicializa, constroi os componentes visuais
	 * @param {Cardapio} cardapio - o cardapio do cliente (comanda)
	 * @param {Mesa} mesa - a mesa do cliente
	 */
	private void initialize(Cardapio cardapio,Mesa mesa) {
		try {
			frmTelaCardapio = new JFrame();
			frmTelaCardapio.setTitle("Comanda");
			frmTelaCardapio.setBounds(0, 0, 400, 450);
			//frmTelaCardapio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			setBounds(100, 100, 400, 450);
			contentPane = new JPanel();
			frmTelaCardapio.add(contentPane,BorderLayout.CENTER);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));		
			setContentPane(contentPane);	
			
			JPanel panel_3 = new JPanel();
			FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
			flowLayout_1.setAlignment(FlowLayout.LEFT);
			contentPane.add(panel_3, BorderLayout.NORTH);
			
			JLabel lblNewLabel_4 = new JLabel("C\u00F3d.");
			lblNewLabel_4.setPreferredSize(new Dimension(30,20));
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel_3.add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("Descri\u00E7\u00E3o");
			lblNewLabel_5.setPreferredSize(new Dimension(100,20));
			lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel_3.add(lblNewLabel_5);
			
			JLabel lblNewLabel_6 = new JLabel("Vlr.UN.");
			lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_6.setPreferredSize(new Dimension(50,20));
			lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel_3.add(lblNewLabel_6);
			
			JLabel lblNewLabel_7 = new JLabel("Qtd.");
			lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_7.setPreferredSize(new Dimension(50,20));
			lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel_3.add(lblNewLabel_7);
			
			JLabel lblNewLabel_8 = new JLabel("Vlr.Tot.Item");
			lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_8.setPreferredSize(new Dimension(70,20));
			lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel_3.add(lblNewLabel_8);
			
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new BorderLayout(0, 0));
			
			JPanel pnTotalCardapio = new JPanel();
			panel.add(pnTotalCardapio);
			GridBagLayout gbl_pnTotalCardapio = new GridBagLayout();
			gbl_pnTotalCardapio.columnWidths = new int[]{0, 0, 0, 0, 0};
			gbl_pnTotalCardapio.rowHeights = new int[]{0, 0, 0};
			gbl_pnTotalCardapio.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_pnTotalCardapio.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			pnTotalCardapio.setLayout(gbl_pnTotalCardapio);
			
			JLabel lblNewLabel = new JLabel("Qt.Itens(Mix)");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			pnTotalCardapio.add(lblNewLabel, gbc_lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Vlr.Un.M\u00E9dio");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = 0;
			pnTotalCardapio.add(lblNewLabel_1, gbc_lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Qt.Tot.Itens");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 2;
			gbc_lblNewLabel_2.gridy = 0;
			pnTotalCardapio.add(lblNewLabel_2, gbc_lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Vlr.Tot.Itens");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_3.gridx = 3;
			gbc_lblNewLabel_3.gridy = 0;
			pnTotalCardapio.add(lblNewLabel_3, gbc_lblNewLabel_3);
			
			tfQtItens = new JTextField();
			tfQtItens.setHorizontalAlignment(SwingConstants.CENTER);
			tfQtItens.setText("0");
			tfQtItens.setEditable(false);
			tfQtItens.setFocusable(false);
			GridBagConstraints gbc_tfQtItens = new GridBagConstraints();
			gbc_tfQtItens.insets = new Insets(0, 0, 0, 5);
			gbc_tfQtItens.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfQtItens.gridx = 0;
			gbc_tfQtItens.gridy = 1;
			pnTotalCardapio.add(tfQtItens, gbc_tfQtItens);
			tfQtItens.setColumns(10);
			
			tfVlrMed = new JTextField();
			tfVlrMed.setText("0.00");
			tfVlrMed.setHorizontalAlignment(SwingConstants.CENTER);
			tfVlrMed.setEditable(false);
			tfVlrMed.setFocusable(false);
			GridBagConstraints gbc_tfVlrMed = new GridBagConstraints();
			gbc_tfVlrMed.insets = new Insets(0, 0, 0, 5);
			gbc_tfVlrMed.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfVlrMed.gridx = 1;
			gbc_tfVlrMed.gridy = 1;
			pnTotalCardapio.add(tfVlrMed, gbc_tfVlrMed);
			tfVlrMed.setColumns(10);
			
			tfQtTot = new JTextField();
			tfQtTot.setText("0.00");
			tfQtTot.setHorizontalAlignment(SwingConstants.CENTER);
			tfQtTot.setEditable(false);
			tfQtTot.setFocusable(false);
			GridBagConstraints gbc_tfQtTot = new GridBagConstraints();
			gbc_tfQtTot.insets = new Insets(0, 0, 0, 5);
			gbc_tfQtTot.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfQtTot.gridx = 2;
			gbc_tfQtTot.gridy = 1;
			pnTotalCardapio.add(tfQtTot, gbc_tfQtTot);
			tfQtTot.setColumns(10);
			
			tfVlTot = new JTextField();
			tfVlTot.setText("0.00");
			tfVlTot.setHorizontalAlignment(SwingConstants.CENTER);
			tfVlTot.setEditable(false);
			tfVlTot.setFocusable(false);
			GridBagConstraints gbc_tfVlTot = new GridBagConstraints();
			gbc_tfVlTot.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfVlTot.gridx = 3;
			gbc_tfVlTot.gridy = 1;
			pnTotalCardapio.add(tfVlTot, gbc_tfVlTot);
			tfVlTot.setColumns(10);
			
			JPanel pnBotoes = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnBotoes.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panel.add(pnBotoes, BorderLayout.SOUTH);
			
			JButton btnNewButton = new JButton("OK");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gravarCardapio();
				}
			});
			btnNewButton.setBackground(new Color(0, 0, 205));
			btnNewButton.setForeground(Color.white);
			pnBotoes.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Cancelar");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancelar();
				}
			});
			btnNewButton_1.setBackground(new Color(230, 30, 60));
			pnBotoes.add(btnNewButton_1);
			
			JPanel panel_1 = new JPanel();
			contentPane.add(panel_1, BorderLayout.CENTER);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane, BorderLayout.CENTER);
			
			JPanel panel_2 = new JPanel();
			scrollPane.setViewportView(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			pnItensCardapio = new JPanel();
			panel_2.add(pnItensCardapio, BorderLayout.NORTH);
			GridBagLayout gbl_pnItensCardapio = new GridBagLayout();
			gbl_pnItensCardapio.columnWidths = new int[] {0};
			gbl_pnItensCardapio.rowHeights = new int[]{0};
			gbl_pnItensCardapio.columnWeights = new double[]{Double.MIN_VALUE};
			gbl_pnItensCardapio.rowWeights = new double[]{Double.MIN_VALUE};
			pnItensCardapio.setLayout(gbl_pnItensCardapio);
			
			
			this.cardapio = cardapio;
			this.mesa = mesa;
			this.setTitle("Comanda " + mesa.getNome() + ", "+mesa.getCliente().getNome());
			montarCardapio();
		} catch (Exception e) {
			e.printStackTrace();
			showMessageDialog(null,e.getClass().getName() + "\n" + e.getMessage());
		}
	}
	
	
	/**
	 * Monta o cardapio visual conforme itens do cardapio
	 */
	public void montarCardapio() {
		try {
			ItemCardapio itemCardapio = null;
			for(int i = 0; i < cardapio.getListaCardapio().size();i++) {
				itemCardapio = cardapio.getListaCardapio().get(i);
				JPanel pnItemCardapio = new JPanel();
				
				JLabel lb = new JLabel(String.valueOf(itemCardapio.getCod()));
				lb.setPreferredSize(new Dimension(30,20));
				pnItemCardapio.add(lb);
				
				lb = new JLabel(itemCardapio.getNome());
				lb.setPreferredSize(new Dimension(100,20));
				pnItemCardapio.add(lb);
				
				JTextField textField = new JTextField(String.format("%.2f",itemCardapio.getValor()));
				textField.setPreferredSize(new Dimension(50,20));
				textField.setEditable(false);
				textField.setFocusable(false);
				pnItemCardapio.add(textField);
				
				JTextFieldSelOnFocus textFieldFoco = new JTextFieldSelOnFocus(String.format("%.2f",itemCardapio.getQt()));
				textFieldFoco.setPreferredSize(new Dimension(50,20));
				textFieldFoco.addKeyListener(this);
				pnItemCardapio.add(textFieldFoco);
				
				textField = new JTextField(String.format("%.2f",itemCardapio.getQt() * itemCardapio.getValor()));
				textField.setEditable(false);
				textField.setFocusable(false);
				textField.setPreferredSize(new Dimension(50,20));
				pnItemCardapio.add(textField);
				
				
				GridBagConstraints ct = new GridBagConstraints();
				ct.gridx = 0;		
				ct.gridy = pnItensCardapio.getComponents().length;
				ct.anchor = GridBagConstraints.WEST;
				pnItensCardapio.add(pnItemCardapio,ct);
				pnItensCardapio.revalidate();
				textFieldFoco.requestFocus();
			}
			pnItensCardapio.revalidate();
			this.recalcularCardapio();
			
		} catch (Exception e) {
			e.printStackTrace();
			showMessageDialog(null,e.getClass().getName() + "\n" + e.getMessage());
		}
	}
	
	/**
	 * chamado por listener ao ter o valor alterado do textField do item
	 * @param {KeyEvent} e - o evento que alterou o campo
	 */
	private void atualizouItemCardapio(KeyEvent e) {
		try {
			JTextField textFieldQt = (JTextField) e.getSource();
			String strQt = textFieldQt.getText().trim().replace(",", ".");
			if (strQt == null) {
				strQt = "0";
			} else if (strQt.equals("") || strQt.equals(".")) {
				strQt = "0";
			}
			double qt;
			try {
				qt = Double.valueOf(strQt);
				if (qt < 0) {
					textFieldQt.setText("0");
					qt = 0;					
				}
			} catch (Exception erroNum) {
				textFieldQt.setText("0");
				qt = 0;
			}
			JPanel pn = (JPanel) textFieldQt.getParent();
			JLabel labelCod = (JLabel) pn.getComponent(0);
			int cod = Integer.valueOf(labelCod.getText());
			JTextField textFieldValor = (JTextField) pn.getComponent(2);
			double valor = Double.valueOf(textFieldValor.getText().replace(",", "."));
			JTextField textFieldValorTotal = (JTextField) pn.getComponent(4);
			double valorTotal = qt * valor;
			textFieldValorTotal.setText(String.format("%.2f",valorTotal));			
			this.recalcularCardapio();
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				this.gravarCardapio();
			}
		} catch (Exception erro) {
			erro.printStackTrace();
			showMessageDialog(null,erro.getClass().getName() + "\n" + erro.getMessage());
		}
	}
	
	/**
	 * recalcula valores totais do cardapio
	 */
	public void recalcularCardapio() {
		try {
			int qtItensCardapio = this.pnItensCardapio.getComponentCount();
			JPanel pnItem = null;
			JTextField textField = null;
			String strQt;
			double qt,qtTotal = 0,vl,vlTotal = 0;
			int qtItens = 0;
			for(int i = 0; i < qtItensCardapio; i++) {
				pnItem = (JPanel) this.pnItensCardapio.getComponent(i);
				textField = (JTextField) pnItem.getComponent(3);
				strQt = textField.getText().trim().replace(",", ".");
				if (strQt == null) {
					strQt = "0";
				} else if (strQt.equals("") || strQt.equals(".")) {
					strQt = "0";
				}
				qt = Double.valueOf(strQt);
				if (qt > 0) {
					qtItens++;
					qtTotal += qt;
					textField = (JTextField) pnItem.getComponent(4);
					vl = Double.valueOf(textField.getText().replace(",", "."));
					vlTotal += vl;
				}
			}
			this.tfQtItens.setText(String.valueOf(qtItens));
			this.tfQtTot.setText(String.format("%.2f",qtTotal));
			this.tfVlTot.setText(String.format("%.2f",vlTotal));
			if (qtTotal > 0) {
				this.tfVlrMed.setText(String.format("%.2f",vlTotal / qtTotal));
			} else {
				this.tfVlrMed.setText("0.00");
			}
		} catch (Exception erro) {
			erro.printStackTrace();
			showMessageDialog(null,erro.getClass().getName() + "\n" + erro.getMessage());
		}
	}

	/**
	 * fecha a janela do cardapio
	 */
	private void fechar() {
		try {
			this.setVisible(false);
			this.dispose();
		} catch (Exception e) {
			e.printStackTrace();
			showMessageDialog(null,e.getClass().getName() + "\n" + e.getMessage());
		}
	}
	
	/**
	 * cancela as alteracoes no cardapio
	 */
	protected void cancelar() {
		this.fechar();
	}

	/**
	 * grava o cardapio (confirma as alteracoes nos items no cardapio(comanda) do cliente)
	 */
	protected void gravarCardapio() {
		try {
			int qtItensCardapio = this.pnItensCardapio.getComponentCount();
			JPanel pnItem = null;
			JTextField textField = null;
			String strQt;
			double qt,qtTotal = 0,vl,vlTotal = 0;
			int qtItens = 0;
			ItemCardapio itemCardapio = null;
			for(int i = 0; i < qtItensCardapio; i++) {
				pnItem = (JPanel) this.pnItensCardapio.getComponent(i);
				JLabel labelCod = (JLabel) pnItem.getComponent(0);
				int cod = Integer.valueOf(labelCod.getText());				
				itemCardapio = this.cardapio.getItemCardapio(cod);
				
				textField = (JTextField) pnItem.getComponent(3);
				strQt = textField.getText().trim().replace(",", ".");
				if (strQt == null) {
					strQt = "0";
				} else if (strQt.equals("") || strQt.equals(".")) {
					strQt = "0";
				}
				qt = Double.valueOf(strQt);
				itemCardapio.setQt(qt);
				if (qt > 0) {
					qtItens++;
					qtTotal += qt;
					textField = (JTextField) pnItem.getComponent(4);
					vl = Double.valueOf(textField.getText().replace(",", "."));
					itemCardapio.setValorTotal(vl);
					vlTotal += vl;
				} else {
					itemCardapio.setValorTotal(0);
				}
			}
			this.cardapio.setQtItens(qtItens);
			this.cardapio.setQtTotal(qtTotal);
			this.cardapio.setValorTotal(vlTotal);			
			this.fechar();
		} catch (Exception erro) {
			erro.printStackTrace();
			showMessageDialog(null,erro.getClass().getName() + "\n" + erro.getMessage());
		}
		
	}
	

	
	/*implements KeyListener*/
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		atualizouItemCardapio(e);
	}
}
