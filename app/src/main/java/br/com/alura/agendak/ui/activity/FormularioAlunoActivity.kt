package br.com.alura.agendak.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.agendak.R
import br.com.alura.agendak.dao.AlunoDAO
import br.com.alura.agendak.model.Aluno
import br.com.alura.agendak.ui.activity.ConstantesActivities.Companion.CHAVE_ALUNO
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_formulario_aluno.*

private const val TITULO_APPBAR_NOVO_ALUNO: String = "Novo aluno"
private const val TITULO_APPBAR_EDITA_ALUNO: String = "Edita aluno"

class FormularioAlunoActivity : AppCompatActivity() {
    private lateinit var campoNome: TextInputEditText
    private lateinit var campoTelefone: TextInputEditText
    private lateinit var campoEmail: TextInputEditText
    private val dao = AlunoDAO()
    private lateinit var aluno: Aluno

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_aluno)
        inicializacaoDosCampos()
        configuraBotaoSalvar()
        carregaAluno()
    }

    private fun carregaAluno() {
        val dados = intent
        if (dados.hasExtra(CHAVE_ALUNO)) {
            title = TITULO_APPBAR_EDITA_ALUNO
            aluno = dados.getParcelableExtra(CHAVE_ALUNO)!!
            preencheCampos()
        } else {
            title = TITULO_APPBAR_NOVO_ALUNO
            aluno = Aluno()
        }
    }

    private fun preencheCampos() {
        campoNome.setText(aluno.nome)
        campoTelefone.setText(aluno.telefone)
        campoEmail.setText(aluno.email)
    }

    private fun configuraBotaoSalvar() {
        activity_formulario_aluno_button.setOnClickListener {
            finalizaFormulario()
        }
    }

    private fun finalizaFormulario() {
        preencheAluno()
        if (aluno.temIdValido()) {
            dao.edita(aluno)
        } else {
            dao.salva(aluno)
        }
        finish()
    }

    private fun inicializacaoDosCampos() {
        campoNome = activity_formulario_aluno_nome
        campoTelefone = activity_formulario_aluno_telefone
        campoEmail = activity_formulario_aluno_email
    }

    private fun preencheAluno() {
        val nome = campoNome.text.toString().trim()
        val telefone = campoTelefone.text.toString().trim()
        val email = campoEmail.text.toString().trim()

        aluno.nome = nome
        aluno.telefone = telefone
        aluno.email = email
    }
}