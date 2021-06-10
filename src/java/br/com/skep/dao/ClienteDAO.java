package br.com.skep.dao;

import br.com.skep.callBD.AcessoDB;
import br.com.skep.entity.Cliente;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO  implements Serializable{

    PreparedStatement pstm;
    ResultSet rs;
    private String consultarTodosCliente = "Select *From cliente";
    private String buscaClientePorCod = "Select *From cliente Where id_cliente = ?";    
    private String consultarPerfil = "SELECT *FROM cliente WHERE nome_cliente LIKE ?";
    private String consultarCpf = "SELECT *FROM cliente WHERE cpf_cliente = ?";
    private String consultarCnpj = "SELECT *FROM cliente WHERE cnpj = ?";
    private String cadastrarCliente = "Insert into cliente (nome_cliente, inscricaoest, pessoa, cpf_cliente, cnpj,"
            + "cep_cliente,tipo_logradouro, logradouro,numero,complemento_cliente, bairro,cidade,estado,"
            + "telefone,celular,zap,email)  Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private String alteraCliente = "UPDATE cliente Set nome_cliente = ?, inscricaoest = ?, pessoa = ?, cpf_cliente = ?, cnpj = ?,cep_cliente = ?,"
            + " tipo_logradouro = ?, logradouro = ?,numero = ?,complemento_cliente = ?, bairro = ?, cidade = ?,estado = ?,telefone = ?, "
            + "celular = ?, zap = ?, email = ? Where id_cliente = ?";
    private String excluirCliente = "Delete From cliente Where id_cliente = ?";

    public void salvar(Cliente clien) {
        try {
            Connection conDb = AcessoDB.getConexao();
            if (clien.getId_cliente() == null) {
                pstm = conDb.prepareCall(cadastrarCliente);
            } else {
                pstm = conDb.prepareCall(alteraCliente);
                pstm.setInt(18, clien.getId_cliente());
            }
            pstm.setString(1, clien.getNome_cliente());
            pstm.setString(2, clien.getInscricaoest());
            pstm.setString(3, clien.getPessoa());
            if("Física".equals(clien.getPessoa())){
            pstm.setString(4, clien.getCpf());
            pstm.setString(5, "");
            }
            if("Jurídica".equals(clien.getPessoa())){
            pstm.setString(4,"");
            pstm.setString(5, clien.getCnpj());
            }
            pstm.setString(6, clien.getCep_cliente());
            pstm.setString(7, clien.getTipoLogradouro());
            pstm.setString(8, clien.getLogradouro());
            pstm.setString(9, clien.getNumero());
            pstm.setString(10, clien.getComplemento_cliente());
            pstm.setString(11, clien.getBairro());
            pstm.setString(12, clien.getCidade());
            pstm.setString(13, clien.getEstado());
            pstm.setString(14, clien.getTelefone());
            pstm.setString(15, clien.getCelular());
            pstm.setString(16, clien.getZap());
            pstm.setString(17, clien.getEmail());
            pstm.executeUpdate();
            AcessoDB.desconectar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     public List<Cliente> buscarClientePorCod(Long id_cliente) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(buscaClientePorCod);
            pstm.setLong(1, id_cliente);
            rs = pstm.executeQuery();
            List<Cliente> listCliente = new ArrayList();
            while (rs.next()) {
                Cliente entClie = new Cliente();
                entClie.setId_cliente(rs.getInt("id_cliente"));
                entClie.setNome_cliente(rs.getString("nome_cliente"));
                listCliente.add(entClie);
            }
            return listCliente;
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
    
    public List<Cliente> listarTodosClientes() {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarTodosCliente);
            rs = pstm.executeQuery();
            List<Cliente> clieLis = new ArrayList();
            while (rs.next()) {
                Cliente cliEnt = new Cliente();
                cliEnt.setId_cliente(rs.getInt("id_cliente"));
                cliEnt.setNome_cliente(rs.getString("nome_cliente"));
                cliEnt.setInscricaoest(rs.getString("inscricaoest"));
                cliEnt.setPessoa(rs.getString("pessoa"));
                cliEnt.setCpf(rs.getString("cpf_cliente"));
                cliEnt.setCnpj(rs.getString("cnpj"));
                cliEnt.setCep_cliente(rs.getString("cep_cliente"));
                cliEnt.setTipoLogradouro(rs.getString("tipo_logradouro"));
                cliEnt.setLogradouro(rs.getString("logradouro"));
                cliEnt.setNumero(rs.getString("numero"));
                cliEnt.setComplemento_cliente(rs.getString("complemento_cliente"));
                cliEnt.setBairro(rs.getString("bairro"));
                cliEnt.setCidade(rs.getString("cidade"));
                cliEnt.setEstado(rs.getString("estado"));
                cliEnt.setTelefone(rs.getString("telefone"));
                cliEnt.setCelular(rs.getString("celular"));
                cliEnt.setZap(rs.getString("zap"));
                cliEnt.setEmail(rs.getString("email"));
                clieLis.add(cliEnt);
            }
            return clieLis;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void excluirCliente(int id_Cliente) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(excluirCliente);
            pstm.setInt(1, id_Cliente);
            pstm.executeUpdate();
            AcessoDB.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
