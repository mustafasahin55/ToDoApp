package com.example.todoapp.uix.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todoapp.R
import com.example.todoapp.data.entity.Notlar
import com.example.todoapp.uix.viewModels.AnasayfaViewModel
import com.google.gson.Gson
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController : NavController , anasayfaViewModel : AnasayfaViewModel)
{
	val aramaYapiliyorMu = remember { mutableStateOf(false) }
	val notListe = anasayfaViewModel.notlarListesi.observeAsState(listOf())
	val scope = rememberCoroutineScope()
	val snackbarHostState = remember { SnackbarHostState() }
	val tf = remember { mutableStateOf("") }

	LaunchedEffect(key1 = true) { anasayfaViewModel.notlariYukle() }

	Scaffold(
		modifier = Modifier.fillMaxSize() ,
		topBar = {
			TopAppBar(
				title = {
					if (aramaYapiliyorMu.value)
					{
						TextField(
							value = tf.value ,
							onValueChange = { tf.value = it } ,
							label = { Text(text = "Ara") } ,
							colors = TextFieldDefaults.textFieldColors(
								containerColor = Color.Transparent ,
								focusedLabelColor = Color.White ,
								unfocusedLabelColor = Color.Black ,
								unfocusedIndicatorColor = Color.White
							)
						)
						anasayfaViewModel.ara(tf.value)
					}
					else
					{
						Text(text = "Notların")
					}

				} ,
				actions = {
					if (aramaYapiliyorMu.value)
					{
						IconButton(onClick = {
							aramaYapiliyorMu.value = false
							tf.value = ""
						}) {
							Icon(
								painter = painterResource(id = R.drawable.search_off) ,
								contentDescription = ""
							)
						}


					}
					else
					{
						IconButton(onClick = { aramaYapiliyorMu.value = true }) {
							Icon(
								painter = painterResource(id = R.drawable.search) ,
								contentDescription = ""
							)
						}
					}
				}

			)
		} ,
		floatingActionButton = {
			FloatingActionButton(onClick = { navController.navigate("NotEkle") }) {
				Icon(
					painter = painterResource(id = R.drawable.baseline_note_add_24) ,
					contentDescription = ""
				)
			}
		} ,

		snackbarHost = { SnackbarHost(hostState = snackbarHostState) }


	) {


			paddingValues ->
		LazyVerticalGrid(
			columns = GridCells.Fixed(2) , // 2 sütun olacak şekilde sabitliyoruz
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues) ,
		) {


			items(count = notListe.value.count() , itemContent = {
				val not = notListe.value[it]

				Card(
					modifier = Modifier
						.fillMaxWidth()
						.padding(5.dp)
						.size(240.dp)
						.clickable {
							val notJson = Gson().toJson(not)
							navController.navigate("NotDuzenle/${notJson}")
						}
				) {
					Column(

						modifier = Modifier.padding()) {
						Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
							.fillMaxWidth()
							.padding(start = 14.dp)) {
							Text(text = "${not.not_Baslik}" , fontSize = 24.sp, color = Color.Black, fontWeight = FontWeight.Bold,modifier = Modifier.fillMaxWidth().weight(1f),maxLines = 1,overflow = TextOverflow.Ellipsis,)
							IconButton(onClick = {
								scope.launch {
									val sb = snackbarHostState.showSnackbar(
										message = "${not.not_Baslik} silinsin mi?" , "EVET"
									)
									if (sb == SnackbarResult.ActionPerformed)
									{
										anasayfaViewModel.notSil(not_id = not.not_ID)
									}

								}


							}) {
								Icon(
									painter = painterResource(id = R.drawable.close) ,
									contentDescription = "" ,
									tint = Color.Gray
								)


							}
						}
						Column(Modifier.padding(start = 10.dp).padding(bottom = 10.dp), verticalArrangement = Arrangement.SpaceEvenly) {
							Text(text = not.not_Aciklama , fontSize = 16.sp, modifier = Modifier.weight(1f).fillMaxWidth(),maxLines = 7,overflow = TextOverflow.Ellipsis)
							Text(text = not.not_Tarih , fontSize = 12.sp)

						}




					}
				//Card item sonu
				}


			})


		}


	}
}