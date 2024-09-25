package br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import org.example.br.com.alura.alugames.modelo.Jogo
import org.example.br.com.alura.alugames.servicos.ConsumoApi
import transformarEmIdade
import java.util.*


fun main() {

    val leitura = Scanner(System.`in`)

    val gamer = Gamer.criarGamer(leitura)
    println("Cadastro realizado com sucesso. Dados do Gamer:")
    println(gamer)
    println("Idade do gamer: " + gamer.dataNascimento?.transformarEmIdade())

    do {
        println("Digite um código de jogo para buscar:")
        val busca = leitura.nextLine()
        val buscaApi = ConsumoApi()
        val jogoJson = buscaApi.buscaJogo(busca)

        var jogo: Jogo? = null
        val resultado = runCatching {
            jogo = Jogo(
                jogoJson.info.title,
                jogoJson.info.thumb
            )
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
            } else {
                println("Descrição padrão:")
                jogo?.descricao = jogo?.titulo
            }

            gamer.jogosBuscados.add(jogo)
        }
        println("Deseja buscar outro jogo? (S/N)")
        val resposta = leitura.nextLine()
    } while (resposta.equals("S", true))

    println("Jogos buscadados:")
    println(gamer.jogosBuscados)
    println("Jogos ordenados por Titulo:")
    println(gamer.jogosBuscados.sortedBy { it?.titulo })

    gamer.jogosBuscados.forEach {
        println(it?.titulo)
    }

    val filtrados = gamer.jogosBuscados.filter {
        it?.titulo?.contains("Resident Evil 5", true) ?: false
    }

    println("Jogos filtrados:")
    println(filtrados)

    println("Deseja excluir algum jogo? (S/N)")
    val excluir = leitura.nextLine()

    if (excluir.equals("S", true)) {
        println("\n" + gamer.jogosBuscados)
        println("Digite a posição do jogo a ser excluído:")
        val posicao = leitura.nextInt()

        gamer.jogosBuscados.removeAt(posicao)
    }

    println("Jogos Atualizados:")

    println(gamer.jogosBuscados)

    println("Busca finalizada com sucesso")



}