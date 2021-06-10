package br.com.skep.dao;

import br.com.skep.entity.Modelo;
import br.com.skep.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class ModeloDAO {

    Session session = HibernateUtil.getSessionFactory().openSession();

    public void salva(Modelo modelo) {
        try {
            if (modelo.getIdModelo() != null) {
                session.beginTransaction();
                session.merge(modelo);
                session.getTransaction().commit();
                session.close();
                session.clear();
            } else {
                session.beginTransaction();
                session.persist(modelo);
                session.getTransaction().commit();
                session.close();
                session.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Modelo> listar() {
        List<Modelo> modeloList;
        modeloList = session.getNamedQuery("Modelo.findAll").list();
        session.close();
        return modeloList;
    }
    
    public void remomer(Modelo modelo){
        session.beginTransaction();
        session.delete(modelo);
        session.getTransaction().commit();
        session.close();
        session.clear();
    }

}
