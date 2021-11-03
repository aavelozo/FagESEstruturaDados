package br.fag.restaurante.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import br.fag.restaurante.classes.brinde.Brindes;
import br.fag.restaurante.classes.caixa.Caixa;
import br.fag.restaurante.classes.cliente.Clientes;
import br.fag.restaurante.classes.configuracoes.Configuracoes;
import br.fag.restaurante.classes.mesa.Mesas;
import br.fag.restaurante.funcoes.FuncoesRestaurante;
import br.fag.restaurante.variaveis.Variaveis;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;
import static javax.swing.JOptionPane.showMessageDialog;


/**
 * Classe da tela principal do programa Sistema Restaurante. 
 * 
 * Sistema para gerir o atendimento de um restaurante, focando no tema collections
 * 
 * Principais classes do projeto: Mesas(Lista), Caixa(Fila), Configuracoes e Clientes(Lista), Brindes (Pilha)
 * 
 * @instituicao Centro Universitário Funcação Assis Gurgacz
 * @curso 		Engenharia de Software
 * @disciplina 	Estrutura de Dados
 * @periodo 	2/2021
 * @professor 	Elenilton Dezengrini
 * @tema 		Collections
 * @projeto 	Sistema Restaurante
 * @author 		Antonio ALENCAR Velozo
 * @created 	30/11/2021
 * @lastupdate  02/11/2021
 *
 */
public class TelaPrincipal {
	private JFrame frmSistemaRestaurante;
	private JPanel pnMesas;
	private JPanel pnFilaCaixa;
	private JPanel pnPilhaBrindes;
	private JButton btnIniciarAtendimento;
	private JButton btnNovoCliente;
	private JButton btnAtenderProximo;
	private JButton btnAbastecerPilha;	
	private FuncoesRestaurante funcsRest = null;
	private Clientes clientes = null;
	private Configuracoes configs = null;
	private Mesas mesas = null;
	private Caixa caixa = null;
	private Brindes brindes = null;
	private JPanel pnConfiguracoes;
	private JTextField tfQtItens;
	private JTextField tfVlrMed;
	private JTextField tfQtTot;
	private JTextField tfVlTot;
	private JButton btnDemo;
	private JLabel lbInfo;
	
	/**
	 * main - roda a aplicação
	 */
	public static void main(String[] args) {
		try {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TelaPrincipal window = new TelaPrincipal();
						window.frmSistemaRestaurante.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			showMessageDialog(null,e.getClass().getName() + "\n" + e.getMessage());
		}
	}

