package br.com.skep.beans;

import br.com.skep.dao.FuncionarioDAO;
import br.com.skep.entity.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@ViewScoped
public class FuncionarioBean {

    private final FuncionarioDAO funcDAO = new FuncionarioDAO();
    private List<Funcionario> funcList = new ArrayList<Funcionario>();
    private List<SelectItem> listFunc ; 
    private Funcionario funcEntity = new Funcionario();

   
      @PostConstruct
    public void init() {
       funcList = funcDAO.listarTodosFuncionario();
    }    
    
    public void adicionar() {
        funcDAO.salvar(funcEntity);
        funcEntity = new Funcionario();
    }
       public void editar(Funcionario u) {
        funcEntity = u;
    }

    public void remover(Funcionario u) {
        funcEntity = u;
        funcDAO.excluirFuncionario(funcEntity.getId_funcionario());
        funcEntity = new Funcionario();
    }


    public List<Funcionario> getFuncList() {
        return funcList;
    }
  
    public void execute(String cep,String tipoLogradouro,String logradouro, String bairro, String cidade, String uf ) {
        funcEntity.setCep_funcionario(cep);
        funcEntity.setTipoLogradouro(tipoLogradouro);
        funcEntity.setLogradouro(logradouro);
        funcEntity.setBairro(bairro);
        funcEntity.setCidade(cidade);
        funcEntity.setUf(uf);
}
        
   
  
    public Funcionario getFuncEntity() {
        return funcEntity;
    }

    public void setFuncEntity(Funcionario funcEntity) {
        this.funcEntity = funcEntity;
    }
    
      

}
