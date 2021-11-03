package br.fag.restaurante.classes;

import java.lang.reflect.Method;


/**
 * Classe que disponibiliza uma simplificacao para executar Threads paralelas
 */
public class TarefaAssincrona extends Thread{
	private Object objetoExecutar = null;
	private Method metodoExecutar = null;
	private Object[] paramsExecutar = null;
	
	/**
	 * Methodo necessario sobrescrito que chama o methodo a ser executado na thread
	 */
	@Override
	public void run() {
		try {
			//System.out.println("Tarefa Assincrona iniciada");
			if (objetoExecutar != null && metodoExecutar != null) {
				if (paramsExecutar != null) {
					metodoExecutar.invoke(objetoExecutar, paramsExecutar);
				} else {
					metodoExecutar.invoke(objetoExecutar);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*GETTERS AND SETTERS*/
	public Method getMetodoExecutar() {
		return metodoExecutar;
	}
	public void setMetodoExecutar(Method metodoExecutar) {
		this.metodoExecutar = metodoExecutar;
	}	
	public Object getObjetoExecutar() {
		return objetoExecutar;
	}
	public void setObjetoExecutar(Object objetoExecutar) {
		this.objetoExecutar = objetoExecutar;
	}
	public Object[] getParamsExecutar() {
		return paramsExecutar;
	}
	public void setParamsExecutar(Object... paramsExecutar) {
		this.paramsExecutar = paramsExecutar;
	}
	
	

}
