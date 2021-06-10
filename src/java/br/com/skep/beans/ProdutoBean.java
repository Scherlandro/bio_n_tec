
package br.com.skep.beans;

import br.com.skep.dao.ProdutoDAO;
import br.com.skep.entity.Produto;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.DualListModel;

@ManagedBean
@RequestScoped
public class ProdutoBean {
    private Produto prodEntity = new Produto();
    private final ProdutoDAO prodDAO = new ProdutoDAO();
    private List<Produto> prodList;
    private DualListModel<Produto> dualListP;

     @PostConstruct
     public void listarProdutos(){
        prodList = prodDAO.listarTodosProduto();
    }
     
     public void adicionar(){
        prodDAO.salvar(prodEntity);
        prodEntity = new Produto();
    } 
      
     public void editar(Produto p){
        prodEntity = p;
    }
       public void remover(Produto p){
        prodEntity = p;
        prodDAO.excluirProduto(prodEntity.getId_produto());
        prodEntity = new Produto ();
        prodList.clear();
    }
    
    public DualListModel<Produto> getDualListP() {
        if(dualListP == null)
        dualListP = new DualListModel<Produto>();    
        dualListP.setSource(obterListProd());
        return dualListP;
    }

     public List<Produto> obterListProd() {
       prodList = prodDAO.listarTodosProduto();
        return prodList;
    }
    
    public List<Produto> getProdList() {
      // prodList = prodDAO.listarTodosProduto();
        return prodList;
    }

    public void setDualListP(DualListModel<Produto> dualListP) {
        this.dualListP = dualListP;
    }
    
    public Produto getProdEntity() {
        return prodEntity;
    }

    public void setProdEntity(Produto prodEntity) {
        this.prodEntity = prodEntity;
    }
    
}
