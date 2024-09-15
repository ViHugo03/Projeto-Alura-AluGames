package org.example.br.com.alura.alugames.servicos

import com.google.gson.Gson
import org.example.br.com.alura.alugames.modelo.InfoJogo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ConsumoApi {

    fun buscaJogo(id: String): InfoJogo {
        val client = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://alugames.herokuapp.com/jogos?busca=$id"))
            .build()
        val response = client.send(request,BodyHandlers.ofString())

        val json = response.body()

        val gson = Gson()

        val jogoJson = gson.fromJson(json, InfoJogo::class.java)

        return jogoJson
    }
}