package br.com.skep.dao;

import br.com.skep.callBD.AcessoDB;
import br.com.skep.entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    PreparedStatement pstm;
    ResultSet rs;
    String consultarTodosUsuario = "Select *From Usuario";
    String consultarPerfil = "SELECT *FROM Usuario WHERE nome_usuario LIKE ?";
    String cadastrarUsuario = "Insert into Usuario (nome_usuario, email, senha, perfil)  Values (?, ?,?,?)";
    String alteraUsuario = "UPDATE Usuario Set nome_usuario = ?, email = ?, senha = ?, perfil = ? Where id_usuario = ?";
    String excluirUsuario = "Delete From Usuario Where id_usuario = ?";
    String logando = "SELECT *FROM Usuario WHERE nome_usuario LIKE ? and senha LIKE ?";

    public Boolean logarUsuario(String nome, String senha) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareCall(logando);
            pstm.setString(1, nome);
            pstm.setString(2, senha);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void salvar(Usuario usuario) {
        try {
            Connection conDb = AcessoDB.getConexao();
            if (usuario.getId_usuario() == null) {
                pstm = conDb.prepareCall(cadastrarUsuario);
            } else {
                pstm = conDb.prepareCall(alteraUsuario);
            }
            pstm.setString(1, usuario.getNome_usuario());
            pstm.setString(2, usuario.getEmail());
            pstm.setString(3, usuario.getSenha());
            pstm.setString(4, usuario.getPerfil());
            pstm.executeUpdate();
            AcessoDB.desconectar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> consultarPerfil(Integer id) {

        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarPerfil);
            pstm.setInt(5, id);
            rs = pstm.executeQuery();
            List<Usuario> usuario = new ArrayList();
            while (rs.next()) {
                Usuario userB = new Usuario();
                userB.setPerfil(rs.getString("perfil"));
                usuario.add(userB);
            }
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Usuario> listarTodosUsuarios() {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarTodosUsuario);
            rs = pstm.executeQuery();
            List<Usuario> usuario = new ArrayList();
            while (rs.next()) {
                Usuario userB = new Usuario();
                userB.setId_usuario(rs.getInt("id_usuario"));
                userB.setNome_usuario(rs.getString("nome_usuario"));
                userB.setEmail(rs.getString("email"));
                userB.setSenha(rs.getString("senha"));
                userB.setPerfil(rs.getString("perfil"));
                usuario.add(userB);
            }
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void excluirUsuario(int id_usuario) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(excluirUsuario);
            pstm.setInt(1, id_usuario);
            pstm.executeUpdate();
            conDb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
