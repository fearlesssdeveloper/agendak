package br.com.alura.agendak.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.alura.agendak.R
import br.com.alura.agendak.dao.AlunoDAO
import br.com.alura.agendak.model.Aluno
import kotlinx.android.synthetic.main.activity_formulario_aluno.*

class FormularioAlunoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_aluno)

        val dao = AlunoDAO()

        val campoNome = activity_formulario_aluno_nome
        val campoTelefone = activity_formulario_aluno_telefone
        val campoEmail = activity_formulario_aluno_email

        activity_formulario_aluno_button.setOnClickListener {
            var nome = campoNome.text.toString().trim()
            var telefone = campoTelefone.text.toString().trim()
            var email = campoEmail.text.toString().trim()

            val alunoCriado = Aluno(nome, telefone, email)
            dao.salva(alunoCriado)
            val intent = Intent(this, ListaAlunosActivity::class.java)
            startActivity(intent)
        }
    }
}