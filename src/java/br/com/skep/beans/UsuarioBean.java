
package br.com.skep.beans;

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
    private final UsuarioDAO userDAO = new UsuarioDAO();
    
    public void adicionar(){
       // usuarios.add(user); para não repetir registro ao alterar
        userDAO.salvar(user);
        user = new Usuario();
        //usuarios.clear();
    }
    
    public void listarUsurio(){
        usuarios = userDAO.listarTodosUsuarios();
    }
    public void editar(Usuario u){
        user = u;
    }
    
    public void remover(Usuario u){
        user = u;
        userDAO.excluirUsuario(user.getId_usuario());
        user = new Usuario();
        
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

}
