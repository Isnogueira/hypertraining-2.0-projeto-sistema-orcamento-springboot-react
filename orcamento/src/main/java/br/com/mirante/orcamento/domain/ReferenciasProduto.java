package br.com.mirante.orcamento.domain;


public class ReferenciasProduto{
	
	private String codigo;
	private Integer mes;
	private Integer ano;
	private Float valor;
	
	private Produto produto;
	
	protected ReferenciasProduto() {}

	public ReferenciasProduto(String codigo, Integer mes, Integer ano, Float valor, Produto produto) {
		super();
		this.codigo = codigo;
		this.mes = mes;
		this.ano = ano;
		this.valor = valor;
		this.produto = produto;
	}


	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}


	public Float getValor() {
		return valor;
	}


	public void setValor(Float valor) {
		this.valor = valor;
	}


	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}