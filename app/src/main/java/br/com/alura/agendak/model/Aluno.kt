package br.com.alura.agendak.model

class Aluno(val nome: String, val telefone: String, val email: String) {
    override fun toString(): String {
        return nome
    }
}
