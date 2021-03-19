package br.com.alura.agendak

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val alunos = listOf<String>("Alex","Fran","José")
        activity_main_lista_de_alunos.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, alunos)
    }
}