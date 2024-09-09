package com.example.todoapp.uix.viewModels

import androidx.lifecycle.ViewModel
import com.example.todoapp.data.repository.NotlarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotEkleViewModel @Inject constructor(var notRepository: NotlarRepository) : ViewModel(){


	fun notKaydet(not_Baslik:String , not_Aciklama:String , not_Tarih:String){
		CoroutineScope(Dispatchers.Main).launch {
			notRepository.notKaydet(not_Baslik,not_Aciklama,not_Tarih)
		}

	}



}