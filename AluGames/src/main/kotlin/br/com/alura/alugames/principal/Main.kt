package org.example.br.com.alura.alugames.principal

import org.example.br.com.alura.alugames.modelo.Jogo
import org.example.br.com.alura.alugames.servicos.ConsumoApi
import java.util.*


fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite um código de jogo para buscar:")
    val busca = leitura.nextLine()

    val buscaApi = ConsumoApi()
    val jogoJson = buscaApi.buscaJogo(busca)

    var jogo : Jogo? = null
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