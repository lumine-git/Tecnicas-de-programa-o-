package com.example.client;

import com.example.model.Empresa;
import com.google.gson.Gson;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;

/**
 * Cliente responsável por consumir a API BrasilAPI.
 * Isolaliza a lógica de chamada HTTP e parsing JSON.
 */
public class BrasilAPIClient {

    private static final String API_BASE_URL = "https://brasilapi.com.br/api/cnpj/v1/";
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    /**
     * Consulta os dados de uma empresa pelo CNPJ.
     * Remove pontos e traços do CNPJ antes de fazer a requisição.
     *
     * @param cnpj o número do CNPJ (com ou sem formatação)
     * @return objeto Empresa com os dados da API ou null se não encontrado
     * @throws IOException se houve erro na comunicação HTTP
     * @throws InterruptedException se a requisição foi interrompida
     */
    public static Empresa buscarEmpresa(String cnpj) throws IOException, InterruptedException {
        // Limpar o CNPJ: remover pontos, traços e outros caracteres
        String cnpjLimpo = cnpj.replaceAll("[^0-9]", "");

        // Validar se tem 14 dígitos
        if (cnpjLimpo.length() != 14) {
            throw new IllegalArgumentException("CNPJ deve conter exatamente 14 dígitos. Recebido: " + cnpjLimpo);
        }

        String url = API_BASE_URL + cnpjLimpo;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar se a resposta foi 200 (OK)
            if (response.statusCode() == 200) {
                // Parsing do JSON para objeto Empresa
                Empresa empresa = gson.fromJson(response.body(), Empresa.class);
                return empresa;
            } else if (response.statusCode() == 404) {
                System.err.println("CNPJ não encontrado na API: " + cnpjLimpo);
                return null;
            } else {
                System.err.println("Erro na API - Status: " + response.statusCode());
                System.err.println("Resposta: " + response.body());
                return null;
            }

        } catch (IOException e) {
            System.err.println("Erro de comunicação com a API: " + e.getMessage());
            throw e;
        } catch (InterruptedException e) {
            System.err.println("Requisição foi interrompida: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retorna o JSON bruto da API (útil para debug ou armazenamento)
     *
     * @param cnpj o número do CNPJ
     * @return String com o JSON retornado pela API
     * @throws IOException se houve erro na comunicação HTTP
     * @throws InterruptedException se a requisição foi interrompida
     */
    public static String buscarJsonBruto(String cnpj) throws IOException, InterruptedException {
        String cnpjLimpo = cnpj.replaceAll("[^0-9]", "");

        if (cnpjLimpo.length() != 14) {
            throw new IllegalArgumentException("CNPJ deve conter exatamente 14 dígitos. Recebido: " + cnpjLimpo);
        }

        String url = API_BASE_URL + cnpjLimpo;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            return null;
        }
    }
}
