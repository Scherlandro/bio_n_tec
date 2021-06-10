package br.com.skep.dao;

import br.com.skep.callBD.AcessoDB;
import br.com.skep.entity.Funcionario;
import br.com.skep.entity.Produto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    PreparedStatement pstm;
    ResultSet rs;
    private String consultarProduto = "Select *From produto";
    private String consultarProdutoPorCod = "Select *From produto Where cod_produto = ?";
    private String cadastrarProduto = "Insert into produto ( cod_produto, dt_cadastro,nome_produto, "
            + "  valor_compra, percentual, valor_venda, quantidade_estoque) Values (?, ?, ?, ?, ?, ?, ?)";
    private String alteraProduto = "UPDATE produto Set cod_produto = ?,  dt_cadastro = ?, "
            + " nome_produto = ?, valor_compra = ?, percentual = ?, valor_venda = ?, quantidade_estoque = ?"
            + " Where id_produto = ? ";
    private String excluirProduto = "Delete From produto Where id_produto = ?";

    public void salvar(Produto produto) {
        try {
            Connection conDb = AcessoDB.getConexao();
            if (produto.getId_produto() == null) {
                pstm = conDb.prepareCall(cadastrarProduto);
            } else {
                pstm = conDb.prepareCall(alteraProduto);
                pstm.setInt(8, produto.getId_produto());
            }
            pstm.setString(1, produto.getCod_produto());
            pstm.setString(2, produto.getDt_cadastro());
            pstm.setString(3, produto.getNome_produto());
            pstm.setDouble(4, produto.getValor_compra());
            pstm.setDouble(5, produto.getPercentual());
            pstm.setDouble(6, produto.getValor_venda());
            pstm.setInt(7, produto.getQuantidade_estoque());
            pstm.executeUpdate();
            AcessoDB.desconectar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listarTodosProduto() {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarProduto);
            // pstm.setString(1, nome);
            rs = pstm.executeQuery();
            List<Produto> listProd = new ArrayList();
            while (rs.next()) {
                Produto entyProd = new Produto();
                entyProd.setId_produto(rs.getInt("id_produto"));
                entyProd.setCod_produto(rs.getString("cod_produto"));
                entyProd.setDt_cadastro(rs.getString("dt_cadastro"));
                entyProd.setNome_produto(rs.getString("nome_produto"));
                entyProd.setValor_venda(rs.getDouble("valor_venda"));
                entyProd.setNome_produto(rs.getString("nome_produto"));
                entyProd.setValor_compra(rs.getDouble("valor_compra"));
                entyProd.setPercentual(rs.getDouble("percentual"));
                entyProd.setValor_venda(rs.getDouble("valor_venda"));
                entyProd.setQuantidade_estoque(rs.getInt("quantidade_estoque"));
                listProd.add(entyProd);
            }
            return listProd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Produto> listarProdutoPorCod(String cod) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarProdutoPorCod);
            pstm.setString(1, cod);
            rs = pstm.executeQuery();
            List<Produto> listProd = new ArrayList();
            while (rs.next()) {
                Produto entyProd = new Produto();
                entyProd.setId_produto(rs.getInt("id_produto"));
                entyProd.setCod_produto(rs.getString("cod_produto"));
                entyProd.setDt_cadastro(rs.getString("dt_cadastro"));
                entyProd.setNome_produto(rs.getString("nome_produto"));
                entyProd.setValor_venda(rs.getDouble("valor_venda"));
                entyProd.setNome_produto(rs.getString("nome_produto"));
                entyProd.setValor_compra(rs.getDouble("valor_compra"));
                entyProd.setPercentual(rs.getDouble("percentual"));
                entyProd.setValor_venda(rs.getDouble("valor_venda"));
                entyProd.setQuantidade_estoque(rs.getInt("quantidade_estoque"));
                listProd.add(entyProd);
            }
            return listProd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void excluirProduto(int id_produto) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(excluirProduto);
            pstm.setInt(1, id_produto);
            pstm.executeUpdate();
            AcessoDB.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
