package br.com.skep.averidores;

import br.com.skep.beans.LoginBean;
import br.com.skep.entity.Usuario;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author Scherlandro
 */
@ManagedBean
public class Mensagens {

    private String fusohr;
    private String mensagem;
    private Integer horaAtual = Date.from(Instant.now()).getHours();

    public void cumprimentar() {
        if (horaAtual >= 0 && horaAtual <= 11) {
            fusohr = " Bom dia ";
        }
        if (horaAtual >= 12 && horaAtual <= 17) {
            fusohr = " Boa Tarde ";
        }
        if (horaAtual >= 18 && horaAtual <= 23) {
            fusohr = " Boa Noite ";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, fusohr, "Seja bem vindo!"));
    }

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

    /*
    public void msgIdentifiarUsuario() {
        LoginBean nome = new LoginBean();
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

}
