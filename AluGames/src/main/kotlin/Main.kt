package org.example

import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner


fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite um código de jogo para buscar:")
    val busca = leitura.nextLine()



    if (json == "[]") {
        println("Nenhum Jogo encontrado")
    }else {
        val jogoJson = gson.fromJson(json, InfoJogo::class.java)

//        try {
//            val jogo = Jogo(jogoJson.info.title, jogoJson.info.thumb)
//            println(jogo)
//        } catch (ex: IllegalStateException) {
//            println("Jogo não encontrado")
//        }
        var jogo : Jogo ? = null
        val resultado = runCatching {
            jogo = Jogo(
                jogoJson.info.title,
                jogoJson.info.thumb)
            println(jogo)
        }
        resultado.onFailure {
            println("Jogo não encontrado")
        }

        resultado.onSuccess {
            println("Jogo encontrado")
            println("Deseja inserir uma descrição personalizada? (S/N)")
            val opcao = leitura.nextLine()
            if (opcao.equals("S", true)) {
                println("Digite a descrição:")
                val descricaoPersonalizada = leitura.nextLine()
                jogo?.descricao = descricaoPersonalizada
            }else {
                println("Descrição padrão:")
                jogo?.descricao = jogo?.titulo
            }
            println(jogo)
        }
    }
}