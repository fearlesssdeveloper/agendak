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
        val viewCriada = criaView(viewGroup)

        val alunoDevolvido = alunos[posicao]
        vincula(viewCriada, alunoDevolvido)
        return viewCriada
    }

    private fun vincula(viewC: View, aluno: Aluno) {
        val nome = viewC.item_aluno_nome
        nome.text = aluno.nome
        val telefone = viewC.item_aluno_telefone
        telefone.text = aluno.telefone
    }

    private fun criaView(viewGroup: ViewGroup?) = LayoutInflater
        .from(context)
        .inflate(R.layout.item_aluno, viewGroup, false)

    fun atualiza(alunos: List<Aluno>) {
        this.alunos.clear()
        this.alunos.addAll(alunos)
        notifyDataSetChanged()
    }

    fun remove(aluno: Aluno) {
        alunos.remove(aluno)
        notifyDataSetChanged()
    }
}