package br.com.alura.agendak

import android.app.Application
import br.com.alura.agendak.dao.AlunoDAO
import br.com.alura.agendak.model.Aluno

class AgendaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        criaAlunosDeTeste()
//        try {
//            Thread.sleep(2000)
//        } catch (e: InterruptedException){
//            e.printStackTrace()
//        }
        /**
         * Pequeno teste para ver a demora ao inicializar o app, qualquer tarefa qeu demanda
         * tempo durante a criação da Application afeta o tempo de exibição Activity laucher.
         */
    }

    private fun criaAlunosDeTeste() {
        val dao = AlunoDAO()
        dao.salva(Aluno(nome = "Alex", telefone = "984350068", email = "ze@icloud.com"))
        dao.salva(Aluno(nome = "Anna", telefone = "984030407", email = "anna@icloud.com"))
    }
}