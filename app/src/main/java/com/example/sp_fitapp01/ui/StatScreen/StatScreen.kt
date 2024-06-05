package com.example.sp_fitapp01.ui.StatScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

//@Dao
//interface FeelingDao {
//    @Query("SELECT COUNT(*) FROM feelings WHERE value = :value")
//    fun getCountOfFeelingsWithValue(value: Int): Int
//}

@Composable
fun StatScreen(navController: NavController) {
    val donutChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("HP", 15f, Color(0xFF5F0A87)),
            PieChartData.Slice("Dell", 30f, Color(0xFF20BF55)),
            PieChartData.Slice("Lenovo", 40f, Color(0xFFEC9F05)),
            PieChartData.Slice("Asus", 10f, Color(0xFFF53844))
        ),
        plotType = PlotType.Donut
    )
    val donutChartConfig = PieChartConfig(
        strokeWidth = 120f,
        activeSliceAlpha = .9f,
        isAnimationEnable = true
    )
    DonutPieChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        donutChartData,
        donutChartConfig
    )
}

//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White),
//        //verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Icon(
//                imageVector = Icons.Default.ArrowBack,
//                contentDescription = "Back",
//                modifier = Modifier
//                    .size(24.dp)
//                    .clickable { navController.popBackStack() }
//            )
//            Spacer(modifier = Modifier.weight(0.8f))
//            Box(modifier = Modifier) {
//                Text(
//                    text = "Exercises",
//                    fontSize = 30.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.Black,
//                    textDecoration = TextDecoration.Underline
//                )
//            }
//            Spacer(modifier = Modifier.weight(1f))
//        }
//
//        Spacer(modifier = Modifier.size(200.dp))
//
//        PieChart()
//
//       // Text(text = "Počet záznamov s hodnotou 1: $countOfFeelingsWithValueOne")
//    }
//}

    @Composable
    fun PieChart() {
        val donutChartData = PieChartData(
            slices = listOf(
                PieChartData.Slice("HP", 15f, Color(0xFF5F0A87)),
                PieChartData.Slice("Dell", 30f, Color(0xFF20BF55)),
                PieChartData.Slice("Lenovo", 40f, Color(0xFFEC9F05)),
                PieChartData.Slice("Asus", 10f, Color(0xFFF53844))
            ),
            plotType = PlotType.Donut
        )
        val donutChartConfig = PieChartConfig(
            strokeWidth = 120f,
            activeSliceAlpha = .9f,
            isAnimationEnable = true
        )
        DonutPieChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            donutChartData,
            donutChartConfig
        )
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