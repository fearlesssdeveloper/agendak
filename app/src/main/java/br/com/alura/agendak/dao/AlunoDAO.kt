package br.com.alura.agendak.dao

import br.com.alura.agendak.model.Aluno

class AlunoDAO {

    val alunos: List<Aluno> = Companion.alunos
    companion object {
        private val alunos: MutableList<Aluno> = mutableListOf()
        private var contadorIds: Int = 1
    }

    fun salva(aluno: Aluno) {
        aluno.id = contadorIds
        Companion.alunos.add(aluno)
        atualizaIds()
    }

    private fun atualizaIds() {
        contadorIds++
    }

    fun edita(aluno: Aluno) {
        var alunoEncontrado: Aluno? = buscaAlunoPeloId(aluno)
        alunoEncontrado?.let {
            val posicao = Companion.alunos.indexOf(alunoEncontrado)
            Companion.alunos[posicao] = aluno
        }
    }

    private fun buscaAlunoPeloId(aluno: Aluno): Aluno? {
        var alunoEncontrado: Aluno? = null
        Companion.alunos.forEach {
            if (aluno.id == it.id) {
                alunoEncontrado = it
            }
        }
        return alunoEncontrado
    }

    fun remove(aluno: Aluno) {
        val alunoDevolvido = buscaAlunoPeloId(aluno)
        alunoDevolvido?.let {
            Companion.alunos.remove(it)
        }
    }

}
