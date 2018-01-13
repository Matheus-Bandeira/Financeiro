package com.br.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.br.financeiro.model.Lancamento;
import com.br.financeiro.repository.Lancamentos;
import com.br.financeiro.service.CadastroLancamentos;
import com.br.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class ConsultaLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Lancamento> lancamentos;

	private Lancamento lancamentoSelecionado;

	@Inject
	private CadastroLancamentos cadastro;

	@Inject
	private Lancamentos lancamentoRepository;

	public void consultar() {
		this.lancamentos = lancamentoRepository.todos();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public Lancamento getLancamentoSelecionado() {
		return lancamentoSelecionado;
	}

	public void setLancamentoSelecionado(Lancamento lancamentoSelecionado) {
		this.lancamentoSelecionado = lancamentoSelecionado;
	}

	public void excluir() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			this.cadastro.excluir(this.lancamentoSelecionado);
			this.consultar();
			context.addMessage(null, new FacesMessage("Lançamento excluído com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
}
