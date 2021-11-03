package br.fag.restaurante.classes;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;


/**
 * Classe extendida do JTextField padrao para selecionar o texto no textField ao receber o foco, util em entrada de dados
 */
public class JTextFieldSelOnFocus extends JTextField{
	
	public JTextFieldSelOnFocus(String string) {
		super(string);
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            	JTextFieldSelOnFocus.this.select(0, getText().length());
            }

            @Override
            public void focusLost(FocusEvent e) {
            	JTextFieldSelOnFocus.this.select(0, 0);
            }
        });
    }
}
