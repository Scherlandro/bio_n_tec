
package br.com.skep.entity;

public class Endereco {
    private Integer id_endereço;
    private String cep;
    private String logradouro;
    private String TipoLogradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private int resultado = 0;

    public Integer getId_endereço() {
        return id_endereço;
    }

    public void setId_endereço(Integer id_endereço) {
        this.id_endereço = id_endereço;
    }
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    
    public String getTipoLogradouro() {
        return TipoLogradouro;
    }

    public void setTipoLogradouro(String TipoLogradouro) {
        this.TipoLogradouro = TipoLogradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }


    
}
