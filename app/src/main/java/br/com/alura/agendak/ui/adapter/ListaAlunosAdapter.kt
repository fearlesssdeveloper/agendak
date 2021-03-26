package br.com.alura.agendak.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.alura.agendak.R
import br.com.alura.agendak.model.Aluno
import kotlinx.android.synthetic.main.item_aluno.view.*

class ListaAlunosAdapter(private val context: Context) : BaseAdapter() {

    private val alunos = ArrayList<Aluno>()

    override fun getCount(): Int {
        return alunos.size
    }

    override fun getItem(posicao: Int): Aluno {
        return alunos[posicao]
    }

    override fun getItemId(posicao: Int): Long {
        return alunos[posicao].id.toLong()
    }

    override fun getView(posicao: Int, view: View?, viewGroup: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context)
            .inflate(R.layout.item_aluno, viewGroup, false)

        val alunoDevolvido = alunos[posicao]

        val nome = viewCriada.item_aluno_nome
        nome.text = alunoDevolvido.nome
        val telefone = viewCriada.item_aluno_telefone
        telefone.text = alunoDevolvido.telefone
        return viewCriada
    }

    fun clear() {
        alunos.clear()
    }

    fun addAll(alunos: List<Aluno>) {
        this.alunos.addAll(alunos)
    }

    fun remove(aluno: Aluno) {
        alunos.remove(aluno)
    }
}