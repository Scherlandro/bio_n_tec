package br.com.skep.dao;

import br.com.skep.callBD.AcessoDB;
import br.com.skep.entity.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    PreparedStatement pstm;
    ResultSet rs;
    private String consultarTodosFornecedor = "Select *From fornecedor";
    private String buscaFornecedorPorCod = "Select *From fornecedor Where id_fornecedor = ?";    
    private String consultarPerfil = "SELECT *FROM fornecedor WHERE razao_social LIKE ?";
    private String consultarCpf = "SELECT *FROM fornecedor WHERE cpf = ?";
    private String consultarCnpj = "SELECT *FROM fornecedor WHERE cnpj = ?";
    private String cadastrarFornecedor = "Insert into fornecedor (razao_social, inscricaoest, pessoa, cpf, cnpj, data_vinculo,"
            + "logradouro,numero,complementos, bairro, cep_fornecedor, cidade, estado,telefone,celular,zap, email, site) "
            + " Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private String alteraFornecedor = "UPDATE fornecedor Set razao_social = ?, inscricaoest = ?, pessoa = ?, cpf = ?, cnpj = ?,data_vinculo = ?,"
            + " logradouro = ?,numero = ?,complementos = ?,bairro = ?,cep_fornecedor=?, cidade = ?,estado = ?,"
            + "telefone = ?, celular = ?, zap = ?, email=?,site = ? Where id_fornecedor = ?";
    private String excluirFornecedor = "Delete From fornecedor Where id_fornecedor = ?";

    public void salvar(Fornecedor fornecd) {
        try {
            Connection conDb = AcessoDB.getConexao();
            if (fornecd.getId_fornecedor() == null) {
                pstm = conDb.prepareCall(cadastrarFornecedor);
            } else {
                pstm = conDb.prepareCall(alteraFornecedor);
                pstm.setInt(19, fornecd.getId_fornecedor());
            }
            pstm.setString(1, fornecd.getRazao_social());
            pstm.setString(2, fornecd.getInscricaoest());
            pstm.setString(3, fornecd.getPessoa());
            if("Física".equals(fornecd.getPessoa())){
            pstm.setString(4, fornecd.getCpf());
            pstm.setString(5, "");
            }
            if("Jurídica".equals(fornecd.getPessoa())){
            pstm.setString(4,"");
            pstm.setString(5, fornecd.getCnpj());
            }
            pstm.setString(6, fornecd.getData_vinculo());
            pstm.setString(7, fornecd.getLogradouro());
            pstm.setString(8, fornecd.getNumero());
            pstm.setString(9, fornecd.getComplementos());
            pstm.setString(10, fornecd.getBairro());
            pstm.setString(11, fornecd.getCep_fornecedor());
            pstm.setString(12, fornecd.getCidade());
            pstm.setString(13, fornecd.getEstado());
            pstm.setString(14, fornecd.getTelefone());
            pstm.setString(15, fornecd.getCelular());
            pstm.setString(16, fornecd.getZap());
            pstm.setString(17, fornecd.getEmail());
            pstm.setString(18, fornecd.getSite());
            pstm.executeUpdate();
            AcessoDB.desconectar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     public List<Fornecedor> buscarFornecedorPorCod(Long id_fornecedor) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(buscaFornecedorPorCod);
            pstm.setLong(1, id_fornecedor);
            rs = pstm.executeQuery();
            List<Fornecedor> listFornecedor = new ArrayList();
            while (rs.next()) {
                Fornecedor entClie = new Fornecedor();
                entClie.setId_fornecedor(rs.getInt("id_fornecedor"));
                entClie.setRazao_social(rs.getString("razao_social"));
                listFornecedor.add(entClie);
            }
            return listFornecedor;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
       public boolean verificarReplicasCPF(String ver) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarCpf);
            pstm.setString(1, ver);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }else
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean verificarReplicasCNPJ(String ver) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarCnpj);
            pstm.setString(1, ver);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }else
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    } 
        
    
    public List<Fornecedor> listarTodosFornecedores() {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarTodosFornecedor);
            rs = pstm.executeQuery();
            List<Fornecedor> fornecdList = new ArrayList();
            while (rs.next()) {
                Fornecedor fornecdEnt = new Fornecedor();
                fornecdEnt.setId_fornecedor(rs.getInt("id_fornecedor"));
                fornecdEnt.setRazao_social(rs.getString("razao_social"));
                fornecdEnt.setInscricaoest(rs.getString("inscricaoest"));
                fornecdEnt.setPessoa(rs.getString("pessoa"));
                fornecdEnt.setCpf(rs.getString("cpf"));
                fornecdEnt.setCnpj(rs.getString("cnpj"));
                fornecdEnt.setData_vinculo(rs.getString("data_vinculo"));
                fornecdEnt.setLogradouro(rs.getString("logradouro"));
                fornecdEnt.setNumero(rs.getString("numero"));
                fornecdEnt.setComplementos(rs.getString("complementos"));
                fornecdEnt.setBairro(rs.getString("bairro"));
                fornecdEnt.setCep_fornecedor(rs.getString("cep_fornecedor"));
                fornecdEnt.setCidade(rs.getString("cidade"));
                fornecdEnt.setEstado(rs.getString("estado"));
                fornecdEnt.setTelefone(rs.getString("telefone"));
                fornecdEnt.setCelular(rs.getString("celular"));
                fornecdEnt.setZap(rs.getString("zap"));
                fornecdEnt.setEmail(rs.getString("email"));
                fornecdEnt.setSite(rs.getString("site"));
                fornecdList.add(fornecdEnt);
            }
            return fornecdList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void excluirFornecedor(int id_Fornecedor) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(excluirFornecedor);
            pstm.setInt(1, id_Fornecedor);
            pstm.executeUpdate();
            AcessoDB.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
