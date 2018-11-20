
package br.com.skep.usuario;

import br.com.skep.dao.UsuarioDAO;
import br.com.skep.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean
public class UsuarioBean {
    
    private Usuario user = new Usuario();
    private List<Usuario> usuarios = new ArrayList<>();
    private UsuarioDAO userDAO = new UsuarioDAO();
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
        mensagem = " Boa noite " + user.getPerfil()+ " " + user.getNome_usuario();
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
