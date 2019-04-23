package br.com.skep.beans;

import br.com.skep.filter.SessionUtil;
import br.com.skep.dao.UsuarioDAO;
import br.com.skep.entity.Usuario;
import br.com.skep.filter.ControleDeAcesso;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@RequestScoped
public class LoginBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private boolean login;
    private Usuario user;
    private String nome;
    private String senha;
    private String hierarquia;
    private String mensagemAdvertencia;

    UsuarioDAO userDAO = new UsuarioDAO();
    ControleDeAcesso contr = new ControleDeAcesso();
    // private String perfil;

    public String logar() {
        login = userDAO.logarUsuario(nome, senha);
        if (login) {
            user = new Usuario();
            SessionUtil.setParam("USUARIOLogado",user);
            return "/index.jsf?faces-redirect=true";
            //return "/restricted/index.jsf?faces-redirect=true"; ERRO-> não sobe para diretorio web
        }else{
        return null;
        }
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        user = null;
        contr.destroy();
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


confirmarsenha


[code]
package modelo;

import java.io.;
import javax.persistence.;
import java.util.*;

@Entity
@Table(name=“usuario”)
public class Usuario {
@Id
@GeneratedValue
private Integer idUsuario;
@Column(name=“nomeUsuario”)
private String nome;
////Quer dizer sera unico
@org.hibernate.annotations.NaturalId
private String email;

private String senha;
private String idioma;

private Boolean estatus;

public Integer getIdUsuario() {
	return idUsuario;
}

public void setIdUsuario(Integer idUsuario) {
	this.idUsuario = idUsuario;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getSenha() {
	return senha;
}

public void setSenha(String senha) {
	this.senha = senha;
}

public String getIdioma() {
	return idioma;
}

public void setIdioma(String idioma) {
	this.idioma = idioma;
}

public Boolean getEstatus() {
	return estatus;
}

public void setEstatus(Boolean estatus) {
	this.estatus = estatus;
}






    */
