package com.example.todoapp.data.repository

import com.example.todoapp.data.datasource.NotlarDataSource
import com.example.todoapp.data.entity.Notlar

class NotlarRepository(var dataSource : NotlarDataSource)
{


	suspend fun notKaydet(not_Baslik:String , not_Aciklama:String , not_Tarih:String) = dataSource.notKaydet(not_Baslik,not_Aciklama,not_Tarih)
	suspend fun notGuncelle(not_id:Int ,not_Baslik:String , not_Aciklama:String , not_Tarih:String) = dataSource.notGuncelle(not_id,not_Baslik,not_Aciklama,not_Tarih)
	suspend fun notSil(not_id:Int) = dataSource.notSil(not_id)
	suspend fun notlariYukle():List<Notlar> = dataSource.notlariYukle()
	suspend fun ara(aramaKelimesi:String):List<Notlar> = dataSource.ara(aramaKelimesi)

}