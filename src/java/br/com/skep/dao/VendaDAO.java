package br.com.skep.dao;

import br.com.skep.callBD.AcessoDB;
import br.com.skep.entity.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    PreparedStatement pstm;
    ResultSet rs;
    int ultID, ultcod;
    private String consultarVendas = "Select *From vendas";
    private String consultarVendasPorCod = "Select *From vendas Where cod_venda = ?";
    private String buscaUltmoIDdaVd = "Select Max(id_venda) As id_venda from vendas";
    private String abrirVenda = "Insert into vendas (cod_venda,id_funcionario,nome_funcionario,dt_venda) Values (?,?,?,?)";
    private String cadastrarVenda = "Insert into vendas (id_cliente,nome_cliente,id_funcionario,dt_venda, "
            + "  valor_unitario,subtotal,desconto,totalgeral,forma_de_pagamento, numero_de_parcelas) Values (?, ?, ?, ?, ?, ?, ?,?, ?, ?)";
    private String alteraClieteEmVenda = "UPDATE vendas Set nome_cliente = ? Where id_venda = ? ";
    private String alteraVenda = "UPDATE vendas Set cod_venda = ?, id_cliente = ?,nome_cliente = ?,id_funcionario = ?,"
            + " dt_venda = ?, subtotal = ?, desconto = ?, totalgeral = ?, forma_de_pagamento = ?, numero_de_parcelas = ?"
            + " Where id_venda = ? ";
    private String atualizarValorVenda = "UPDATE vendas Set subtotal = ?, desconto = ?, totalgeral = ?"
            + " Where id_venda = ? ";
    private String excluirVenda = "Delete From vendas Where id_venda = ?";

    public int buscaUltimoIDdaVenda() {
        try {
            Connection conn = AcessoDB.getConexao();
            pstm = conn.prepareStatement(buscaUltmoIDdaVd);
            rs = pstm.executeQuery();
            if (rs.last()) {
                ultID = rs.getInt("id_venda");
                return ultID;
            }
            AcessoDB.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ultID;
    }

    public void abrirVenda(Vendas venda) {
        try {
            Connection conDb = AcessoDB.getConexao();
            if (venda.getId_venda() == null) {
                pstm = conDb.prepareCall(abrirVenda);
                pstm.setString(1, venda.getCod_venda());
                pstm.setInt(2, venda.getId_funcionario());
                pstm.setString(3, venda.getNome_funcionario());
                pstm.setString(1, venda.getNome_cliente());
                pstm.setString(4, venda.getDt_venda());
                pstm.executeUpdate();
                AcessoDB.desconectar();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void salvarVenda(Vendas venda) {
        try {
            Connection conDb = AcessoDB.getConexao();
            if (venda.getId_venda() == null) {
                pstm = conDb.prepareCall(cadastrarVenda);
            } else {
                pstm = conDb.prepareCall(alteraVenda);
                pstm.setInt(11, venda.getId_venda());
                if (venda.getId_cliente() == null) {
                    pstm = conDb.prepareCall(atualizarValorVenda);
                    pstm.setDouble(1, venda.getSubtotal());
                    if (venda.getDesconto() == null) {
                        venda.setDesconto(0.0);
                    } 
                        pstm.setDouble(2, venda.getDesconto());
                        pstm.setDouble(3, venda.getSubtotal() - venda.getDesconto());
                        pstm.setInt(4, venda.getId_venda());
                        pstm.executeUpdate();
                        AcessoDB.desconectar();
                }
            }
            if (venda.getId_cliente() != null) {
            pstm.setString(1, venda.getCod_venda());
            pstm.setInt(2, venda.getId_cliente());
            pstm.setString(3, venda.getNome_cliente());
            pstm.setInt(4, venda.getId_funcionario());
            pstm.setString(5, venda.getDt_venda());
            pstm.setDouble(6, venda.getSubtotal());
            pstm.setDouble(7, venda.getDesconto());
            pstm.setDouble(8, venda.getTotal_geral());
            pstm.setString(9, venda.getForma_de_pagamento());
            pstm.setInt(10, venda.getQtd_de_parcelas());
            pstm.executeUpdate();
            AcessoDB.desconectar();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Vendas> listarTodasVendas() {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarVendas);
            rs = pstm.executeQuery();
            List<Vendas> listVenda = new ArrayList();
            while (rs.next()) {
                Vendas entyVendas = new Vendas();
                entyVendas.setId_venda(rs.getInt("id_venda"));
                entyVendas.setCod_venda(rs.getString("cod_venda"));
                entyVendas.setId_cliente(rs.getInt("id_cliente"));
                entyVendas.setNome_cliente(rs.getString("nome_cliente"));
                entyVendas.setId_funcionario(rs.getInt("id_funcionario"));
                entyVendas.setNome_funcionario(rs.getString("nome_funcionario"));
                entyVendas.setDt_venda(rs.getString("dt_venda"));
                entyVendas.setSubtotal(rs.getDouble("subtotal"));
                entyVendas.setDesconto(rs.getDouble("desconto"));
                entyVendas.setTotal_geral(rs.getDouble("totalgeral"));
                entyVendas.setForma_de_pagamento(rs.getString("forma_de_pagamento"));
                entyVendas.setQtd_de_parcelas(rs.getInt("numero_de_parcelas"));
                listVenda.add(entyVendas);
            }
            return listVenda;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Vendas> listarVendaParcial() {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarVendas);
            rs = pstm.executeQuery();
            List<Vendas> listVenda = new ArrayList();
            while (rs.next()) {
                Vendas entyVendas = new Vendas();
                entyVendas.setId_venda(rs.getInt("id_venda"));
                entyVendas.setCod_venda(rs.getString("cod_venda"));
                entyVendas.setId_cliente(rs.getInt("id_cliente"));
                entyVendas.setNome_cliente(rs.getString("nome_cliente"));
                entyVendas.setId_funcionario(rs.getInt("id_funcionario"));
                entyVendas.setNome_funcionario(rs.getString("nome_funcionario"));
                entyVendas.setDt_venda(rs.getString("dt_venda"));
                entyVendas.setSubtotal(rs.getDouble("subtotal"));
                entyVendas.setDesconto(rs.getDouble("desconto"));
                entyVendas.setTotal_geral(rs.getDouble("totalgeral"));
                entyVendas.setForma_de_pagamento(rs.getString("forma_de_pagamento"));
                entyVendas.setQtd_de_parcelas(rs.getInt("numero_de_parcelas"));
                listVenda.add(entyVendas);
            }
            return listVenda;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Vendas> listarVendaPorCodigo(String cod) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(consultarVendasPorCod);
            pstm.setString(1, cod);
            rs = pstm.executeQuery();
            List<Vendas> listVenda = new ArrayList();
            while (rs.next()) {
                Vendas entyVendas = new Vendas();
                entyVendas.setId_venda(rs.getInt("id_venda"));
                entyVendas.setCod_venda(rs.getString("cod_venda"));
                //entyVendas.setId_cliente(rs.getInt("id_cliente"));
                entyVendas.setNome_cliente(rs.getString("nome_cliente"));
                entyVendas.setId_funcionario(rs.getInt("id_funcionario"));
                entyVendas.setNome_funcionario(rs.getString("nome_funcionario"));
                entyVendas.setDt_venda(rs.getString("dt_venda"));
                entyVendas.setSubtotal(rs.getDouble("subtotal"));
                entyVendas.setDesconto(rs.getDouble("desconto"));
                entyVendas.setTotal_geral(rs.getDouble("totalgeral"));
                entyVendas.setForma_de_pagamento(rs.getString("forma_de_pagamento"));
                entyVendas.setQtd_de_parcelas(rs.getInt("numero_de_parcelas"));
                listVenda.add(entyVendas);
            }
            return listVenda;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void excluirVenda(int id_venda) {
        try {
            Connection conDb = AcessoDB.getConexao();
            pstm = conDb.prepareStatement(excluirVenda);
            pstm.setInt(1, id_venda);
            pstm.executeUpdate();
            AcessoDB.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
