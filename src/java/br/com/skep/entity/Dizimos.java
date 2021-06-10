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
@Table(name = "dizimos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dizimos.findAll", query = "SELECT d FROM Dizimos d")
    , @NamedQuery(name = "Dizimos.findByIdDizimos", query = "SELECT d FROM Dizimos d WHERE d.idDizimos = :idDizimos")
    , @NamedQuery(name = "Dizimos.findByCongregacao", query = "SELECT d FROM Dizimos d WHERE d.congregacao = :congregacao")
    , @NamedQuery(name = "Dizimos.findByFormasDePagamento", query = "SELECT d FROM Dizimos d WHERE d.formasDePagamento = :formasDePagamento")
    , @NamedQuery(name = "Dizimos.findByMontante", query = "SELECT d FROM Dizimos d WHERE d.montante = :montante")
    , @NamedQuery(name = "Dizimos.findByQtdDeParcelas", query = "SELECT d FROM Dizimos d WHERE d.qtdDeParcelas = :qtdDeParcelas")
    , @NamedQuery(name = "Dizimos.findByNumeroDaParcelaAtual", query = "SELECT d FROM Dizimos d WHERE d.numeroDaParcelaAtual = :numeroDaParcelaAtual")
    , @NamedQuery(name = "Dizimos.findByTipoDeTaxas", query = "SELECT d FROM Dizimos d WHERE d.tipoDeTaxas = :tipoDeTaxas")
    , @NamedQuery(name = "Dizimos.findByTaxas", query = "SELECT d FROM Dizimos d WHERE d.taxas = :taxas")
    , @NamedQuery(name = "Dizimos.findByValDoDizimoAtual", query = "SELECT d FROM Dizimos d WHERE d.valDoDizimoAtual = :valDoDizimoAtual")
    , @NamedQuery(name = "Dizimos.findByObs", query = "SELECT d FROM Dizimos d WHERE d.obs = :obs")
    , @NamedQuery(name = "Dizimos.findByDtContratual", query = "SELECT d FROM Dizimos d WHERE d.dtContratual = :dtContratual")
    , @NamedQuery(name = "Dizimos.findByDataDoPgAtual", query = "SELECT d FROM Dizimos d WHERE d.dataDoPgAtual = :dataDoPgAtual")
    , @NamedQuery(name = "Dizimos.findByDtConclusao", query = "SELECT d FROM Dizimos d WHERE d.dtConclusao = :dtConclusao")})
public class Dizimos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dizimos")
    private Integer idDizimos;
    @Column(name = "congregacao")
    private String congregacao;
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
    @Column(name = "valDoDizimoAtual")
    private Double valDoDizimoAtual;
    @Column(name = "obs")
    private String obs;
    @Column(name = "dtContratual")
    private String dtContratual;
    @Column(name = "dataDoPgAtual")
    private String dataDoPgAtual;
    @Column(name = "dtConclusao")
    private String dtConclusao;

    public Dizimos() {
    }

    public Dizimos(Integer idDizimos) {
        this.idDizimos = idDizimos;
    }

    public Integer getIdDizimos() {
        return idDizimos;
    }

    public void setIdDizimos(Integer idDizimos) {
        this.idDizimos = idDizimos;
    }

    public String getCongregacao() {
        return congregacao;
    }

    public void setCongregacao(String congregacao) {
        this.congregacao = congregacao;
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

    public Double getValDoDizimoAtual() {
        return valDoDizimoAtual;
    }

    public void setValDoDizimoAtual(Double valDoDizimoAtual) {
        this.valDoDizimoAtual = valDoDizimoAtual;
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
        hash += (idDizimos != null ? idDizimos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dizimos)) {
            return false;
        }
        Dizimos other = (Dizimos) object;
        if ((this.idDizimos == null && other.idDizimos != null) || (this.idDizimos != null && !this.idDizimos.equals(other.idDizimos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.skep.entity.Dizimos[ idDizimos=" + idDizimos + " ]";
    }
    
}
