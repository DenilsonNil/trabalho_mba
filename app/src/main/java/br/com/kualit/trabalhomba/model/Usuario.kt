package br.com.kualit.trabalhomba.model

data class Usuario(val nome: String, val email: String) {

    fun nome(): String {
       return this.nome
    }
    fun email (): String {
        return this.email
    }
}
