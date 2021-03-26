package br.com.alura.agendak.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
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
        dao.salva(Aluno(nome = "Alex", telefone = "984350068", email = "ze@icloud.com"))
        dao.salva(Aluno(nome = "Anna", telefone = "984030407", email = "anna@icloud.com"))

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
                val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
                val alunoEscolhido = adapter.getItem(menuInfo.position)
                alunoEscolhido?.let {
                    remove(it)
                }
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
        atualizaAlunos()
    }

    private fun atualizaAlunos() {
        adapter.clear()
        adapter.addAll(alunos)
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
        adapter = ArrayAdapter(this, R.layout.item_aluno)
        activity_lista_alunos_listview.adapter = object : BaseAdapter(){

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
                val viewCriada = LayoutInflater.from(this@ListaAlunosActivity)
                    .inflate(R.layout.item_aluno, viewGroup)
                return viewCriada
            }

        }
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