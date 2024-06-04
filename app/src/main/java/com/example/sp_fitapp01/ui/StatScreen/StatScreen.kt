package com.example.sp_fitapp01.ui.StatScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

//@Dao
//interface FeelingDao {
//    @Query("SELECT COUNT(*) FROM feelings WHERE value = :value")
//    fun getCountOfFeelingsWithValue(value: Int): Int
//}

@Composable
fun StatScreen(navController: NavController) {
    val feelingsCount = 5 // Počet hodnôt feelings


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.weight(0.8f))
            Box(modifier = Modifier) {
                Text(
                    text = "Exercises",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textDecoration = TextDecoration.Underline
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.size(200.dp))

       // Text(text = "Počet záznamov s hodnotou 1: $countOfFeelingsWithValueOne")
    }
}

//@Dao
//interface FeelingDao {
//    @Query("SELECT COUNT(*) FROM feelings WHERE value = 1")
//    fun getCountOfFeelingsWithValueOne(): Int
//}

//fun getNumberOfFeelingsWithValueOne(context: Context): Int {
//    // Inicializujte Room database
//    val db = Room.databaseBuilder(
//        context.applicationContext,
//        AppDatabase::class.java, "feelings_database"
//    ).build()
//
//    // Získajte FeelingDao
//    val feelingDao = db.feelingDao()
//
//    // Zavolajte funkciu na získanie počtu záznamov s hodnotou 1
//    val count = feelingDao.getCountOfFeelingsWithValueOne()
//
//    // Uzavrite databázové pripojenie
//    db.close()
//
//    return count
//}
@Preview
@Composable
fun StatScreenPreview() {
    StatScreen(navController = rememberNavController())
}