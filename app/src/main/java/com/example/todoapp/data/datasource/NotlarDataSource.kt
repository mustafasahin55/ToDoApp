package com.example.todoapp.data.datasource

import android.util.Log
import com.example.todoapp.data.entity.Notlar
import com.example.todoapp.room.NotlarDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotlarDataSource(var notlarDao : NotlarDao)
{
	suspend fun notKaydet(not_Baslik:String , not_Aciklama:String , not_Tarih:String){
		val yeniNot = Notlar(0,not_Baslik,not_Aciklama,not_Tarih)
			notlarDao.notKaydet(yeniNot)

	}
	suspend fun notGuncelle(not_id:Int ,not_Baslik:String , not_Aciklama:String , not_Tarih:String){

		val guncellenenNot = Notlar(not_id,not_Baslik,not_Aciklama,not_Tarih)
		notlarDao.notGuncelle(guncellenenNot)

	}

	suspend fun notSil(not_id:Int){
		val silinenNot = Notlar(not_id,"","","")
		notlarDao.notSil(silinenNot)

	}
	suspend fun notlariYukle():List<Notlar> = withContext(Dispatchers.IO){

		return@withContext notlarDao.notlariYukle()

	}

	suspend fun ara(aramaKelimesi: String):List<Notlar> = withContext(Dispatchers.IO){


		return@withContext notlarDao.ara(   aramaKelimesi)

	}


}