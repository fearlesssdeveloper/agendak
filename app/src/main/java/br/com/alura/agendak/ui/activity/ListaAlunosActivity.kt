package br.com.alura.agendak.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.agendak.R
import br.com.alura.agendak.model.Aluno
import br.com.alura.agendak.ui.ListaAlunosView
import br.com.alura.agendak.ui.activity.ConstantesActivities.Companion.CHAVE_ALUNO
import kotlinx.android.synthetic.main.activity_lista_alunos.*

class ListaAlunosActivity : AppCompatActivity() {
    private val listaAlunosView = ListaAlunosView(this)

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
                listaAlunosView.confirmaRemocao(item)
            }
        }
        return super.onContextItemSelected(item)
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
        listaAlunosView.atualizaAlunos()
    }

    private fun configuraLista() {
        val listaDeAlunos = activity_lista_alunos_listview
        listaAlunosView.configuraAdapter(listaDeAlunos)
        configuraListenerDeCliquePorItem(listaDeAlunos)
        registerForContextMenu(listaDeAlunos)
    }

    private fun configuraListenerDeCliquePorItem(listaDeAlunos : ListView) {
        listaDeAlunos.setOnItemClickListener { adapterView, _, posicao, _ ->
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