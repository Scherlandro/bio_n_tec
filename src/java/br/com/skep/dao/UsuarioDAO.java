package br.com.skep.dao;

import br.com.skep.callBD.AcessoDB;
import br.com.skep.entity.Usuario;
import br.com.skep.util.HibernateUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class UsuarioDAO {

    public Boolean logarUsuario(String nome, String senha) {
        PreparedStatement pstm;
        ResultSet rs;
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareCall("SELECT *FROM usuario WHERE nome_usuario LIKE ? and senha LIKE ?");
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

     public boolean validate(String nome, String senha) {

        Usuario user  = null;
        try{
             Session session = HibernateUtil.getSessionFactory().openSession(); 
            session.beginTransaction();
            user = (Usuario) session.createQuery("FROM Usuario u WHERE u.nomeUsuario = :nomeUsuario").setParameter("nomeUsuario", nome).uniqueResult();
            if (user != null && user.getSenha().equals(senha)) {
                return true;
            }
                session.getTransaction().commit();
                session.close();
                session.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void salvar(Usuario user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            if (user.getIdUsuario() != null) {
                session.beginTransaction();
                session.merge(user);
                session.getTransaction().commit();
           //     session.close();
           //     session.clear();
            } else {
                session.beginTransaction();
                session.persist(user);
                session.getTransaction().commit();
          //      session.close();
         //       session.clear();
            }
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
    }

    public List<Usuario> listarTodosUsuarios() {
        List<Usuario> listUser;
        Session session = HibernateUtil.getSessionFactory().openSession();
        listUser = session.getNamedQuery("Usuario.findAll").list();
        //session.close();
        return listUser;
    }

    public void remover(Usuario user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
     //   session.close();
     //   session.clear();
    }
}
