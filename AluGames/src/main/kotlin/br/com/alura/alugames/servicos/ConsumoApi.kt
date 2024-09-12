package org.example.br.com.alura.alugames.servicos

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest

class ConsumoApi {

    fun buscaJogo(busca: String): String {
        val client = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://alugames.herokuapp.com/jogos?busca=$busca"))
            .build()
        val response = client.send(request, BodyHandlers.ofString())
        return response.body()
    }
}