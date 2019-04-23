package br.com.skep.beans;

import br.com.skep.dao.FuncionarioDAO;
import br.com.skep.entity.Funcionario;
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
public class FuncionarioBean {

    private final FuncionarioDAO funcDAO = new FuncionarioDAO();
    private List<Funcionario> funcList;

    private DualListModel<Funcionario> dualListF;

    private void selecionarFuncionario() {
        /*
        FuncionarioDAO funcDAO = new FuncionarioDAO();
        List<Funcionario> funcList ;
        List<Funcionario> funcList2 = null;
        funcList = funcDAO.listarTodosFuncionario();
       
        List<String> funcList = new ArrayList<String>();
        List<String> funcList2 = new ArrayList<String>();
        funcList.add("Chico Antoe");
        funcList.add("Antoi Sisso");
        funcList.add("Virgulino");
        funcList.add("Zé Cueca");
        funcList.add("Raimudão");

        dualListF = new DualListModel<>(funcList, funcList2);
         */

    }
 /*
    public void selecionarFuncionarios() {
        funcList = funcDAO.listarTodosFuncionario();
        funcList2 = funcDAO.listarTodosFuncionario();
        dualListF = new DualListModel<>(funcList, funcList2);
        /*
        for (Funcionario f : funcList) {
            if (f.getNome_funcionario().startsWith("Scherlandro")) {
                funcList.add(f);
            }
        }
        dualListF = new DualListModel<>(funcList, funcList2);      
         */
  /*  }

    public void mostrarFuncionariosSelecionadas() {
        for (Funcionario func : dualListF.getTarget()) {
            System.out.println("Esta funcionario é: " + func);
        }
    }
*/
    public DualListModel<Funcionario> getDualListF() {
        if(dualListF == null)
        dualListF = new DualListModel<Funcionario>();    
        dualListF.setSource(getFuncList());
        return dualListF;
    }

    public void setDualListF(DualListModel<Funcionario> dualListF) {
        this.dualListF = dualListF;
    }

    public List<Funcionario> getFuncList() {
       funcList = funcDAO.listarTodosFuncionario();
        return funcList;
    }
    /*

    public void setFuncList(List<Funcionario> funcList) {
        this.funcList = funcList;
    }

    public List<Funcionario> getFuncList2() {
        return funcList2;
    }

    public void setFuncList2(List<Funcionario> funcList2) {
        this.funcList2 = funcList2;
    }
    */
    
    
    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((Funcionario) item).getNome_funcionario()).append("<br />");
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
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Selecionados: " + dualListF.getTarget().size()+" Restante: " + dualListF.getSource(), null));
        
    }

}
