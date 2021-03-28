package br.com.alura.agendak.ui.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.agendak.R
import br.com.alura.agendak.dao.AlunoDAO
import br.com.alura.agendak.model.Aluno
import br.com.alura.agendak.ui.activity.ConstantesActivities.Companion.CHAVE_ALUNO
import br.com.alura.agendak.ui.adapter.ListaAlunosAdapter
import kotlinx.android.synthetic.main.activity_lista_alunos.*

class ListaAlunosActivity : AppCompatActivity() {
    private val dao = AlunoDAO()
    private val alunos = dao.alunos
    private lateinit var adapter: ListaAlunosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)
        title = "Lista de Alunos"
        configuraFabNovoAluno()
        configuraLista()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.activity_lista_alunos_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.activity_lista_alunos_menu_remover -> {
                confirmaRemocao(item)
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun confirmaRemocao(item: MenuItem) {
        AlertDialog.Builder(this)
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

    private fun configuraFabNovoAluno() {
        activity_lista_alunos_fab_novo_aluno.setOnClickListener {
            abreFormularioModoInsereAluno()
        }
    }

    private fun abreFormularioModoInsereAluno() {
        startActivity(Intent(this, FormularioAlunoActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        atualizaAlunos()
    }

    private fun atualizaAlunos() {
        adapter.atualiza(alunos)
    }

    private fun configuraLista() {
        configuraAdapter()
        configuraListenerDeCliquePorItem()
        registerForContextMenu(activity_lista_alunos_listview)
    }

    private fun remove(aluno: Aluno) {
        dao.remove(aluno)
        adapter.remove(aluno)
    }

    private fun configuraAdapter() {
        adapter = ListaAlunosAdapter(this)
        activity_lista_alunos_listview.adapter = adapter
    }

    private fun configuraListenerDeCliquePorItem() {
        activity_lista_alunos_listview.setOnItemClickListener { adapterView, _, posicao, _ ->
            val alunoEscolhido = adapterView.getItemAtPosition(posicao) as Aluno
            abreFormularioModoEditaAluno(alunoEscolhido)
        }
    }

    private fun abreFormularioModoEditaAluno(aluno: Aluno) {
        val vaiParaFormularioActivity = Intent(this, FormularioAlunoActivity::class.java)
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno)
        startActivity(vaiParaFormularioActivity)
    }
}