package br.com.alura.agendak.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Aluno(var nome: String, var telefone: String, var email: String) : Parcelable{

    override fun toString(): String {
        return nome
    }
}
