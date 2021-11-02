package br.com.mirante.orcamento.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ITEM_ORCAMENTO")
public class ItemOrcamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String origem;
	private String codigo;
	private String descricao;
	
	@Column(name="VALOR_UNITARIO")
	private float valorUnitario;
	private String unidade;
	private float quantidade;
	
	@Column(name="VALOR_TOTAL_INFORMADO")
	private Float valorTotalInformado;
	
	@ManyToOne
	@JoinColumn(name="ID_ORCAMENTO", insertable = true)
	@JsonIgnore
	private Orcamento orcamento;
	
	protected ItemOrcamento() {}
	
	
	public ItemOrcamento(String origem, String codigo, String descricao, float valorUnitario,
			String unidade, float quantidade, float valorTotalInformado) {
		this.origem = origem;
		this.codigo = codigo;
		this.descricao = descricao;
		this.valorUnitario = valorUnitario;
		this.unidade = unidade;
		this.quantidade = quantidade;
		this.valorTotalInformado = valorTotalInformado;
	}
	
		public ItemOrcamento(Integer id, String origem, String codigo, String descricao, float valorUnitario, String unidade,
			float quantidade, float valorTotalInformado, Orcamento orcamento) {
		this(origem, codigo, descricao, valorUnitario, unidade, quantidade, valorTotalInformado);
		this.id = id;
	}
	
	
	

	public Integer getId() {
			return id;
		}


		public void setId(Integer id) {
			this.id = id;
		}


		public String getOrigem() {
			return origem;
		}


		public void setOrigem(String origem) {
			this.origem = origem;
		}


		public String getCodigo() {
			return codigo;
		}


		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}


		public String getDescricao() {
			return descricao;
		}


		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}


		public float getValorUnitario() {
			return valorUnitario;
		}


		public void setValorUnitario(float valorUnitario) {
			this.valorUnitario = valorUnitario;
		}


		public String getUnidade() {
			return unidade;
		}


		public void setUnidade(String unidade) {
			this.unidade = unidade;
		}


		public float getQuantidade() {
			return quantidade;
		}


		public void setQuantidade(float quantidade) {
			this.quantidade = quantidade;
		}


		public Float getValorTotalInformado() {
			return valorTotalInformado;
		}


		public void setValorTotalInformado(Float valorTotalInformado) {
			this.valorTotalInformado = valorTotalInformado;
		}


		public Orcamento getOrcamento() {
			return orcamento;
		}


		public void setOrcamento(Orcamento orcamento) {
			this.orcamento = orcamento;
		}


	public Float getValorTotalCalculado() {
		return this.valorUnitario * this.quantidade;
	}

	
	public boolean possuiInconsistencia() {
		
		return valorUnitario * quantidade != valorTotalInformado;
	}
}
