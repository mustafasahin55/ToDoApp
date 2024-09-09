package com.example.todoapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.data.entity.Notlar

@Dao
interface NotlarDao

{
	@Insert
	suspend fun notKaydet(not:Notlar)


	@Query("SELECT * FROM Notlar")
	suspend fun notlariYukle():List<Notlar>

	@Update
	suspend fun notGuncelle(not:Notlar)

	@Delete
	suspend fun notSil(not:Notlar)

	@Query("SELECT * FROM Notlar WHERE not_Baslik like '%' || :aramaKelimesi || '%'")
	suspend fun ara(aramaKelimesi:String):List<Notlar>


}