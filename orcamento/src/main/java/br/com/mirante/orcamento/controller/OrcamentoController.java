package br.com.mirante.orcamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mirante.orcamento.domain.ItemOrcamento;
import br.com.mirante.orcamento.domain.Orcamento;
import br.com.mirante.orcamento.service.AtualizarOrcamentoService;
import br.com.mirante.orcamento.service.CadastrarOrcamentoService;
import br.com.mirante.orcamento.service.DetalharOrcamentoService;
import br.com.mirante.orcamento.service.ExcluirOrcamentoService;
import br.com.mirante.orcamento.service.ListarOrcamentosService;
import br.com.mirante.orcamento.service.RecuperarInconsistenciaService;

@RequestMapping("/orcamentos")
@RestController
public class OrcamentoController {
	
	@Autowired
	private ListarOrcamentosService listarService;
	
	@Autowired
	private DetalharOrcamentoService detalharService;
	
	@Autowired
	private CadastrarOrcamentoService cadastrarService;
	
	@Autowired
	private ExcluirOrcamentoService excluirService;
	
	@Autowired
	private AtualizarOrcamentoService atualizarService;
	
	@Autowired
	private RecuperarInconsistenciaService recuperarInconsistenciaService;
	

	@GetMapping
	public List<Orcamento> listarOrcamentos(){
		
		return listarService.listarOrcamentos();
		
	}
	
	@GetMapping("/{id}")
	public Orcamento recuperarOrcamento(@PathVariable Integer id) {
		
		return detalharService.recuperar(id);
	}
	
	@GetMapping("/{id}/inconsistencias")
	public List<String> recuperarInconsistencias(@PathVariable Integer id) {
		
		return recuperarInconsistenciaService.recuperarInconsistencias(id);
	}
	
	@PostMapping
	public Orcamento salvarOrcamento(@RequestBody CadastrarOrcamentoRequest requisicao) {
		
		return cadastrarService.cadastrar(requisicao.orcamento, requisicao.itens);
		
	}
	
	@DeleteMapping("/{id}")
	public void excluirOrcamento(@PathVariable Integer id) {
		
		excluirService.excluir(id);
	}
	
	@PutMapping("/{id}")
	
	public void atualizarOrcamento(@PathVariable Integer id, @RequestBody Orcamento orcamento){
		
		atualizarService.atualizar(id, orcamento);
		
	}


}

 class CadastrarOrcamentoRequest {
	
	Orcamento orcamento;
	List<ItemOrcamento> itens;
	
	public Orcamento getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	public List<ItemOrcamento> getItens() {
		return itens;
	}
	public void setItens(List<ItemOrcamento> itens) {
		this.itens = itens;
	}
	

}
