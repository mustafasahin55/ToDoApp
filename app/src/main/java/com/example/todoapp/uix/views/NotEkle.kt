package com.example.todoapp.uix.views

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.uix.viewModels.NotEkleViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotEkle(navController: NavController,notEkleViewModel: NotEkleViewModel) {
	val not_baslik = remember { mutableStateOf("") }
	val notAciklama = remember { mutableStateOf("") }
	var currentTime = remember { mutableStateOf("") }


	Scaffold(topBar = { TopAppBar(title = { Text(text = "Not Ekle") }) }) {paddingValues ->
		
		Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.SpaceEvenly,
			 modifier = Modifier.padding(paddingValues).fillMaxSize()) {
			
			TextField(value = not_baslik.value , onValueChange = {not_baslik.value = it} , label = { Text(text = "Not Başlığı") } )
			TextField(modifier = Modifier.size(300.dp,300.dp), value = notAciklama.value , onValueChange = {notAciklama.value = it} , label = { Text(text = "Not Açıklama") } )

			Button(onClick = {
				val now = LocalDateTime.now()
				val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
				currentTime.value = now.format(formatter)


				notEkleViewModel.notKaydet(not_baslik.value,notAciklama.value, not_Tarih = "${currentTime.value}")
				navController.navigate("Anasayfa")

			},
				modifier = Modifier.size(250.dp,50.dp)
			) {
				Text(text = "Kaydet")
			}
		}
		
	}

}