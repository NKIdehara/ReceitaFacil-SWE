package br.edu.infnet.receitafacil_swe.receitas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReceitasViewModel : ViewModel() {

    val receitas: MutableLiveData<ArrayList<Receita>> by lazy{
        MutableLiveData<ArrayList<Receita>>()
    }

}
