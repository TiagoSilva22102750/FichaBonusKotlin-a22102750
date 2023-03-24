package pt.ulusofona.cm.kotlin.challenge.models

import java.awt.Point
import java.text.SimpleDateFormat

class Bicicleta(override val identificador: String) : Veiculo(identificador) {
    override fun requerCarta(): Boolean {
        return false
    }

    override fun moverPara(x: Int, y: Int) {
        posicao.alterarPosicaoPara(x, y)
    }

    override fun toString() : String {
        return "Bicicleta | ${identificador} | ${dataFormatada()} | Posicao | x:${posicao.x} | y:${posicao.y}"
    }

    fun dataFormatada() : String {
        val formato = SimpleDateFormat("dd-MM-yyyy")
        val dataModificada = formato.format(dataDeAquisicao)
        return dataModificada.toString()
    }
}