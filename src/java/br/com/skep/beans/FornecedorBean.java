package br.com.skep.beans;

import br.com.skep.dao.FornecedorDAO;
import br.com.skep.entity.Fornecedor;
import br.com.skep.entity.Endereco;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class FornecedorBean {

    private Fornecedor fornecedEntity = new Fornecedor();

    private List<Fornecedor> fornecedList = new ArrayList<>();
    private final FornecedorDAO fornecedDAO = new FornecedorDAO();
    private BuscaPorCepBean busCep = new BuscaPorCepBean();

    public void adicionar() {        
        fornecedDAO.salvar(fornecedEntity);
        fornecedEntity = new Fornecedor();
    }

    public void verificaReplica() {
      boolean jaExiste = true;
       String buscaCad = fornecedEntity.getPessoa();       
        switch (buscaCad) {
            case "":
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite um valor para pessoa ", fornecedEntity.getPessoa()));
                break;
            case "Física":
                jaExiste = fornecedDAO.verificarReplicasCPF(fornecedEntity.getCpf());
              //  break;
            case "Jurídica":
                jaExiste = fornecedDAO.verificarReplicasCNPJ(fornecedEntity.getCnpj());
               // break;
            default:
                if (jaExiste == true) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Já existe cadastro com essa pessoa", fornecedEntity.getPessoa()));
   } else {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não existe cadastro com essa pessoa", fornecedEntity.getPessoa()));
               }
        }

    }

    public void updateEndereco() {
        Endereco endrEty = new Endereco();
        String cep = fornecedEntity.getCep_fornecedor();
        // endrDAO.salvarEndereco(cep.);
    }

    public void listarFornecedores() {
        fornecedList = fornecedDAO.listarTodosFornecedores();
    }

    public void editar(Fornecedor u) {
        fornecedEntity = u;
    }

    public void remover(Fornecedor u) {
        fornecedEntity = u;
        fornecedDAO.excluirFornecedor(fornecedEntity.getId_fornecedor());
        fornecedEntity = new Fornecedor();
    }

    public void limpaTela() {
        this.fornecedEntity = new Fornecedor();
        this.fornecedList.clear();
    }

    public void execute(String cep, String tipoLogradouro, String logradouro, String bairro, String cidade, String uf) {
        fornecedEntity.setCep_fornecedor(cep);
        fornecedEntity.setTipoLogradouro(tipoLogradouro);
        fornecedEntity.setLogradouro(logradouro);
        fornecedEntity.setBairro(bairro);
        fornecedEntity.setCidade(cidade);
        fornecedEntity.setEstado(uf);
    }

    public Fornecedor getFornecedEntity() {
        return fornecedEntity;
    }

    public void setFornecedEntity(Fornecedor fornecedEntity) {
        this.fornecedEntity = fornecedEntity;
    }

    public List<Fornecedor> getFornecedorList() {
        fornecedList = fornecedDAO.listarTodosFornecedores();
        return fornecedList;
    }

    public void setFornecedorList(List<Fornecedor> fornecedList) {
        this.fornecedList = fornecedList;
    }

    public BuscaPorCepBean getBusCep() {
        return busCep;
    }

    public void setBusCep(BuscaPorCepBean busCep) {
        this.busCep = busCep;
    }

}
