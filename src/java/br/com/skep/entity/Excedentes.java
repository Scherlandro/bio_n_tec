/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.skep.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Scherlandro
 */
@Entity
@Table(name = "excedentes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Excedentes.findAll", query = "SELECT e FROM Excedentes e")
    , @NamedQuery(name = "Excedentes.findByIdExcedentes", query = "SELECT e FROM Excedentes e WHERE e.idExcedentes = :idExcedentes")
    , @NamedQuery(name = "Excedentes.findByFavorecido", query = "SELECT e FROM Excedentes e WHERE e.favorecido = :favorecido")
    , @NamedQuery(name = "Excedentes.findByFormasDePagamento", query = "SELECT e FROM Excedentes e WHERE e.formasDePagamento = :formasDePagamento")
    , @NamedQuery(name = "Excedentes.findByMontante", query = "SELECT e FROM Excedentes e WHERE e.montante = :montante")
    , @NamedQuery(name = "Excedentes.findByQtdDeParcelas", query = "SELECT e FROM Excedentes e WHERE e.qtdDeParcelas = :qtdDeParcelas")
    , @NamedQuery(name = "Excedentes.findByNumeroDaParcelaAtual", query = "SELECT e FROM Excedentes e WHERE e.numeroDaParcelaAtual = :numeroDaParcelaAtual")
    , @NamedQuery(name = "Excedentes.findByTipoDeTaxas", query = "SELECT e FROM Excedentes e WHERE e.tipoDeTaxas = :tipoDeTaxas")
    , @NamedQuery(name = "Excedentes.findByTaxas", query = "SELECT e FROM Excedentes e WHERE e.taxas = :taxas")
    , @NamedQuery(name = "Excedentes.findByValDoExcedenteAtual", query = "SELECT e FROM Excedentes e WHERE e.valDoExcedenteAtual = :valDoExcedenteAtual")
    , @NamedQuery(name = "Excedentes.findByObs", query = "SELECT e FROM Excedentes e WHERE e.obs = :obs")
    , @NamedQuery(name = "Excedentes.findByDtContratual", query = "SELECT e FROM Excedentes e WHERE e.dtContratual = :dtContratual")
    , @NamedQuery(name = "Excedentes.findByDataDoPgAtual", query = "SELECT e FROM Excedentes e WHERE e.dataDoPgAtual = :dataDoPgAtual")
    , @NamedQuery(name = "Excedentes.findByDtConclusao", query = "SELECT e FROM Excedentes e WHERE e.dtConclusao = :dtConclusao")})
public class Excedentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_excedentes")
    private Integer idExcedentes;
    @Column(name = "favorecido")
    private String favorecido;
    @Column(name = "formasDePagamento")
    private String formasDePagamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montante")
    private Double montante;
    @Column(name = "qtdDeParcelas")
    private Integer qtdDeParcelas;
    @Column(name = "numeroDaParcelaAtual")
    private String numeroDaParcelaAtual;
    @Column(name = "tipoDeTaxas")
    private String tipoDeTaxas;
    @Column(name = "taxas")
    private Double taxas;
    @Column(name = "valDoExcedenteAtual")
    private Double valDoExcedenteAtual;
    @Column(name = "obs")
    private String obs;
    @Column(name = "dtContratual")
    private String dtContratual;
    @Column(name = "dataDoPgAtual")
    private String dataDoPgAtual;
    @Column(name = "dtConclusao")
    private String dtConclusao;

    public Excedentes() {
    }

    public Excedentes(Integer idExcedentes) {
        this.idExcedentes = idExcedentes;
    }

    public Integer getIdExcedentes() {
        return idExcedentes;
    }

    public void setIdExcedentes(Integer idExcedentes) {
        this.idExcedentes = idExcedentes;
    }

    public String getFavorecido() {
        return favorecido;
    }

    public void setFavorecido(String favorecido) {
        this.favorecido = favorecido;
    }

    public String getFormasDePagamento() {
        return formasDePagamento;
    }

    public void setFormasDePagamento(String formasDePagamento) {
        this.formasDePagamento = formasDePagamento;
    }

    public Double getMontante() {
        return montante;
    }

    public void setMontante(Double montante) {
        this.montante = montante;
    }

    public Integer getQtdDeParcelas() {
        return qtdDeParcelas;
    }

    public void setQtdDeParcelas(Integer qtdDeParcelas) {
        this.qtdDeParcelas = qtdDeParcelas;
    }

    public String getNumeroDaParcelaAtual() {
        return numeroDaParcelaAtual;
    }

    public void setNumeroDaParcelaAtual(String numeroDaParcelaAtual) {
        this.numeroDaParcelaAtual = numeroDaParcelaAtual;
    }

    public String getTipoDeTaxas() {
        return tipoDeTaxas;
    }

    public void setTipoDeTaxas(String tipoDeTaxas) {
        this.tipoDeTaxas = tipoDeTaxas;
    }

    public Double getTaxas() {
        return taxas;
    }

    public void setTaxas(Double taxas) {
        this.taxas = taxas;
    }

    public Double getValDoExcedenteAtual() {
        return valDoExcedenteAtual;
    }

    public void setValDoExcedenteAtual(Double valDoExcedenteAtual) {
        this.valDoExcedenteAtual = valDoExcedenteAtual;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getDtContratual() {
        return dtContratual;
    }

    public void setDtContratual(String dtContratual) {
        this.dtContratual = dtContratual;
    }

    public String getDataDoPgAtual() {
        return dataDoPgAtual;
    }

    public void setDataDoPgAtual(String dataDoPgAtual) {
        this.dataDoPgAtual = dataDoPgAtual;
    }

    public String getDtConclusao() {
        return dtConclusao;
    }

    public void setDtConclusao(String dtConclusao) {
        this.dtConclusao = dtConclusao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExcedentes != null ? idExcedentes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Excedentes)) {
            return false;
        }
        Excedentes other = (Excedentes) object;
        if ((this.idExcedentes == null && other.idExcedentes != null) || (this.idExcedentes != null && !this.idExcedentes.equals(other.idExcedentes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.skep.entity.Excedentes[ idExcedentes=" + idExcedentes + " ]";
    }
    
}
