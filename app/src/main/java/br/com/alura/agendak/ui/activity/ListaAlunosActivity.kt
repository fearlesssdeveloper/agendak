package br.com.alura.agendak.ui.activity

import android.content.Intent
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)
        title = "Lista de Alunos"
        configuraFabNovoAluno()
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
        configuraLista()
    }

    private fun configuraLista() {
        configuraAdapter()
        configuraListenerDeCliquePorItem()
    }

    private fun configuraAdapter() {
        activity_lista_alunos_listview.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, alunos)
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