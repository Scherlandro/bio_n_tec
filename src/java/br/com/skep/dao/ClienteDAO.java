package br.com.skep.dao;

import br.com.skep.callBD.AcessoDB;
import br.com.skep.entity.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    PreparedStatement pstm;
    ResultSet rs;
    private String consultarTodosCliente = "Select *From Cliente";
    private String consultarPerfil = "SELECT *FROM Cliente WHERE nome_Cliente LIKE ?";
    private String cadastrarCliente = "Insert into Cliente (nome_Cliente, inscricaoest, pessoa, cpf, cnpj,"
            + "cep,logradouro,numero,bairro,cidade,estado,telefone,celular,zap)  Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private String alteraCliente = "UPDATE Cliente Set nome_Cliente = ?, inscricaoest = ?, pessoa = ?, cpf = ?, cnpj = ?,cep = ?,"
            + " logradouro = ?,numero = ?,bairro = ?, cidade = ?,estado = ?,telefone = ?, celular = ?, zap = ? Where id_Cliente = ?";
    private String excluirCliente = "Delete From Cliente Where id_Cliente = ?";

    public void salvar(Cliente clien) {
        try {
            Connection conDb = AcessoDB.getConexao();
            if (clien.getId_cliente() == null) {
                pstm = conDb.prepareCall(cadastrarCliente);
            } else {
                pstm = conDb.prepareCall(alteraCliente);
                pstm.setInt(15, clien.getId_cliente());
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
            pstm.setString(6, clien.getCep());
            pstm.setString(7, clien.getLogradouro());
            pstm.setString(8, clien.getNumero());
            pstm.setString(9, clien.getBairro());
            pstm.setString(10, clien.getCidade());
            pstm.setString(11, clien.getEstado());
            pstm.setString(12, clien.getTelefone());
            pstm.setString(13, clien.getCelular());
            pstm.setString(14, clien.getZap());
            pstm.executeUpdate();
            AcessoDB.desconectar();

        } catch (Exception e) {
            e.printStackTrace();
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
                cliEnt.setCpf(rs.getString("cpf"));
                cliEnt.setCnpj(rs.getString("cnpj"));
                cliEnt.setCep(rs.getString("cep"));
                cliEnt.setLogradouro(rs.getString("logradouro"));
                cliEnt.setNumero(rs.getString("numero"));
                cliEnt.setBairro(rs.getString("bairro"));
                cliEnt.setCidade(rs.getString("cidade"));
                cliEnt.setEstado(rs.getString("estado"));
                cliEnt.setTelefone(rs.getString("telefone"));
                cliEnt.setCelular(rs.getString("celular"));
                cliEnt.setZap(rs.getString("zap"));
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
