package br.com.skep.entity;

public class ItensDoCarrinho {
Produto prod = new Produto();
    private Integer id_itensVd;
    private String codDaVenda = prod.getCod_produto();
    private String codProdutoItens = prod.getCod_produto();
    private String descricaoDoIntem = prod.getNome_produto();
    private int qtdCarrinho = 1;
    private Double valorItem = prod.getValor_venda();
    private Double valorParcial ;
    private Vendas vendItensEnty;

    /*
    public ItensDoCarrinho(String nome, Double preco, Integer quantidade, int qtdCarrinho) {
        Produto prod = new Produto();
        nome = prod.getNome_produto();
        preco = prod.getValor_venda();
        this.qtdCarrinho = 1;
        this.qtdItem = quantidade;
    }

    
    */

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

    public Vendas getVendItensEnty() {
        return vendItensEnty;
    }

    public void setVendItensEnty(Vendas vendItensEnty) {
        this.vendItensEnty = vendItensEnty;
    }

    public int getQtdCarrinho() {
        return qtdCarrinho;
    }

    public void setQtdCarrinho(int qtdCarrinho) {
        this.qtdCarrinho = qtdCarrinho;
    }

}
