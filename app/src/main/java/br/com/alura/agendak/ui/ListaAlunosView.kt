package br.com.alura.agendak.ui

import android.app.AlertDialog
import android.content.Context
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import br.com.alura.agendak.dao.AlunoDAO
import br.com.alura.agendak.model.Aluno
import br.com.alura.agendak.ui.adapter.ListaAlunosAdapter

class ListaAlunosView(private val context: Context) {

    private val dao = AlunoDAO()
    private val alunos = dao.alunos
    private lateinit var adapter: ListaAlunosAdapter

    fun confirmaRemocao(item: MenuItem) {
        AlertDialog.Builder(context)
            .setTitle("Removendo aluno")
            .setMessage("Tem certeza que quer remover o aluno?")
            .setPositiveButton("Sim") { _, _ ->
                val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val alunoEscolhido = adapter.getItem(menuInfo.position)
                remove(alunoEscolhido)
            }
            .setNegativeButton("Não", null)
            .show()
    }

    fun configuraAdapter(listaDeAlunos : ListView) {
        adapter = ListaAlunosAdapter(context)
        listaDeAlunos.adapter = adapter
    }

    fun atualizaAlunos() {
        adapter.atualiza(alunos)
    }

    private fun remove(aluno: Aluno) {
        dao.remove(aluno)
        adapter.remove(aluno)
    }

}