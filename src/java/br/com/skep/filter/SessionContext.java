package br.com.skep.filter;

/*
Nessa classe ultiliza o padrão Singleton 
para evitar múltiplas instâncias no mesmo contexto da aplicação.
 */
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class SessionContext {

    private static SessionContext instance;

    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }

        return instance;
    }

    private SessionContext() {

    }
    /*
Este metodo é de extrema importância para funcionamento da nossa sessão, 
o seu objetivo é retornar um objeto ExternalContext através da requisição HTTP atual
e isso só é possível se uma requisição foi disparada.
Não pode ser chamado este método fora de uma requisição HTTP, 
    por fim mostrando a mensagem.
     */
    private ExternalContext currentExternalContext() {
        if (FacesContext.getCurrentInstance() == null) {
            throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
        } else {
            return FacesContext.getCurrentInstance().getExternalContext();
        }
    }

    public void encerrarSessao() {
        currentExternalContext().invalidateSession();
    }

    public Object getAttribute(String nome) {
        return currentExternalContext().getSessionMap().get(nome);
    }

    public void setAttribute(String nome, Object valor) {
        currentExternalContext().getSessionMap().put(nome, valor);
    }
}