	/**
	 * Constructor
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Inicializa o frame (cria os componentes visuais principais)
	 */
	private void initialize() {
		try {
			frmSistemaRestaurante = new JFrame();
			frmSistemaRestaurante.setTitle("Sistema Restaurante");
			frmSistemaRestaurante.setBounds(0, 0, 800, 600);
			frmSistemaRestaurante.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frmSistemaRestaurante.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			frmSistemaRestaurante.getContentPane().add(tabbedPane, BorderLayout.CENTER);
			
			JPanel pnAtendimento = new JPanel();
			pnAtendimento.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			pnAtendimento.setToolTipText("Mesas");
			tabbedPane.addTab("Atendimento", null, pnAtendimento, "");
			pnAtendimento.setLayout(new BorderLayout(0, 0));
			
			JPanel panel_5 = new JPanel();
			panel_5.setBorder(new TitledBorder(null, "Mesas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnAtendimento.add(panel_5, BorderLayout.CENTER);
			panel_5.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_2 = new JScrollPane();
			panel_5.add(scrollPane_2, BorderLayout.CENTER);
			
			this.pnMesas = new JPanel();
			scrollPane_2.setViewportView(pnMesas);
			GridBagLayout gbl_pnMesas = new GridBagLayout();
			gbl_pnMesas.columnWidths = new int[]{0};
			gbl_pnMesas.rowHeights = new int[]{0};
			gbl_pnMesas.columnWeights = new double[]{Double.MIN_VALUE};
			gbl_pnMesas.rowWeights = new double[]{Double.MIN_VALUE};
			pnMesas.setLayout(gbl_pnMesas);
			
			JPanel panel_1 = new JPanel();
			pnAtendimento.add(panel_1, BorderLayout.NORTH);
			
			btnIniciarAtendimento = new JButton("Iniciar Atendimento");
			btnIniciarAtendimento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					iniciarAtendimento();
				}
			});
			panel_1.add(btnIniciarAtendimento);
			
			this.btnNovoCliente = new JButton("Novo Cliente");
			this.btnNovoCliente.setEnabled(false);
			this.btnNovoCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clientes.getInstancia().novoCliente();
				}
			});
			panel_1.add(this.btnNovoCliente);
			
			btnDemo = new JButton("Parar Demo");
			btnDemo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pararDemo();
				}
			});
			btnDemo.setEnabled(false);
			panel_1.add(btnDemo);
			
			JPanel panel_2 = new JPanel();
			pnAtendimento.add(panel_2, BorderLayout.SOUTH);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JPanel panel_8 = new JPanel();
			panel_2.add(panel_8, BorderLayout.NORTH);
			
			lbInfo = new JLabel("(info)");
			panel_8.add(lbInfo);
			
			JPanel pnTotalizadores = new JPanel();
			panel_2.add(pnTotalizadores, BorderLayout.EAST);
			GridBagLayout gbl_pnTotalizadores = new GridBagLayout();
			gbl_pnTotalizadores.columnWidths = new int[]{0, 0, 0, 0, 0};
			gbl_pnTotalizadores.rowHeights = new int[]{0, 0, 0};
			gbl_pnTotalizadores.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_pnTotalizadores.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			pnTotalizadores.setLayout(gbl_pnTotalizadores);
			
			JLabel lblNewLabel = new JLabel("Qt.Itens(Mix)");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 0;
			pnTotalizadores.add(lblNewLabel, gbc_lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Vlr.Un.M\u00E9dio");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 1;
			gbc_lblNewLabel_1.gridy = 0;
			pnTotalizadores.add(lblNewLabel_1, gbc_lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Qt.Tot.Itens");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 2;
			gbc_lblNewLabel_2.gridy = 0;
			pnTotalizadores.add(lblNewLabel_2, gbc_lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Vlr.Total");
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_3.gridx = 3;
			gbc_lblNewLabel_3.gridy = 0;
			pnTotalizadores.add(lblNewLabel_3, gbc_lblNewLabel_3);
			
			tfQtItens = new JTextField();
			tfQtItens.setFont(new Font("Tahoma", Font.BOLD, 13));
			tfQtItens.setHorizontalAlignment(SwingConstants.CENTER);
			tfQtItens.setText("0");
			tfQtItens.setEditable(false);
			GridBagConstraints gbc_tfQtItens = new GridBagConstraints();
			gbc_tfQtItens.insets = new Insets(0, 0, 0, 5);
			gbc_tfQtItens.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfQtItens.gridx = 0;
			gbc_tfQtItens.gridy = 1;
			pnTotalizadores.add(tfQtItens, gbc_tfQtItens);
			tfQtItens.setColumns(10);
			
			tfVlrMed = new JTextField();
			tfVlrMed.setFont(new Font("Tahoma", Font.BOLD, 13));
			tfVlrMed.setText("0.00");
			tfVlrMed.setHorizontalAlignment(SwingConstants.CENTER);
			tfVlrMed.setEditable(false);
			GridBagConstraints gbc_tfVlrMed = new GridBagConstraints();
			gbc_tfVlrMed.insets = new Insets(0, 0, 0, 5);
			gbc_tfVlrMed.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfVlrMed.gridx = 1;
			gbc_tfVlrMed.gridy = 1;
			pnTotalizadores.add(tfVlrMed, gbc_tfVlrMed);
			tfVlrMed.setColumns(10);
			
			tfQtTot = new JTextField();
			tfQtTot.setFont(new Font("Tahoma", Font.BOLD, 13));
			tfQtTot.setText("0.00");
			tfQtTot.setHorizontalAlignment(SwingConstants.CENTER);
			tfQtTot.setEditable(false);
			GridBagConstraints gbc_tfQtTot = new GridBagConstraints();
			gbc_tfQtTot.insets = new Insets(0, 0, 0, 5);
			gbc_tfQtTot.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfQtTot.gridx = 2;
			gbc_tfQtTot.gridy = 1;
			pnTotalizadores.add(tfQtTot, gbc_tfQtTot);
			tfQtTot.setColumns(10);
			
			tfVlTot = new JTextField();
			tfVlTot.setFont(new Font("Tahoma", Font.BOLD, 13));
			tfVlTot.setText("0.00");
			tfVlTot.setHorizontalAlignment(SwingConstants.CENTER);
			tfVlTot.setEditable(false);
			GridBagConstraints gbc_tfVlTot = new GridBagConstraints();
			gbc_tfVlTot.fill = GridBagConstraints.HORIZONTAL;
			gbc_tfVlTot.gridx = 3;
			gbc_tfVlTot.gridy = 1;
			pnTotalizadores.add(tfVlTot, gbc_tfVlTot);
			tfVlTot.setColumns(10);
			
			JPanel pnCaixa = new JPanel();
			pnAtendimento.add(pnCaixa, BorderLayout.EAST);
			pnCaixa.setLayout(new BorderLayout(0, 0));
			
			JPanel pnSubCaixa = new JPanel();
			pnSubCaixa.setBorder(new TitledBorder(null, "Caixa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnCaixa.add(pnSubCaixa, BorderLayout.WEST);
			pnSubCaixa.setLayout(new BorderLayout(0, 0));
			
			JPanel panel_4 = new JPanel();
			pnSubCaixa.add(panel_4, BorderLayout.NORTH);
			panel_4.setBackground(new Color(127, 255, 212));
			GridBagLayout gbl_panel_4 = new GridBagLayout();
			gbl_panel_4.columnWidths = new int[]{27, 0};
			gbl_panel_4.rowHeights = new int[]{23, 0, 0};
			gbl_panel_4.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_panel_4.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panel_4.setLayout(gbl_panel_4);
			
			JLabel lblNewLabel1 = new JLabel("Caixa");
			lblNewLabel1.setForeground(Color.BLACK);
			GridBagConstraints gbc_lblNewLabel1 = new GridBagConstraints();
			gbc_lblNewLabel1.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel1.gridx = 0;
			gbc_lblNewLabel1.gridy = 0;
			panel_4.add(lblNewLabel1, gbc_lblNewLabel1);
			
			this.btnAtenderProximo = new JButton("Atender Pr\u00F3ximo");
			this.btnAtenderProximo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					caixa.atenderProximo();
				}
			});
			this.btnAtenderProximo.setEnabled(false);
			GridBagConstraints gbc_btnAtenderProximo = new GridBagConstraints();
			gbc_btnAtenderProximo.gridx = 0;
			gbc_btnAtenderProximo.gridy = 1;
			panel_4.add(this.btnAtenderProximo, gbc_btnAtenderProximo);
			
			JScrollPane scrollPane = new JScrollPane();
			pnSubCaixa.add(scrollPane, BorderLayout.CENTER);
			
			JPanel panel = new JPanel();
			scrollPane.setViewportView(panel);
			panel.setLayout(new BorderLayout(0, 0));
			
			pnFilaCaixa = new JPanel();
			panel.add(pnFilaCaixa, BorderLayout.NORTH);
			GridBagLayout gbl_pnFilaCaixa = new GridBagLayout();
			gbl_pnFilaCaixa.columnWidths = new int[]{0};
			gbl_pnFilaCaixa.rowHeights = new int[] {0};
			gbl_pnFilaCaixa.columnWeights = new double[]{Double.MIN_VALUE};
			gbl_pnFilaCaixa.rowWeights = new double[]{Double.MIN_VALUE};
			pnFilaCaixa.setLayout(gbl_pnFilaCaixa);
			
			
			JPanel pnBrindes = new JPanel();
			pnBrindes.setBorder(new TitledBorder(null, "Brindes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnCaixa.add(pnBrindes, BorderLayout.EAST);
			pnBrindes.setLayout(new BorderLayout(0, 0));
			
			JPanel panel_4_1 = new JPanel();
			panel_4_1.setBackground(new Color(127, 255, 212));
			pnBrindes.add(panel_4_1, BorderLayout.NORTH);
			GridBagLayout gbl_panel_4_1 = new GridBagLayout();
			gbl_panel_4_1.columnWidths = new int[]{27, 0};
			gbl_panel_4_1.rowHeights = new int[]{23, 0, 0};
			gbl_panel_4_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_panel_4_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
			panel_4_1.setLayout(gbl_panel_4_1);
			
			JLabel lblBrindes = new JLabel("Brindes");
			lblBrindes.setForeground(Color.BLACK);
			GridBagConstraints gbc_lblBrindes = new GridBagConstraints();
			gbc_lblBrindes.insets = new Insets(0, 0, 5, 0);
			gbc_lblBrindes.gridx = 0;
			gbc_lblBrindes.gridy = 0;
			panel_4_1.add(lblBrindes, gbc_lblBrindes);		
			btnAbastecerPilha = new JButton("Abastecer Pilha");
			btnAbastecerPilha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					brindes.abastecerPilha();
				}
			});
			btnAbastecerPilha.setEnabled(false);
			GridBagConstraints gbc_btnAbastecerPilha = new GridBagConstraints();
			gbc_btnAbastecerPilha.gridx = 0;
			gbc_btnAbastecerPilha.gridy = 1;
			panel_4_1.add(btnAbastecerPilha, gbc_btnAbastecerPilha);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			pnBrindes.add(scrollPane_1, BorderLayout.CENTER);
			
			JPanel panel_3 = new JPanel();
			scrollPane_1.setViewportView(panel_3);
			panel_3.setLayout(new BorderLayout(0, 0));
			pnPilhaBrindes = new JPanel();
			panel_3.add(pnPilhaBrindes, BorderLayout.NORTH);
			pnPilhaBrindes.setToolTipText("Pilha de Brindes");
			GridBagLayout gbl_pnPilhaBrindes = new GridBagLayout();
			gbl_pnPilhaBrindes.columnWidths = new int[] {0};
			gbl_pnPilhaBrindes.rowHeights = new int[] {0};
			gbl_pnPilhaBrindes.columnWeights = new double[]{1.0};
			gbl_pnPilhaBrindes.rowWeights = new double[]{0.0};
			pnPilhaBrindes.setLayout(gbl_pnPilhaBrindes);
			
			JPanel panel_6 = new JPanel();
			tabbedPane.addTab("Configura\u00E7\u00F5es", null, panel_6, null);
			panel_6.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane_3 = new JScrollPane();
			panel_6.add(scrollPane_3, BorderLayout.CENTER);
			
			JPanel panel_7 = new JPanel();
			scrollPane_3.setViewportView(panel_7);
			panel_7.setLayout(new BorderLayout(0, 0));
			
			pnConfiguracoes = new JPanel();
			panel_7.add(pnConfiguracoes, BorderLayout.NORTH);
			GridBagLayout gbl_pnConfiguracoes = new GridBagLayout();
			gbl_pnConfiguracoes.columnWidths = new int[]{0};
			gbl_pnConfiguracoes.rowHeights = new int[]{0};
			gbl_pnConfiguracoes.columnWeights = new double[]{Double.MIN_VALUE};
			gbl_pnConfiguracoes.rowWeights = new double[]{Double.MIN_VALUE};
			pnConfiguracoes.setLayout(gbl_pnConfiguracoes);
			
			JLabel lblNewLabel_4 = new JLabel("Totalizadores Restaurante   ");
			lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
			panel_2.add(lblNewLabel_4, BorderLayout.CENTER);			
			
			this.configs = Configuracoes.getInstancia();
			this.configs.setPnConfiguracoes(this.pnConfiguracoes);
			this.caixa = Caixa.getInstancia();
			this.caixa.setPnFilaCaixa(this.pnFilaCaixa);	
			this.caixa.setTfQtItens(tfQtItens);
			this.caixa.setTfVlrMed(tfVlrMed);
			this.caixa.setTfQtTotItens(tfQtTot);
			this.caixa.setTfVlTotal(tfVlTot);
			this.caixa.setLbInfo(lbInfo);
			this.brindes = Brindes.getInstancia();
			this.brindes.setPnPilhaBrindes(this.pnPilhaBrindes);
			this.funcsRest = FuncoesRestaurante.getInstancia();
			this.carregarAbaConfiguracoes();
		} catch (Exception e) {
			e.printStackTrace();
			showMessageDialog(null,e.getClass().getName() + "\n" + e.getMessage());
		}
	}
	
	/**
	 * Carrega as configuracoes, cujos componentes sao criados conforme os itens da classe Configuracoes
	 */
	private void carregarAbaConfiguracoes() {
		try {
			configs.carregarDados();
		} catch(Exception e) {
			e.printStackTrace();
			showMessageDialog(null,e.getClass().getName() + "\n" + e.getMessage());
		} 
	}

	/**
	 * Inicia/Encerra/Reinicia o atendimento do restaurante
	 */
	public void iniciarAtendimento() {
		try {			
			if (Variaveis.getInstancia().getStatusAtendimento() == 1) {
				int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja encerrar o atendimento atual?");
				if (resposta == 0) {
					this.btnNovoCliente.setEnabled(false);
					this.btnAtenderProximo.setEnabled(false);
					this.btnAbastecerPilha.setEnabled(false);
					this.btnDemo.setEnabled(false);
					this.btnIniciarAtendimento.setText("Iniciar Atendimento");
					this.btnDemo.setText("Iniciar Demo");
					mesas.getInstancia().excluirMesas(this.pnMesas);
					brindes.getInstancia().limparPilha();
					caixa.getInstancia().limparFila();
					caixa.getInstancia().limparValores();										
					Variaveis.getInstancia().setStatusAtendimento(0);
					Variaveis.getInstancia().setStatusDemo(0);
					this.lbInfo.setText("Atendimento encerrado");
				}
			} else {
				funcsRest.getInstancia().iniciarAtendimento(pnMesas);				
				brindes.getInstancia().iniciarPilha();
				this.btnNovoCliente.setEnabled(true);
				this.btnAtenderProximo.setEnabled(true);
				this.btnAbastecerPilha.setEnabled(true);
				this.btnDemo.setEnabled(true);
				this.btnIniciarAtendimento.setText("Encerrar Atendimento");
				this.btnDemo.setText("Iniciar Demo");
				Variaveis.getInstancia().setStatusAtendimento(1);
				Variaveis.getInstancia().setStatusDemo(0);
				this.lbInfo.setText("Atendimento Iniciado");
			}
		} catch(Exception e) {
			e.printStackTrace();
			showMessageDialog(null,e.getClass().getName() + "\n" + e.getMessage());
		}
	}	
	
	public void pararDemo() {
		if (Variaveis.getInstancia().getStatusDemo() == 0) {
			this.btnDemo.setText("Parar Demo");
			Variaveis.getInstancia().setStatusDemo(1);
		} else {
			this.btnDemo.setText("Continuar Demo");
			Variaveis.getInstancia().setStatusDemo(0);
		}
	}

}
