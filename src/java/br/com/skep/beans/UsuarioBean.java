
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
    
    private Usuario userEntity = new Usuario();
    private List<Usuario> userList = new ArrayList<>();
    private final UsuarioDAO userDAO = new UsuarioDAO();
    
    public void adicionar(){
       // userList.add(userEntity); para não repetir registro ao alterar
        userDAO.salvar(userEntity);
        userEntity = new Usuario();
        //userList.clear();
    }    
    
    public void listarUsurio(){
        userList = userDAO.listarTodosUsuarios();
    }
    public void editar(Usuario u){
        userEntity = u;
    }    
    
    public void remover(Usuario u){
        userEntity = u;
        userDAO.excluirUsuario(userEntity.getId_usuario());
        userEntity = new Usuario();
        userList.clear();
    }
    
    public Usuario getUserEntity() {
        return userEntity;
    }
    
    public void setUserEntity(Usuario userEntity) {
        this.userEntity = userEntity;
    }

    public List<Usuario> getUserList() {
        return userList;
    }

    public void setUserList(List<Usuario> userList) {
        this.userList = userList;
    }

}
