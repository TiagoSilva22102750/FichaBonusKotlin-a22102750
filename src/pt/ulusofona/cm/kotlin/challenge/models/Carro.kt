package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoLigadoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Ligavel
import java.text.SimpleDateFormat

class Carro(override val identificador: String, val motor: Motor) : Veiculo(identificador), Ligavel {
    override fun requerCarta(): Boolean {
        return true
    }

    override fun moverPara(x: Int, y: Int) {
        if (estaLigado()) {
            posicao.alterarPosicaoPara(x, y)
            desligar()
            return
        }
        ligar()
        posicao.alterarPosicaoPara(x, y)
        desligar()
    }

    override fun ligar() {
        motor.ligar()
    }

    override fun desligar() {
        motor.desligar()
    }

    override fun estaLigado(): Boolean {
        return motor.estaLigado()
    }

    override fun toString() : String {
        return "Carro | ${identificador} | ${dataFormatada()} | ${posicao}"
    }

    fun dataFormatada() : String {
        val formato = SimpleDateFormat("dd-MM-yyyy")
        val dataModificada = formato.format(dataDeAquisicao)
        return dataModificada.toString()
    }
}