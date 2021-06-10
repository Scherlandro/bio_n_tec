
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
   private String consultarFuncionario = "Select *From funcionario";
   private String buscaFuncionarioPorCod = "Select * From funcionario Where id_funcionario = ?";
   private String cadastrarFuncionario = "Insert into funcionario (nome_funcionario,cpf, rg, dt_nascimento,"
            + "dt_admissao, dt_demissao, cargo, salario,tipo_logradouro, logradouro,n_residencial,complemento, bairro,"
           + "cep, cidade, uf, telefone, celular, zap, email, obs)  Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   private String alteraFuncionario = "UPDATE funcionario Set nome_funcionario = ?,cpf = ?, rg = ?, dt_nascimento = ?,"
            + "dt_admissao = ?, dt_demissao = ?, cargo = ?, salario = ?,tipo_logradouro = ?, "
           + "logradouro = ?,n_residencial = ?,complemento = ?, bairro = ?,cep = ?, cidade = ?, uf = ?, "
           + "telefone = ?, celular = ?, zap = ?, email = ?, obs = ? Where id_funcionario = ?";
   private String excluirFuncionario = "Delete From funcionario Where id_funcionario = ?";
    public void salvar(Funcionario funcionario) {
        try {
            Connection conDb = AcessoDB.getConexao();
            if (funcionario.getId_funcionario() == null) {
                pstm = conDb.prepareCall(cadastrarFuncionario);
            } else {
                pstm = conDb.prepareCall(alteraFuncionario);
                pstm.setInt(22, funcionario.getId_funcionario());
            }
            pstm.setString(1, funcionario.getNome_funcionario());
            pstm.setString(2, funcionario.getRg());
            pstm.setString(3, funcionario.getCpf());           
            pstm.setString(4, funcionario.getDt_nascimento());           
            pstm.setString(5, funcionario.getDt_admissao());           
            pstm.setString(6, funcionario.getDt_demissao());           
            pstm.setString(7, funcionario.getCargo());            
            pstm.setDouble(8, funcionario.getSalario());            
            pstm.setString(9, funcionario.getTipoLogradouro());
            pstm.setString(10, funcionario.getLogradouro());
            pstm.setString(11, funcionario.getNumero());
            pstm.setString(12, funcionario.getComplemento());
            pstm.setString(13, funcionario.getBairro());
            pstm.setString(14, funcionario.getCep_funcionario());
            pstm.setString(15, funcionario.getCidade());
            pstm.setString(16, funcionario.getUf());
            pstm.setString(17, funcionario.getTelefone());
            pstm.setString(18, funcionario.getCelular());
            pstm.setString(19, funcionario.getZap_funcionario());
            pstm.setString(20, funcionario.getEmail());
            pstm.setString(21, funcionario.getObs());
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
            rs = pstm.executeQuery();
            List<Funcionario> listFunc = new ArrayList();
            while (rs.next()) {
                Funcionario entFunc = new Funcionario();
                entFunc.setId_funcionario(rs.getInt("id_funcionario"));
                entFunc.setNome_funcionario(rs.getString("nome_funcionario"));
                entFunc.setRg(rs.getString("rg"));
                entFunc.setCpf(rs.getString("cpf"));
                entFunc.setCargo(rs.getString("cargo"));
                entFunc.setTipoLogradouro(rs.getString("tipo_logradouro"));
                entFunc.setLogradouro(rs.getString("logradouro"));
                entFunc.setNumero(rs.getString("n_residencial"));
                entFunc.setBairro(rs.getString("bairro"));
                entFunc.setCep_funcionario(rs.getString("cep"));
                entFunc.setCidade(rs.getString("cidade"));
                entFunc.setUf(rs.getString("uf"));
                entFunc.setTelefone(rs.getString("telefone"));
                entFunc.setCelular(rs.getString("celular"));
                entFunc.setZap_funcionario(rs.getString("zap"));
                entFunc.setEmail(rs.getString("email"));
                entFunc.setObs(rs.getString("obs"));
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

}
