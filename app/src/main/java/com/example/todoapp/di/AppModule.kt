package com.example.todoapp.di

import android.content.Context
import androidx.room.Room
import com.example.todoapp.data.datasource.NotlarDataSource
import com.example.todoapp.data.repository.NotlarRepository
import com.example.todoapp.room.NotlarDao
import com.example.todoapp.room.Veritabani
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule
{
	@Provides
	@Singleton
	fun provideNotlarDataSource(nDao: NotlarDao) : NotlarDataSource{
		return NotlarDataSource(nDao)

	}

	@Provides
	@Singleton
	fun provideNotlarRepository(dataSource: NotlarDataSource) : NotlarRepository
	{
		return NotlarRepository(dataSource)
	}

	@Provides
	@Singleton
	fun provideNotlarDao(@ApplicationContext context :Context):NotlarDao{
		val vt = Room.databaseBuilder(context, Veritabani::class.java,"notlar.sqlite").createFromAsset("notlar.sqlite").build()
		return vt.getNotlarDao()
	}




}