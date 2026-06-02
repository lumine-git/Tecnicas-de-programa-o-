package com.example.model;

import com.google.gson.annotations.SerializedName;

/**
 * POJO que representa um sócio ou administrador da empresa.
 * Mapeia os dados do array 'qsa' (Quadro de Sócios e Administradores) da API BrasilAPI.
 */
public class Socio {

    @SerializedName("nome_socio")
    private String nomeSocio;

    @SerializedName("cnpj_cpf_do_socio")
    private String cnpjCpfDoSocio;

    @SerializedName("qualificacao_socio")
    private String qualificacaoSocio;

    // Construtores
    public Socio() {
    }

    public Socio(String nomeSocio, String cnpjCpfDoSocio, String qualificacaoSocio) {
        this.nomeSocio = nomeSocio;
        this.cnpjCpfDoSocio = cnpjCpfDoSocio;
        this.qualificacaoSocio = qualificacaoSocio;
    }

    // Getters e Setters
    public String getNomeSocio() {
        return nomeSocio;
    }

    public void setNomeSocio(String nomeSocio) {
        this.nomeSocio = nomeSocio;
    }

    public String getCnpjCpfDoSocio() {
        return cnpjCpfDoSocio;
    }

    public void setCnpjCpfDoSocio(String cnpjCpfDoSocio) {
        this.cnpjCpfDoSocio = cnpjCpfDoSocio;
    }

    public String getQualificacaoSocio() {
        return qualificacaoSocio;
    }

    public void setQualificacaoSocio(String qualificacaoSocio) {
        this.qualificacaoSocio = qualificacaoSocio;
    }

    @Override
    public String toString() {
        return "Socio{" +
                "nomeSocio='" + nomeSocio + '\'' +
                ", cnpjCpfDoSocio='" + cnpjCpfDoSocio + '\'' +
                ", qualificacaoSocio='" + qualificacaoSocio + '\'' +
                '}';
    }
}
