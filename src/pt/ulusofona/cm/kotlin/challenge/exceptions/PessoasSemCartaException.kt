package pt.ulusofona.cm.kotlin.challenge.exceptions

class PessoasSemCartaException(var nome : String) : Exception("$nome não tem carta para conduzir o veículo indicado")