
package br.com.skep.beans;

import br.com.skep.dao.UsuarioDAO;
import br.com.skep.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class UsuarioBean {
    
  private Usuario userEntity = new Usuario();
    private List<Usuario> userList = new ArrayList<>(); 
    private final UsuarioDAO userDAO = new UsuarioDAO();
    
   
    @PostConstruct
       public void init() {
        userList = userDAO.listarTodosUsuarios();
    }
     
       public void adicionar(){
       userList.add(userEntity);
       userEntity = new Usuario();
   }
       public void gravar(){
       userDAO.salvar(userEntity);
       userEntity = new Usuario();
   }
    

    public void editar(Usuario us) {
        userEntity = us;
    }
    
      public List<Usuario> completeText(String query) {
//https://pt.stackoverflow.com/questions/105354/alimentar-array-primefaces
        userList = new UsuarioDAO().listarTodosUsuarios();
        List<Usuario> results =  new ArrayList<>();
        for(Usuario us : userList) {
            if (us.getNomeUsuario().startsWith(query)) {
                results.add(us);
            }
        }
        return results;
    }

    public void listar() {
        userList = userDAO.listarTodosUsuarios();
        userEntity = new Usuario();
    }
    

    public void remover(Usuario us) {
        userEntity = us;
        userDAO.remover(us);
        userEntity = new Usuario();
    }

    public void limpar() {
        userEntity = new Usuario();
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
