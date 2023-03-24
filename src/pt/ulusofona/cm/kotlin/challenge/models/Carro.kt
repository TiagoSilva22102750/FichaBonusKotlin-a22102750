package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoLigadoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Ligavel

class Carro(override val identificador: String, val motor: Motor) : Veiculo(identificador) {
    override fun requerCarta(): Boolean {
        return true
    }

    override fun moverPara(x: Int, y: Int) {
        posicao.alterarPosicaoPara(x, y)
    }

    override fun toString() : String {
        return "Carro | ${identificador} | ${dataDeAquisicao} | Posicao | x:${posicao.x} | y:${posicao.y}"
    }
}