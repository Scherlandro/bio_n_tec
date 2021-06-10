
package br.com.skep.dao;

import br.com.skep.callBD.AcessoDB;
import br.com.skep.entity.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class EnderecoDAO {
      PreparedStatement pstm;
    ResultSet rs;
    private String consultarEndereco = "Select *From endereco Where cep = ?";
    private String cadastrarEndereco = "Insert into endereco (cep,logradouro,bairro,cidade,estado,pais)  Values (?,?,?,?,?,?)";
    private String alteraEndereco = "UPDATE endereco Set cep = ?, logradouro = ?, bairro = ?, cidade = ?, estado = ?, pais = ? Where cep = ?";
    private String excluirEndereco = "Delete From eEndereco Where cep = ?";

     public void salvarEndereco(Endereco endr) {
        try {
            Connection conDb = AcessoDB.getConexao();
            if (endr.getId_endereço() == null) {
                pstm = conDb.prepareCall(cadastrarEndereco);
            } else {
                pstm = conDb.prepareCall(alteraEndereco);
                pstm.setInt(7, endr.getId_endereço());
            }
            pstm.setString(1, endr.getCep());
            pstm.setString(2, endr.getLogradouro());
            pstm.setString(3, endr.getBairro());
            pstm.setString(4, endr.getCidade());
            pstm.setString(5, endr.getEstado());
            pstm.setString(6, endr.getPais());
            pstm.executeUpdate();
            AcessoDB.desconectar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
      public List<Endereco> listarEndereco(String cep) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarEndereco);
            pstm.setString(2, cep);
            rs = pstm.executeQuery();
           List<Endereco> endList = new ArrayList();
            while (rs.next()) {
                Endereco endc = new Endereco();
                endc.setCep(rs.getString("cep"));
                endc.setLogradouro(rs.getString("logradouro"));
                endc.setBairro(rs.getString("bairro"));
                endc.setCidade(rs.getString("cidade"));
                endc.setEstado(rs.getString("estado"));
                endc.setPais(rs.getString("pais"));
                endList.add(endc);
            }
            return endList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
