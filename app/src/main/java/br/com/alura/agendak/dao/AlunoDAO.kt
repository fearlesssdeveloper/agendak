package br.com.alura.agendak.dao

import br.com.alura.agendak.model.Aluno

class AlunoDAO {

    val alunos: List<Aluno> = Companion.alunos
    companion object {
        private val alunos: MutableList<Aluno> = mutableListOf()
    }

    fun salva(aluno: Aluno) {
        Companion.alunos.add(aluno)
    }

}
