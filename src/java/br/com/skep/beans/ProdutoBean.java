
package br.com.skep.beans;

import br.com.skep.dao.ProdutoDAO;
import br.com.skep.entity.Produto;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

@ManagedBean
@RequestScoped
public class ProdutoBean {
    private Produto prodEntity = new Produto();
    private final ProdutoDAO prodDAO = new ProdutoDAO();
    private List<Produto> prodList;
    private DualListModel<Produto> dualListP;

     public void adicionar(){
        prodDAO.salvar(prodEntity);
        prodEntity = new Produto();
    } 
     public void listarUsurio(){
        prodList = prodDAO.listarTodosProduto();
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
        dualListP.setSource(getProdList());
        return dualListP;
    }

    public void setDualListP(DualListModel<Produto> dualListP) {
        this.dualListP = dualListP;
    }

    public List<Produto> getProdList() {
       prodList = prodDAO.listarTodosProduto();
        return prodList;
    }

    public Produto getProdEntity() {
        return prodEntity;
    }

    public void setProdEntity(Produto prodEntity) {
        this.prodEntity = prodEntity;
    }

    
    
    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((Produto) item).getNome_produto()).append("<br />");
        }
         
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  
     
    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }
     
    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }
     
    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }
    
    
    public void showResult(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Selecionados: " + dualListP.getTarget().size()+" Restante: " + dualListP.getSource(), null));
    } 
        
}
