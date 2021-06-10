package br.com.skep.averidores;

import java.time.Instant;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;

/**
 *
 * @author Scherlandro
 */
@ManagedBean
@Named
@RequestScoped
public class Mensagens {

    private String fusohr;
    private String mensagem;
    private Integer horaAtual = Date.from(Instant.now()).getHours();
    
    public void cumprimentar() {
        if (horaAtual >= 0 && horaAtual <= 11) {
            fusohr = " Um Ótimo dia ";
        }
        if (horaAtual >= 12 && horaAtual <= 17) {
            fusohr = " Uma Ótima Tarde ";
        }
        if (horaAtual >= 18 && horaAtual <= 23) {
            fusohr = " Uma Ótima Noite ";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, fusohr,  "e seja muito bem vindo!"));
    }
   /*
    public void contarLongin() {
    Integer contar= 0, logs = 0 ;
    String totalLogs;
        do {            
 logs = ++contar;
        } while (true);
   // totalLogs = Integer.parseInt(logs);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, String.valueOf(logs),  "Quantidade de logs!" ));
    }
*/
    public void msgAdvertir() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Operação", "realizada com sucesso"));
    }
     public void msgDeGravacao() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação", "realizada com sucesso"));
    }
    public void msgDeErro(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario ou senha invalidos!",""));
        
    }
    public void msgDeExcluir(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Exclusão realizada com sucesso!",""));
        
    }
    public void msgDeAtualizar(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atualizado com sucesso!",""));
        
    }

    /*
    public void msgIdentifiarUsuario() {
        AutenticadorBean nome = new AutenticadorBean();
        String n = nome.getNome();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, n, "Seja bem vindo!"));
    }
    
    */

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
        public void handleClose(CloseEvent event) {
        addMessage(event.getComponent().getId() + " closed", "So you don't like nature?");
    }
     
    public void handleMove(MoveEvent event) {
        addMessage(event.getComponent().getId() + " moved", "Left: " + event.getLeft() + ", Top: " + event.getTop());
    }
     
    public void destroyWorld() {
        addMessage("System Error", "Please try again later.");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
  
    

}
