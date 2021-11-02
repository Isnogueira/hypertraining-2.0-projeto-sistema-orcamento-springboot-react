package br.com.mirante.orcamento.domain;

public class Produto {
	
	public String origem;
	public String codigo;
	public String decriçao;
	public String unidade;
	public Float valorInformado;
	
	
	
	public Produto(String origem, String codigo, String decriçao, String unidade, Float valorInformado) {
		super();
		this.origem = origem;
		this.codigo = codigo;
		this.decriçao = decriçao;
		this.unidade = unidade;
		this.valorInformado = valorInformado;
	}

	public String getDecriçao() {
		return decriçao;
	}

	public String getUnidade() {
		return unidade;
	}

	public Float getValorInformado() {
		return valorInformado;
	}

	public String getOrigem() {
		return origem;
	}

	public String getCodigo() {
		return codigo;
	}

}
