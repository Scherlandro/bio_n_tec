package br.com.skep.entity;

import java.io.Serializable;

public class Produto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id_produto;
    private String cod_produto;
    private String dt_cadastro;
    private String nome_produto;
    private Double valor_compra;
    private Double percentual;
    private Double valor_venda;
    private Integer quantidade_estoque;
    
    private String inventoryStatus = "INSTOCK";

    private String imagemProd1 = "https://comeraprender.com.br/wp-content/uploads/2019/07/shutterstock_348902039-1-1531x860.jpg";
    private String imagemProd2 = "/resources/images/clone_old_TG.png";
    
    public Integer getId_produto() {
        return id_produto;
    }

    public void setId_produto(Integer id_produto) {
        this.id_produto = id_produto;
    }

    public String getCod_produto() {
        return cod_produto;
    }

    public void setCod_produto(String cod_produto) {
        this.cod_produto = cod_produto;
    }

    public String getDt_cadastro() {
        return dt_cadastro;
    }

    public void setDt_cadastro(String dt_cadastro) {
        this.dt_cadastro = dt_cadastro;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public Double getValor_compra() {
        return valor_compra;
    }

    public void setValor_compra(Double valor_compra) {
        this.valor_compra = valor_compra;
    }

    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }

    public Double getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(Double valor_venda) {
        this.valor_venda = valor_venda;
    }

    public Integer getQuantidade_estoque() {
        return quantidade_estoque;
    }

    public void setQuantidade_estoque(Integer quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public String getImagemProd1() {
        return imagemProd1;
    }

    public void setImagemProd1(String imagemProd1) {
        this.imagemProd1 = imagemProd1;
    }

    public String getImagemProd2() {
        return imagemProd2;
    }

    public void setImagemProd2(String imagemProd2) {
        this.imagemProd2 = imagemProd2;
    }
   
    
}
