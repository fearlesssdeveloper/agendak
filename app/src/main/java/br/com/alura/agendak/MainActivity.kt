package br.com.alura.agendak

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Lista de Alunos"
        val alunos = listOf<String>("Alex","Fran","José")
        activity_main_lista_de_alunos.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, alunos)

    }
}