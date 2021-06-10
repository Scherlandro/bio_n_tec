package br.com.skep.beans;

import br.com.skep.dao.MarcaDAO;
import br.com.skep.entity.Marca;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MarcaBean {
    private Marca marcaEntity = new Marca();
    private List<Marca> marcaList = new ArrayList<>();
    MarcaDAO marcaDAO = new MarcaDAO();

    public void gravarMarca(){
        marcaDAO.salvar(marcaEntity);
        marcaEntity = new Marca();
    }
    public void listarMarca(){
     marcaList =  marcaDAO.ListarMarcas();     
    }
    public void editarMarca(Marca marca){
        marcaEntity = marca;
    }
    public void removerMarca(Marca marca){
        marcaDAO.remover(marcaEntity);
        marcaEntity = new Marca();
    }
    
    
    
    public Marca getMarcaEntity() {
        return marcaEntity;
    }

    public void setMarcaEntity(Marca marcaEntity) {
        this.marcaEntity = marcaEntity;
    }

    public List<Marca> getMarcaList() {
        return marcaList;
    }

    public void setMarcaList(List<Marca> marcaList) {
        this.marcaList = marcaList;
    }
    
    
    
}
