package com.example.todoapp.uix.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.data.entity.Notlar
import com.google.gson.Gson

@Composable
fun Anasayfa(navController : NavController)
{
	val notListe = mutableListOf<Notlar>()
	val not1 = Notlar(1 , "AA" , "SS" , "DD")
	val not2 = Notlar(2 , "AB" , "SS" , "DD")
	notListe.add(not1)
	notListe.add(not2)

	Scaffold(modifier = Modifier.fillMaxSize() ,
		floatingActionButton = {FloatingActionButton(onClick = {  navController.navigate("NotEkle") } ,
			content = {
			Icon(painter = painterResource(id = R.drawable.baseline_note_add_24) , contentDescription = "")
			}
		)},




	) {


			paddingValues ->
		LazyColumn(modifier = Modifier.padding(paddingValues)) {


			items(count = notListe.count() , itemContent = {
				val not = notListe[it]

				Card(modifier = Modifier
					.padding(5.dp)
					.clickable {
					val notJson = Gson().toJson(not)
						navController.navigate("NotDuzenle/${notJson}")


					}) {
					Column(
						modifier = Modifier
							.fillParentMaxWidth()
							.padding(10.dp)


					) {

						Text(text = not.not_Baslik , fontSize = 20.sp)
						Text(text = not.not_Aciklama , fontSize = 15.sp)
						Text(text = not.not_Tarih , fontSize = 15.sp)


					}
				}


			})


		}


	}
}