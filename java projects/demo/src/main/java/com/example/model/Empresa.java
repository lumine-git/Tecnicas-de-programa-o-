package com.example.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * POJO que representa uma empresa conforme retornado pela API BrasilAPI.
 * Mapeia os dados principais da empresa e contém uma lista de sócios (QSA).
 */
public class Empresa {

    @SerializedName("cnpj")
    private String cnpj;

    @SerializedName("razao_social")
    private String razaoSocial;

    @SerializedName("nome_fantasia")
    private String nomeFantasia;

    @SerializedName("logradouro")
    private String logradouro;

    @SerializedName("numero")
    private String numero;

    @SerializedName("complemento")
    private String complemento;

    @SerializedName("bairro")
    private String bairro;

    @SerializedName("municipio")
    private String municipio;

    @SerializedName("uf")
    private String uf;

    @SerializedName("cep")
    private String cep;

    @SerializedName("cnae_fiscal")
    private String cnaeFiscal;

    @SerializedName("cnae_fiscal_descricao")
    private String cnaeFiscalDescricao;

    @SerializedName("qsa")
    private List<Socio> qsa;

    // Construtores
    public Empresa() {
    }

    public Empresa(String cnpj, String razaoSocial, String nomeFantasia) {
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
    }

    // Getters e Setters
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCnaeFiscal() {
        return cnaeFiscal;
    }

    public void setCnaeFiscal(String cnaeFiscal) {
        this.cnaeFiscal = cnaeFiscal;
    }

    public String getCnaeFiscalDescricao() {
        return cnaeFiscalDescricao;
    }

    public void setCnaeFiscalDescricao(String cnaeFiscalDescricao) {
        this.cnaeFiscalDescricao = cnaeFiscalDescricao;
    }

    public List<Socio> getQsa() {
        return qsa;
    }

    public void setQsa(List<Socio> qsa) {
        this.qsa = qsa;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "cnpj='" + cnpj + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", cnaeFiscal='" + cnaeFiscal + '\'' +
                ", qsa=" + (qsa != null ? qsa.size() + " sócios" : "null") +
                '}';
    }
}
