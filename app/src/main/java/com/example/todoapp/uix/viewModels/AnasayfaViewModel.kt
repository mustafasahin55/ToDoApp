package com.example.todoapp.uix.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.data.entity.Notlar
import com.example.todoapp.data.repository.NotlarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AnasayfaViewModel @Inject constructor(var notRepository: NotlarRepository) : ViewModel(){
	var notlarListesi = MutableLiveData<List<Notlar>>()

	init
	{
		notlariYukle()
	}

	fun notlariYukle(){
		CoroutineScope(Dispatchers.Main).launch {
			notlarListesi.value = notRepository.notlariYukle()

		}
	}

	fun ara(aramaKelimesi:String){
		CoroutineScope(Dispatchers.Main).launch {
			notlarListesi.value = notRepository.ara(aramaKelimesi)
		}
	}
	fun notSil(not_id:Int){
		CoroutineScope(Dispatchers.Main).launch {
			notRepository.notSil(not_id)
			notlariYukle()
		}

	}






}