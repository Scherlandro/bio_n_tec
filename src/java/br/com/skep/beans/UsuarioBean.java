
package br.com.skep.beans;

import br.com.skep.dao.UsuarioDAO;
import br.com.skep.entity.Usuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class UsuarioBean {
    
    private Usuario user = new Usuario();
    private List<Usuario> usuarios = new ArrayList<>();
    private UsuarioDAO userDAO = new UsuarioDAO();
    private String fusohr;
    private String mensagem;
    
    public void adicionar(){
       // usuarios.add(user); para não repetir registro ao alterar
        userDAO.salvar(user);
        user = new Usuario();
    }
    
    public void listarUsurio(){
        usuarios = userDAO.listarTodosUsuarios();
    }
    public void editar(Usuario u){
        user = u;
    }
    
    public void cumprimentar(){
         if (Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM){
        fusohr = " Good Morning"; 
        } else {
       fusohr =  " Good AfterNoon"; 
	}
        mensagem = fusohr;// + user.getPerfil()+ " " + user.getNome_usuario();
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
}
