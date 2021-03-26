package br.com.alura.agendak.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.alura.agendak.R
import br.com.alura.agendak.model.Aluno

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
        return LayoutInflater.from(context)
            .inflate(R.layout.item_aluno, viewGroup, false)
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