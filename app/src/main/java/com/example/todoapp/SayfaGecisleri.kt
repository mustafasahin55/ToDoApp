package com.example.todoapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoapp.data.entity.Notlar
import com.example.todoapp.uix.views.Anasayfa
import com.example.todoapp.uix.views.NotDuzenle
import com.example.todoapp.uix.views.NotEkle
import com.google.gson.Gson

@Composable
fun SayfaGecisleri(){
	val navController = rememberNavController()
	NavHost(navController = navController , startDestination = "Anasayfa"  ){
		composable("Anasayfa"){
			Anasayfa(navController = navController)
		}
		composable("NotEkle"){
			NotEkle(navController = navController)
		}
		composable("NotDuzenle/{not}",
			arguments = listOf(
				navArgument("not") {type = NavType.StringType})){
			val json = it.arguments?.getString("not")
			val not = Gson().fromJson(json, Notlar::class.java)

			NotDuzenle(not,navController = navController)
		}



	}



}