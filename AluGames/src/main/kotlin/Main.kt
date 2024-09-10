package org.example

import com.google.gson.Gson
import java.lang.IllegalStateException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner


fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite o id do jogo")

    val busca = leitura.nextLine()
    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$busca"

    val client: HttpClient = HttpClient.newHttpClient()

    val request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
        .build()

    val response = client
        .send(request, BodyHandlers.ofString())

    val json = response.body()

    val gson = Gson()
    val JogoJson = gson.fromJson(json, InfoJogo::class.java)
    println(JogoJson)

    try {
        val jogo = Jogo(JogoJson.info.title, JogoJson.info.thumb)
        println(jogo)
    } catch (e: IllegalStateException) {
        println("Jogo não encontrado")
    }
}