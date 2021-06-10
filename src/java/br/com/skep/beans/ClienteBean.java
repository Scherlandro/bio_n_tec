package br.com.skep.beans;

import br.com.skep.dao.ClienteDAO;
import br.com.skep.entity.Cliente;
import br.com.skep.entity.Endereco;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
@SessionScoped
public class ClienteBean implements Serializable{

    private List<Cliente> clientList;
    private Cliente clientEntity = new Cliente();
    private final ClienteDAO clientDAO = new ClienteDAO();
    private boolean edit;
    private BuscaPorCepBean busCep = new BuscaPorCepBean();

    @PostConstruct
    public void init() {
        clientList = clientDAO.listarTodosClientes();
    }

    public void editar(Cliente u) {
        clientEntity = u;
        edit = true;
    }

    public void adicionar() {
        //  if (verificadorCpfCnpj()) {
        clientDAO.salvar(clientEntity);
        //  }
        clientEntity = new Cliente();
        edit = false;
    }

    public void verificaReplica() {
        boolean jaExiste = true;
       String buscaCad = clientEntity.getPessoa();       
        switch (buscaCad) {
            case "":
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Digite um valor para pessoa ", clientEntity.getPessoa()));
                break;
            case "Física":
                jaExiste = clientDAO.verificarReplicasCPF(clientEntity.getCpf());
              //  break;
            case "Jurídica":
                jaExiste = clientDAO.verificarReplicasCNPJ(clientEntity.getCnpj());
               // break;
            default:
                if (jaExiste == true) {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Já existe cadastro com essa pessoa", clientEntity.getPessoa()));
   } else {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não existe cadastro com essa pessoa", clientEntity.getPessoa()));
               }
        }

    }

    public void updateEndereco() {
        Endereco endrEty = new Endereco();
        String cep = clientEntity.getCep_cliente();
        // endrDAO.salvarEndereco(cep.);
    }

    public void remover(Cliente u) {
        clientEntity = u;
        clientDAO.excluirCliente(clientEntity.getId_cliente());
        clientList.remove(u);
        //clientEntity = new Cliente();
    }

    public void limpaTela() {
        this.clientEntity = new Cliente();
        this.clientList.clear();
    }

    public void execute(String cep, String tipoLogradouro, String logradouro, String bairro, String cidade, String uf) {
        clientEntity.setCep_cliente(cep);
        clientEntity.setTipoLogradouro(tipoLogradouro);
        clientEntity.setLogradouro(logradouro);
        clientEntity.setBairro(bairro);
        clientEntity.setCidade(cidade);
        clientEntity.setEstado(uf);
    }

    public Cliente getClientEntity() {
        return clientEntity;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public void setClientEntity(Cliente clientEntity) {
        this.clientEntity = clientEntity;
    }

    public List<Cliente> getClientList() {
        return clientList;
    }

    public void setClientList(List<Cliente> clientList) {
        this.clientList = clientList;
    }

    public BuscaPorCepBean getBusCep() {
        return busCep;
    }

    public void setBusCep(BuscaPorCepBean busCep) {
        this.busCep = busCep;
    }

}
