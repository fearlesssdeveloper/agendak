package br.com.alura.agendak

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val alunos = listOf<String>("Alex","Fran","José")
        textView1.text = alunos[0]
        textView2.text = alunos[1]
        textView3.text = alunos[2]
    }
}