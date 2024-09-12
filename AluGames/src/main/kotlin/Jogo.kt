package org.example

data class Jogo(val titulo : String,
           val capa : String
) {
    var descricao:String? = null

    override fun toString(): String {
        return "Meu Jogo \n" +
                "titulo='$titulo'\n" +
                "capa='$capa'\n" +
                "descricao='$descricao'"
    }


}