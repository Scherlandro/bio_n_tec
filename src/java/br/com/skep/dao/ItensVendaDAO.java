
package br.com.skep.dao;

import br.com.skep.callBD.AcessoDB;
import br.com.skep.entity.ItensVenda;
import br.com.skep.entity.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItensVendaDAO {
    PreparedStatement pstm;
    ResultSet rs;
    private String consultarTodos = "Select *From itensdavenda";
    private String consultarItens = "Select *From itensdavenda ivd Inner Join vendas vd "
            + "Where vd.cod_venda = ivd.cod_vendas and ivd.cod_vendas = ? ";
    private String consultarItensPorID = "Select *From itensdavenda ivd  "
            + "Where ivd.id_itens_vd = ? ";
    private String cadastrarItens = "Insert into itensdavenda (cod_vendas, cod_produtos, descricao, "
            + "  valor_venda, qtd_vendidas, valor_parcial) Values (?, ?, ?, ?, ?, ?)";
    private String alteraItens = "UPDATE itensdavenda Set cod_vendas = ?, cod_produtos = ?, descricao = ?, "
            + " valor_venda = ?, qtd_vendidas = ?, valor_parcial = ? Where id_itens_vd = ? ";
    private String alteraQtd = "UPDATE itensdavenda Set qtd_vendidas = ?, valor_parcial = ? Where id_itens_vd = ? ";
    private String excluirItens = "Delete From itensdavenda Where id_itens_vd = ?";

    public void salvar(ItensVenda itensVd, Vendas vd) {
        try {
            Connection conDb = AcessoDB.getConexao();
            if (itensVd.getId_itensVd()== null) {
                pstm = conDb.prepareCall(cadastrarItens);
            } else {
                pstm = conDb.prepareCall(alteraItens);
                pstm.setInt(7, itensVd.getId_itensVd());
            }
            pstm.setString(1, itensVd.getCodDaVenda());
            pstm.setString(2, itensVd.getCodProdutoItens());
            pstm.setString(3, itensVd.getDescricaoDoIntem());
            pstm.setDouble(4, itensVd.getValorItem());
            pstm.setDouble(5, itensVd.getQtdItem());
            pstm.setDouble(6, itensVd.getValorParcial());
            pstm.executeUpdate();
            AcessoDB.desconectar();
         VendaDAO vdDAO = new VendaDAO();
         vdDAO.salvarVenda(vd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void alteraQTDeSubtVenda(ItensVenda itensVd, Vendas vd) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareCall(alteraQtd);
            pstm.setDouble(1, itensVd.getQtdItem());
            pstm.setDouble(2, itensVd.getValorParcial());
            pstm.setInt(3, itensVd.getId_itensVd());
            pstm.executeUpdate();
            AcessoDB.desconectar();             
            VendaDAO vdDAO = new VendaDAO();
            vdDAO.salvarVenda(vd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ItensVenda> listarItensDaVenda(String codVD) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarItens);
            pstm.setString(1, codVD);
            rs = pstm.executeQuery();
            List<ItensVenda> listItensDeVenda = new ArrayList();
            while (rs.next()) {
                ItensVenda entyItensVD = new ItensVenda();
                entyItensVD.setId_itensVd(rs.getInt("id_itens_vd"));
                entyItensVD.setCodDaVenda(rs.getString("cod_vendas"));
                entyItensVD.setCodProdutoItens(rs.getString("cod_produtos"));
                entyItensVD.setDescricaoDoIntem(rs.getString("descricao"));
                entyItensVD.setValorItem(rs.getDouble("valor_venda"));
                entyItensVD.setQtdItem(rs.getInt("qtd_vendidas"));
                entyItensVD.setValorParcial(rs.getDouble("valor_parcial"));
                listItensDeVenda.add(entyItensVD);
            }
            return listItensDeVenda;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<ItensVenda> listarItensDaVendaPorID(int id) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarItensPorID);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            List<ItensVenda> listItensDeVenda = new ArrayList();
            while (rs.next()) {
                ItensVenda entyItensVD = new ItensVenda();
                entyItensVD.setId_itensVd(rs.getInt("id_itens_vd"));
                entyItensVD.setCodDaVenda(rs.getString("cod_vendas"));
                entyItensVD.setCodProdutoItens(rs.getString("cod_produtos"));
                entyItensVD.setDescricaoDoIntem(rs.getString("descricao"));
                entyItensVD.setValorItem(rs.getDouble("valor_venda"));
                entyItensVD.setQtdItem(rs.getInt("qtd_vendidas"));
                entyItensVD.setValorParcial(rs.getDouble("valor_parcial"));
                listItensDeVenda.add(entyItensVD);
            }
            return listItensDeVenda;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
   public List<ItensVenda> listarTodosItensDaVenda() {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarTodos);
         //   pstm.setString(1, codVD);
            rs = pstm.executeQuery();
            List<ItensVenda> listItensDeVenda = new ArrayList();
            while (rs.next()) {
                ItensVenda entyItensVD = new ItensVenda();
                entyItensVD.setId_itensVd(rs.getInt("id_itens_vd"));
                entyItensVD.setCodDaVenda(rs.getString("cod_vendas"));
                entyItensVD.setCodProdutoItens(rs.getString("cod_produtos"));
                entyItensVD.setDescricaoDoIntem(rs.getString("descricao"));
                entyItensVD.setValorItem(rs.getDouble("valor_venda"));
                entyItensVD.setQtdItem(rs.getInt("qtd_vendidas"));
                entyItensVD.setValorParcial(rs.getDouble("valor_parcial"));
                listItensDeVenda.add(entyItensVD);
            }
            return listItensDeVenda;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void excluirItenDaVenda(int id_itensVD, Vendas vd) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(excluirItens);
            pstm.setInt(1, id_itensVD);
            pstm.executeUpdate();
            AcessoDB.desconectar();
            VendaDAO vdDAO = new VendaDAO();
            vdDAO.salvarVenda(vd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
