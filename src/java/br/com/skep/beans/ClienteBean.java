package br.com.skep.beans;

import br.com.skep.dao.ClienteDAO;
import br.com.skep.entity.Cliente;
import br.com.skep.entity.Endereco;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class ClienteBean {

    private Cliente clientEntity = new Cliente();

    private List<Cliente> clientList = new ArrayList<>();
    private final ClienteDAO clientDAO = new ClienteDAO();
    private BuscaPorCepBean busCep = new BuscaPorCepBean();

    public void adicionar() {
        // clieList.add(clieEntity); para não repetir registro ao alterar
        clientDAO.salvar(clientEntity);
        // endrDAO.salvarEndereco(endcEntity);
        clientEntity = new Cliente();
        //clientList.clear();
    }

    public void updateEndereco() {
        Endereco endrEty = new Endereco();
        String cep = clientEntity.getCep();
        // endrDAO.salvarEndereco(cep.);
    }

    public void listarClientes() {
        clientList = clientDAO.listarTodosClientes();
    }

    public void editar(Cliente u) {
        clientEntity = u;
    }

    public void remover(Cliente u) {
        clientEntity = u;
        clientDAO.excluirCliente(clientEntity.getId_cliente());
        clientEntity = new Cliente();
    }

    public void execute(String cep,String tipoLogradouro,String logradouro, String bairro, String cidade, String uf ) {
        clientEntity.setCep(cep);
        clientEntity.setTipoLogradouro(tipoLogradouro);
        clientEntity.setLogradouro(logradouro);
        clientEntity.setBairro(bairro);
        clientEntity.setCidade(cidade);
        clientEntity.setEstado(uf);
}

public Cliente getClientEntity() {
        return clientEntity;
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
