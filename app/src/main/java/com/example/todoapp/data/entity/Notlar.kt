package com.example.todoapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "Notlar")
class Notlar(
	@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "not_ID") @NotNull var not_ID : Int ,
	@ColumnInfo(name = "not_Baslik") @NotNull var not_Baslik : String ,
	@ColumnInfo(name = "not_Aciklama") @NotNull var not_Aciklama : String ,
	@ColumnInfo(name = "not_Tarih") @NotNull var not_Tarih : String
)

