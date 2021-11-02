package br.com.mirante.orcamento.tests;

import java.time.LocalDateTime;

public class Conta {
	
	private float saldo;
	String numero;
	String agencia;
	//Cliente cliente;
	LocalDateTime dataAbertura;
	
	public Conta(float saldo, String numero, String agencia, LocalDateTime dataAbertura) {

		this.saldo = saldo;
		this.numero = numero;
		this.agencia = agencia;
		this.dataAbertura = LocalDateTime.now();
	}

	public float getSaldo() {
		return saldo;
	}
	
	boolean sacar(float valor) {
		
		if(valor <= saldo) {
			saldo -= valor;
			return true;
		}else {
			
			System.out.println("N�o � poss�vel sacar.");
		}
		return false;
		
	}
	
	void depositar(float valor) {
		System.out.println("Depositando R$" + valor);
		saldo += valor;
	}

}
