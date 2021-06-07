package com.juliana.agenda

open class Contato(nomeContato : String,
              celularContato : String,
              var referenciaContato : String) :
    Pessoa(nomeContato, celularContato){

    open fun exibirRegistro() : String{
        return "$nomePessoa - $celularPessoa - $referenciaContato \n"
    }
}