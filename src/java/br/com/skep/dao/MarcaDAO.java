package br.com.skep.dao;

import br.com.skep.entity.Marca;
import br.com.skep.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class MarcaDAO {

    public void salvar(Marca marca){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            if(marca.getIdMarca()!=null){
                session.beginTransaction();
                session.merge(marca);
                session.getTransaction().commit();
                session.close();
                session.clear();
            }else{
                session.beginTransaction();
                session.persist(marca);
                session.getTransaction().commit();
                session.close();
                session.clear();
            }
        } catch (Exception e) {
        }
    }
    
        public List<Marca> ListarMarcas(){
            List<Marca> marcaList ;
            Session session = HibernateUtil.getSessionFactory().openSession();
           marcaList = session.getNamedQuery("Marca.findAll").list(); 
           session.close();
           return marcaList;
        }
        
        public void remover(Marca marca){
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(marca);
            session.getTransaction().commit();
            session.close();
            session.clear();
        }
    
    
}
