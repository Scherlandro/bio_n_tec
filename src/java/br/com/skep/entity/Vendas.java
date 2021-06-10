package br.com.skep.entity;



public class Vendas {
    
    private Integer id_venda;
    private String cod_venda;
    private Integer id_cliente;
    private String nome_cliente;
    private Integer id_funcionario;
    private String nome_funcionario;
    private String dt_venda;
    private Double subtotal;
    private Double desconto;
    private Double total_geral;
    private String forma_de_pagamento;
    private Integer qtd_de_parcelas;

   
    public Integer getId_venda() {
        return id_venda;
    }

    public void setId_venda(Integer id_venda) {
        this.id_venda = id_venda;
    }

    public String getCod_venda() {
        return cod_venda;
    }

    public void setCod_venda(String cod_venda) {
        this.cod_venda = cod_venda;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public Integer getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(Integer id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getDt_venda() {
        return dt_venda;
    }

    public void setDt_venda(String dt_venda) {
        this.dt_venda = dt_venda;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getTotal_geral() {
        return total_geral;
    }

    public void setTotal_geral(Double total_geral) {
        this.total_geral = total_geral;
    }

    public String getForma_de_pagamento() {
        return forma_de_pagamento;
    }

    public void setForma_de_pagamento(String forma_de_pagamento) {
        this.forma_de_pagamento = forma_de_pagamento;
    }

    public Integer getQtd_de_parcelas() {
        return qtd_de_parcelas;
    }

    public void setQtd_de_parcelas(Integer qtd_de_parcelas) {
        this.qtd_de_parcelas = qtd_de_parcelas;
    }

}
