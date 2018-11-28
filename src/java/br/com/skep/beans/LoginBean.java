package br.com.skep.beans;

import br.com.skep.dao.UsuarioDAO;
import br.com.skep.entity.Usuario;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@Named
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{

    private boolean login;
    private Usuario user;
    private String nome;
    private String senha;
    private String hierarquia;
    private String mensagemAdvertencia;

    UsuarioDAO userDAO = new UsuarioDAO();
    // private String perfil;

    public String logar() {
        login = userDAO.logarUsuario(nome, senha);
        if (login) {
            user = new Usuario();
            return "/index.jsf?faces-redirect=true";
            //return "/restricted/index.jsf?faces-redirect=true"; ERRO-> não sobe para diretorio web
        }
        return null;
        
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        user = null;
        return "/radix?faces-redirect=true";
    }


    public String getHierarquia() {
        return hierarquia;
    }

    public void setHierarquia(String hierarquia) {
        this.hierarquia = hierarquia;
    }

    public String getMensagemAdvertencia() {
        return mensagemAdvertencia;
    }

    public void setMensagemAdvertencia(String mensagemAdvertencia) {
        this.mensagemAdvertencia = mensagemAdvertencia;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
 

}
    /*
    
    public void idetificarHierarquia() {
        Usuario user = new Usuario();
        List<Usuario> perfil = new ArrayList<>();
        perfil = userDAO.consultarPerfil(user.getId_usuario());
        perfil.add(user);
        switch (user.getPerfil()) {
            case "Administrador":
                //perfilAdministrador();
                hierarquia = " Boa noite " + user.getPerfil() + " " + user.getNome_usuario();
                break;
            case "Gerencia":
                // perfilGerencia();
                hierarquia = " Boa noite " + user.getPerfil() + " " + user.getNome_usuario();
                break;
            case "Manutencao":
                //perfilManutencao();
                hierarquia = " Boa noite " + user.getPerfil() + " " + user.getNome_usuario();
                break;
            case "Vendedor":
                // perfilVendedor();
                hierarquia = " Boa noite " + user.getPerfil() + " " + user.getNome_usuario();
                break;
            case "Entregador":
                // perfilEntregador();
                hierarquia = " Boa noite " + user.getPerfil() + " " + user.getNome_usuario();
                break;
        }
    }
    */
