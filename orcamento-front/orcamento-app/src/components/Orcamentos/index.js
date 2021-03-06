import axios from "axios";
import React from "react";
import { AiFillDelete } from "react-icons/ai";
import { FaSearchDollar } from "react-icons/fa";

function Orcamentos(props) {
	const [orcamentos, setOrcamentos] = React.useState([]);
	const [clickBtnOrcamento, setClickBtnOrcamento] = React.useState(false);
	const [descricao, setDescricao] = React.useState("");
	const [mes, setMes] = React.useState("");
	const [ano, setAno] = React.useState("");
	const [valorTotalInformado, setValorTotalInformado] = React.useState("");
	const [itensOrcamentoCsv, setItensOrcamentoCsv] = React.useState("")

	React.useEffect(loadData, []);

	function loadData() {
		async function fetchData() {
			let dados = await axios
				.get("/orcamentos")
				.then((response) => response.data);
			setOrcamentos(dados);
		}
		fetchData();
	}

	function obterItens(){

		const linhas = itensOrcamentoCsv.split('\n')

		return	linhas.map((linha) => {
			const atributos = linha.split(';')
			return {
				origem: atributos[0],
				codigo: atributos[1],
				descricao: atributos[2],
				valorUnitario: atributos[3],
				unidade: atributos[4],
				quantidade: atributos[5],
				valorTotalInformado: atributos[6],
			}
		} )
	}

	function handleDeleteClick(idOrcamento) {
		axios.delete(`/orcamentos/${idOrcamento}`).then(() => {
			loadData();
		});
	}

	function handleSaveClick() {
		let dados = {
			orcamento: { descricao, mes, ano, valorTotalInformado },
			itens: obterItens(),
		};

		axios.post(`/orcamentos`, dados).then(() => {
			loadData();
			setDescricao("");
			setMes("");
			setAno("");
			setValorTotalInformado("");
			setClickBtnOrcamento(false);
		});
	}

	return (
		<>
			<table className="table table-dark table-hover table-striped">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Descrição</th>
						<th scope="col">Periodo</th>
						<th scope="col">Valor Total Informado</th>
						<th scope="col">Valor Total Calculado</th>
						<th scope="col">Diferença de preço</th>
						<th scope="col">Ações</th>
					</tr>
				</thead>
				<tbody>
					{orcamentos.map((orcamento) => (
						<tr key={orcamento.id}>
							<td>{orcamento.id}</td>
							<td>{orcamento.descricao}</td>
							<td>
								{orcamento.mes}/{orcamento.ano}
							</td>
							<td>R${orcamento.valorTotalInformado}</td>
							<td>R${orcamento.valorTotalCalculado}</td>
							<td>
								R${orcamento.diferencaCalculo}
							</td>
							<td>
								<AiFillDelete
									className="me-2 pointer"
									onClick={() => handleDeleteClick(orcamento.id)}
								/>
								<FaSearchDollar
									className="pointer"
									onClick={() => props.setOrcamentoDetalhado(orcamento)}
								/>
							</td>
						</tr>
					))}
				</tbody>
			</table>
			<button
				type="button"
				className="btn btn-outline-dark"
				onClick={() => setClickBtnOrcamento(true)}
			>
				Novo Orçamento
			</button>

			{clickBtnOrcamento && (
				<div className="modal fade show" style={{ display: "block" }}>
					<div className="modal-dialog">
						<div className="modal-content">
							<div className="input-group mb-3">
								<input
									type="text"
									className="form-control"
									placeholder="Descrição"
									aria-label="Descrição"
									value={descricao}
									onChange={(event) => setDescricao(event.target.value)}
								/>
							</div>
							<div className="input-group mb-3">
								<input
									type="text"
									className="form-control"
									placeholder="Mês"
									aria-label="Mês"
									value={mes}
									onChange={(event) => setMes(event.target.value)}
								/>
							</div>
							<div className="input-group mb-3">
								<input
									type="text"
									className="form-control"
									placeholder="Ano"
									aria-label="Ano"
									value={ano}
									onChange={(event) => setAno(event.target.value)}
								/>
							</div>
							<div className="input-group mb-3">
								<input
									type="text"
									className="form-control"
									placeholder="Valor total"
									aria-label="Valor total"
									value={valorTotalInformado}
									onChange={(event) =>
										setValorTotalInformado(event.target.value)
									}
								/>
							</div>
							<div className="input-group mb-3">
								<textarea
									className="form-control"
									placeholder="Itens orcamento (CSV)"
									aria-label="Itens orcamento (CSV)"
									value={itensOrcamentoCsv}
									onChange={(event) =>
										setItensOrcamentoCsv(event.target.value)}
								/>	
							</div>
							<button
								type="button"
								className="btn btn-outline-dark"
								onClick={() => setClickBtnOrcamento(false)}
							>
								Cancelar
							</button>
							<button
								type="button"
								className="btn btn-outline-dark"
								onClick={() => {
									handleSaveClick();
									setClickBtnOrcamento(false);
								}}
							>
								Salvar
							</button>
						</div>
					</div>
				</div>
			)}
		</>
	);
}

export default Orcamentos;