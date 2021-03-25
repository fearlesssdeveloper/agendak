package br.com.alura.agendak.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.agendak.R
import br.com.alura.agendak.dao.AlunoDAO
import br.com.alura.agendak.model.Aluno
import br.com.alura.agendak.ui.activity.ConstantesActivities.Companion.CHAVE_ALUNO
import kotlinx.android.synthetic.main.activity_lista_alunos.*

class ListaAlunosActivity : AppCompatActivity() {
    private val dao = AlunoDAO()
    private val alunos = dao.alunos
    private lateinit var adapter: ArrayAdapter<Aluno>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)
        title = "Lista de Alunos"
        configuraFabNovoAluno()
        configuraLista()
        dao.salva(Aluno(nome = "José", telefone = "984350068", email = "ze@icloud.com"))
        dao.salva(Aluno(nome = "Anna", telefone = "984030407", email = "anna@icloud.com"))
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
        adapter.clear()
        adapter.addAll(alunos)
    }

    private fun configuraLista() {
        configuraAdapter()
        configuraListenerDeCliquePorItem()
        configuraListenerDeCliqueLongoPorItem()
    }

    private fun configuraListenerDeCliqueLongoPorItem() {
        activity_lista_alunos_listview.setOnItemLongClickListener { adapterView, view, posicao, id ->
            var alunoEscolhido = adapterView.getItemAtPosition(posicao) as Aluno
            remove(alunoEscolhido)
            true
        }
    }

    private fun remove(aluno: Aluno) {
        dao.remove(aluno)
        adapter.remove(aluno)
    }

    private fun configuraAdapter() {
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)
        activity_lista_alunos_listview.adapter = adapter
    }

    private fun configuraListenerDeCliquePorItem() {
        activity_lista_alunos_listview.setOnItemClickListener { adapterView, _, posicao, _ ->
            var alunoEscolhido = adapterView.getItemAtPosition(posicao) as Aluno
            abreFormularioModoEditaAluno(alunoEscolhido)
        }
    }

    private fun abreFormularioModoEditaAluno(aluno: Aluno) {
        val vaiParaFormularioActivity = Intent(this, FormularioAlunoActivity::class.java)
        vaiParaFormularioActivity.putExtra(CHAVE_ALUNO, aluno)
        startActivity(vaiParaFormularioActivity)
    }
}