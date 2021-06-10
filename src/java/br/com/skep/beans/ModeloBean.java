package br.com.skep.beans;

import br.com.skep.dao.ModeloDAO;
import br.com.skep.entity.Modelo;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class ModeloBean {
        private Modelo modeloEntity = new Modelo();
        private List<Modelo> modeloList = new ArrayList<>();
        ModeloDAO modeloDAO = new ModeloDAO();
        
        public void gravarModelo(){
            modeloDAO.salva(modeloEntity);
            modeloEntity = new Modelo();
        }
        
        public List<Modelo> listarModelos(){
           modeloList = modeloDAO.listar();            
            return modeloList;
        }
        public void editarModelo(Modelo modelo){
          modeloEntity = modelo;  
        }
        
        public void removerModelo(){
            modeloDAO.remomer(modeloEntity);
            modeloEntity = new Modelo();
        }

    public Modelo getModeloEntity() {
        return modeloEntity;
    }

    public void setModeloEntity(Modelo modeloEntity) {
        this.modeloEntity = modeloEntity;
    }

    public List<Modelo> getModeloList() {
        return modeloList;
    }

    public void setModeloList(List<Modelo> modeloList) {
        this.modeloList = modeloList;
    }
   
    
}
