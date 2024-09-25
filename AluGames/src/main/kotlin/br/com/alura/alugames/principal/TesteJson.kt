package br.com.alura.alugames.principal

import org.example.br.com.alura.alugames.servicos.ConsumoApi


fun main() {
    val consumoApi = ConsumoApi()
    val listaGamers = consumoApi.buscaGamers()
    println(listaGamers)

}