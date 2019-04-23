
package br.com.skep.dao;

import br.com.skep.callBD.AcessoDB;
import br.com.skep.entity.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class FuncionarioDAO {
    
    PreparedStatement pstm;
    ResultSet rs;
   private String consultarFuncionario = "Select *From Funcionario";
   private String cadastrarFuncionario = "Insert into Funcionario (nome_funcionario,perfil)  Values (?, ?)";
   private String alteraFuncionario = "UPDATE Funcionario Set nome_funcionario = ?, cargo = ? Where id_funcionario = ?";
   private String excluirFuncionario = "Delete From Funcionario Where id_funcionario = ?";

 

    public void salvar(Funcionario funcionario) {
        try {
            Connection conDb = AcessoDB.getConexao();
            if (funcionario.getId_funcionario() == null) {
                pstm = conDb.prepareCall(cadastrarFuncionario);
            } else {
                pstm = conDb.prepareCall(alteraFuncionario);
              //  pstm.setInt(2, funcionario.getId_funcionario());
            }
            pstm.setString(1, funcionario.getNome_funcionario());
            pstm.setString(2, funcionario.getCargo());            
            pstm.executeUpdate();
            AcessoDB.desconectar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public List<Funcionario> listarTodosFuncionario() {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarFuncionario);
           // pstm.setString(1, nome);
            rs = pstm.executeQuery();
            List<Funcionario> listFunc = new ArrayList();
            while (rs.next()) {
                Funcionario entFunc = new Funcionario();
                entFunc.setId_funcionario(rs.getInt("id_funcionario"));
                entFunc.setNome_funcionario(rs.getString("nome_funcionario"));
                entFunc.setCargo(rs.getString("cargo"));
                listFunc.add(entFunc);
            }
            return listFunc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    public void excluirFuncionario(int id_funcionario) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(excluirFuncionario);
            pstm.setInt(1, id_funcionario);
            pstm.executeUpdate();
            AcessoDB.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
  /* 
     private String sql;
 private final String id_funcionario = "id";
 private final String nome_funcionario = "nome";
 private final String Cargo = "cargo";

 public List<Funcionario> buscaPorCargo(int cargo) {
 List<Funcionario> Funcionario = new ArrayList<Funcionario>();
 sql = "select * from Funcionario j where j.cargo = ?";
 con = ConnectionFactory.getConnection();
 PreparedStatement ps;
 try {
 ps = con.prepareStatement(sql);
 ps.setInt(1, cargo);
 ResultSet rs = ps.executeQuery();
 while (rs.next()) {
 Funcionario funcionario = new Funcionario();
 funcionario.setId(rs.getInt(id_funcionario));
 funcionario.setNome(rs.getString(nome_funcionario));
 funcionario.setEstado(rs.getInt(Cargo));
 Funcionario.add(funcionario);
 }
 con.close();
 } catch (SQLException ex) {
 Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
 }
 return Funcionario;

 }

 public Funcionario buscaPorNome(String nome) {
 Funcionario funcionario = new Funcionario();
 sql = "select * from Funcionario j where j.nome = ?";
 con = ConnectionFactory.getConnection();
 PreparedStatement ps;
 try {
 ps = con.prepareStatement(sql);
 ps.setString(1, nome);
 ResultSet rs = ps.executeQuery();
 if (rs.next()) {
 funcionario.setId(rs.getInt(id_funcionario));
 funcionario.setNome(rs.getString(nome_funcionario));
 funcionario.setEstado(rs.getInt(Cargo));
 }
 con.close();
 } catch (SQLException ex) {
 Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
 }
 return funcionario;

 }

 public List<Funcionario> listaTodos() {
 List<Funcionario> Funcionario = new ArrayList<Funcionario>();
 try {
 con = ConnectionFactory.getConnection();
 sql = "select * from Funcionario j";
 PreparedStatement ps = con.prepareStatement(sql);
 ResultSet rs = ps.executeQuery();
 while (rs.next()) {
 Funcionario j = new Funcionario();
 j.setId(rs.getInt(id_funcionario));
 j.setNome(rs.getString(nome_funcionario));
 j.setEstado(rs.getInt(Cargo));
 Funcionario.add(j);
 }
 con.close();
 } catch (SQLException ex) {
 Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
 }
 return Funcionario;
 }
 public void atualiza(Funcionario funcionario) {
 sql = "update Funcionario set nome=?, cargo=? where id=?";
 con = ConnectionFactory.getConnection();
 try {
 PreparedStatement ps = con.prepareStatement(sql);
 ps.setString(1, funcionario.getNome());
 ps.setInt(2, funcionario.getEstado());
 ps.setInt(3, funcionario.getId());
 ps.execute();
 } catch (SQLException ex) {
 Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
 }
 }
    
    
  */  
    

}
