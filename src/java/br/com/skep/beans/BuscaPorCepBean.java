package br.com.skep.beans;

import br.com.skep.averidores.CepWebService;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbService")
@RequestScoped
public class BuscaPorCepBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String tipoLogradouro;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String cepResult = null;
    
    public void encontraCEP() {
        CepWebService cepWebService = new CepWebService(getCepResult());
 
        if (cepWebService.getResultadoWebServ() == 1) {
            setTipoLogradouro(cepWebService.getTipoLogradouroWebServ());
            setLogradouro(cepWebService.getLogradouroWebServ());
            setCidade(cepWebService.getCidadeWebServ());
            setBairro(cepWebService.getBairroWebServ());
            setEstado(cepWebService.getEstadoWebServ());
        } else {
 
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Servidor não está respondendo",
                            "Servidor não está respondendo"));
        }
    
    
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCepResult() {
        return cepResult;
    }

    public void setCepResult(String cepResult) {
        this.cepResult = cepResult;
    }

  
    
}
    
    
    
    /*
    private Endereco enderecoEntity = new Endereco();
    private List<Endereco> enderecoList = new ArrayList<>();
    private final EnderecoDAO enderecoDAO = new EnderecoDAO();
    
    public void atualizarEndereco() {
        enderecoDAO.salvarEndereco(enderecoEntity);
        enderecoEntity = new Endereco();
    }
   

    public void listarEnderecos() {
        enderecoList = enderecoDAO.listarEndereco(enderecoEntity.getCep());
    }
    public void listarEndereco(String endr) {
       enderecoDAO.listarEndereco(endr);
    }
   
    public void editar(Endereco u) {
        enderecoEntity = u;
    }

    public void remover(Endereco u) {
        enderecoEntity = u;
        //enderecoDAO.excluirEndereco(enderecoEntity.);
        enderecoEntity = new Endereco();
    }

    public Endereco getEnderecoEntity() {
        return enderecoEntity;
    }

    public void setEnderecoEntity(Endereco enderecoEntity) {
        this.enderecoEntity = enderecoEntity;
    }

    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
    }
    
    
    */
