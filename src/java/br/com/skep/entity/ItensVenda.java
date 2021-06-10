
package br.com.skep.entity;



public class ItensVenda {

    private Integer id_itensVd;
    private String codDaVenda;
    private String codProdutoItens;
    private String descricaoDoIntem;
    private Integer qtdItem;
    private Double valorItem;
    private Double valorParcial;
   
    public Integer getId_itensVd() {
        return id_itensVd;
    }

    public void setId_itensVd(Integer id_itensVd) {
        this.id_itensVd = id_itensVd;
    }

    public String getCodDaVenda() {
        return codDaVenda;
    }

    public void setCodDaVenda(String codDaVenda) {
        this.codDaVenda = codDaVenda;
    }

    public String getCodProdutoItens() {
        return codProdutoItens;
    }

    public void setCodProdutoItens(String codProdutoItens) {
        this.codProdutoItens = codProdutoItens;
    }

    public String getDescricaoDoIntem() {
        return descricaoDoIntem;
    }

    public void setDescricaoDoIntem(String descricaoDoIntem) {
        this.descricaoDoIntem = descricaoDoIntem;
    }

    public Integer getQtdItem() {
        return qtdItem;
    }

    public void setQtdItem(Integer qtdItem) {
        this.qtdItem = qtdItem;
    }

    public Double getValorItem() {
        return valorItem;
    }

    public void setValorItem(Double valorItem) {
        this.valorItem = valorItem;
    }

    public Double getValorParcial() {
        return valorParcial;
    }

    public void setValorParcial(Double valorParcial) {
        this.valorParcial = valorParcial;
    }

  
    
    
    
}
