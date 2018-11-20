package br.com.skep.dao;

import br.com.skep.callBD.AcessoMySql;
import br.com.skep.entity.Usuario;
import com.sun.istack.internal.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UsuarioDAO {

    PreparedStatement pstm;
    ResultSet rs;
    String consultarTodosUsuario = "Select *From Usuario;";
    String consultarUsuario = "SELECT *FROM Usuario WHERE nome_usuario LIKE ?";
    String cadastrarUsuario = "Insert into Usuario (nome_usuario, email, senha, perfil)  Values (?, ?,?,?)";
    String alteraUsuario = "UPDATE Usuario Set nome_usuario = ?, email = ?, senha = ?, perfil = ? Where id_usuario = ?";
    String excluirUsuario = "Delete From Usuario Where id_usuario = ?";
    String logando = "SELECT *FROM Usuario WHERE nome_usuario LIKE ? and senha LIKE ?";
    String confirmarSenha = "SELECT *FROM Usuario WHERE senha LIKE ?";

    public void logarUsuario(Usuario usuario) {
        try {
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(logando);
            pstm.setString(1, usuario.getNome_usuario());
            pstm.setString(2, usuario.getSenha());
            pstm.setString(3, usuario.getPerfil());
            rs = pstm.executeQuery();
            if (rs.next()) {
                //  perfil = rs.getString(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvar(Usuario usuario) {
        try {
            AcessoMySql mysql = new AcessoMySql();
            if(usuario.getId_usuario() == null){
            pstm = mysql.conectar().prepareStatement(cadastrarUsuario);                
            }else{                
            pstm = mysql.conectar().prepareStatement(alteraUsuario);
            }
            pstm.setString(1, usuario.getNome_usuario());
            pstm.setString(2, usuario.getEmail());
            pstm.setString(3, usuario.getSenha());
            pstm.setString(4, usuario.getPerfil());
            pstm.executeUpdate();
            mysql.desconectar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarUsuario(Usuario usuario) {
        try {
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(alteraUsuario);
            pstm.setString(1, usuario.getNome_usuario());
            pstm.setString(2, usuario.getEmail());
            pstm.setString(3, usuario.getSenha());
            pstm.setString(4, usuario.getPerfil());
            pstm.setInt(5, usuario.getId_usuario());
            pstm.executeUpdate();
            mysql.desconectar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listarUsuarios(String user) {
        List<Usuario> usuario = new ArrayList();
        try {
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(consultarUsuario);
            pstm.setString(1, user);
            rs = pstm.executeQuery();
            Usuario userB;
            while (rs.next()) {
                userB = new Usuario();
                userB.setId_usuario(rs.getInt("id_usuario"));
                userB.setNome_usuario(rs.getString("nome_usuario"));
                userB.setSenha(rs.getString("senha"));
                userB.setPerfil(rs.getString("perfil"));
                usuario.add(userB);
            }
            mysql.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public List<Usuario> listarTodosUsuarios() {

        try {
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(consultarTodosUsuario);
            rs = pstm.executeQuery();
            List<Usuario> usuario = new ArrayList();
            Usuario userB;
            while (rs.next()) {
                userB = new Usuario();
                userB.setId_usuario(rs.getInt("id_usuario"));
                userB.setNome_usuario(rs.getString("nome_usuario"));
                userB.setEmail(rs.getString("email"));
                userB.setSenha(rs.getString("senha"));
                userB.setPerfil(rs.getString("perfil"));
                usuario.add(userB);
            }
            return usuario;
            // mysql.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName().getClass()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void excluirUsuario(int id_usuario) {
        try {
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(excluirUsuario);
            pstm.setInt(1, id_usuario);
            pstm.executeUpdate();
            mysql.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String confirmarSenhaParaCadastrar(String senha) {
        try {
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(confirmarSenha);
            pstm.setString(1, senha);
            rs = pstm.executeQuery();
            if (rs.next()) {
                String usuario = rs.getString(2);
                return usuario;
            } else {
                // JOptionPane.showMessageDialog(null, "senha invalidos!");

            }
        } catch (Exception e) {
        }
        return null;
    }
}
