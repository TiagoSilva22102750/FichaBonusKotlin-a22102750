package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.MenorDeIdadeException
import pt.ulusofona.cm.kotlin.challenge.exceptions.PessoaSemCartaException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoNaoEncontradoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date

class Pessoa(val nome: String, val dataDeNascimento: Date) : Movimentavel {
    val veiculos: MutableList<Veiculo> = mutableListOf();
    var carta: Carta? = null;
    val posicao: Posicao = Posicao(0, 0);

    fun comprarVeiculo(veiculo: Veiculo) {
        veiculo.dataDeAquisicao = Date.from(Instant.now())
        veiculos.add(veiculo)
    }

    fun pesquisarVeiculo(identificador: String) : Veiculo {
        for (veiculoAEncontrar in veiculos) {
            if (veiculoAEncontrar.identificador == identificador) {
                return veiculoAEncontrar
            }
        }
        throw VeiculoNaoEncontradoException()
    }

    fun venderVeiculo(identificador: String, comprador : Pessoa) {
        val veiculoAVender = pesquisarVeiculo(identificador)
        veiculos.remove(veiculoAVender)
        comprador.veiculos.add(veiculoAVender)
    }

    fun moverVeiculoPara(identificador: String, x: Int, y: Int) {
        val veiculo = pesquisarVeiculo(identificador)

        if (!temCarta() && veiculo.requerCarta()) {
            throw PessoaSemCartaException(nome)
        }
        veiculo.moverPara(x, y)
        moverPara(x, y)
    }

    fun temCarta(): Boolean {
        return this.carta != null
    }

    fun tirarCarta() {
        if (calcularIdade()) {
            this.carta = Carta()
        }
        throw MenorDeIdadeException()
    }

    fun calcularIdade(): Boolean {
        val now = Date()
        val idadeEmMiliseconds = now.time - dataDeNascimento.time
        val idadeEmAnos = idadeEmMiliseconds / 1000L / 60L / 60L / 24L / 365L
        return idadeEmAnos >= 18L
    }

    fun dataFormatada() : String {
        val formato = SimpleDateFormat("dd-MM-yyyy")
        val dataModificada = formato.format(dataDeNascimento)
        return dataModificada.toString()
    }

    override fun moverPara(x: Int, y: Int) {
        posicao.alterarPosicao(x, y)
    }

    override fun toString() : String {
        return "Pessoa | ${nome} | ${dataFormatada()} | Posicao | x:${posicao.x} | y:${posicao.y}"
    }
}