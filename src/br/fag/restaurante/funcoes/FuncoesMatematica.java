package br.fag.restaurante.funcoes;


/**
 * Classe de utilidades genericas de matematica (Singleton)
 */
public class FuncoesMatematica {
	private static FuncoesMatematica uFuncoesMatematica = null; //singleton
	
	/**
	 * Constructor - SingleTon (private)
	 */
	private FuncoesMatematica() {
		
	}
	
	/**
	 * GetInstancia - Singleton e synchronized - obtem a instancia unica da classe
	 * @return {FuncoesMatematica} - a instancia unica da classe 
	 */
	public static synchronized FuncoesMatematica getInstancia() {
		if (uFuncoesMatematica == null) {
			uFuncoesMatematica = new FuncoesMatematica();
		}
		return uFuncoesMatematica;
	}
	
	/**
	 * Gera um inteiro randomico entre o minimo e maximo dos parametros
	 * @param min
	 * @param max
	 * @return
	 */
	public Integer rand(int min, int max) {
		try {
			return min + (int)(Math.random() * ((max - min) + 1));
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
