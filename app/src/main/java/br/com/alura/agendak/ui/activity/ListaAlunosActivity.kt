package br.com.alura.agendak.ui.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.agendak.R
import kotlinx.android.synthetic.main.activity_lista_alunos.*

class ListaAlunosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)
        title = "Lista de Alunos"
        val alunos = listOf<String>("Alex","Fran","José")
        activity_lista_alunos_listview.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, alunos)

    }
}