package com.example.sp_fitapp01.ui.StatScreen

import android.graphics.Typeface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import co.yml.charts.common.model.PlotType
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData
import com.example.sp_fitapp01.ui.AppViewModelProvider
import com.example.sp_fitapp01.ui.HomeScreen.TopBarName

/**
 * Composable function for displaying statistics screen.
 *
 * @param navController The navigation controller for navigating between screens.
 * @param viewModel The view model responsible for providing data for statistics.
 */
@Composable
fun StatScreen(
    navController: NavHostController,
    viewModel: StatViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val feelings by viewModel.feelings.collectAsState()

    Scaffold(
        topBar = { TopBarName(navController = navController, name = "Statistics") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TotalWorkouts(viewModel)
            Spacer(modifier = Modifier.height(16.dp))
            if (feelings.isNotEmpty()) {
                PieChart(
                    s1 = feelings.getOrElse(0) { 0f },
                    s2 = feelings.getOrElse(1) { 0f },
                    s3 = feelings.getOrElse(2) { 0f },
                    s4 = feelings.getOrElse(3) { 0f },
                    s5 = feelings.getOrElse(4) { 0f }
                )
            }
        }
    }
}
/**
 * Composable function for displaying a pie chart.
 *
 * @param s1 The value of the first slice in the pie chart.
 * @param s2 The value of the second slice in the pie chart.
 * @param s3 The value of the third slice in the pie chart.
 * @param s4 The value of the fourth slice in the pie chart.
 * @param s5 The value of the fifth slice in the pie chart.
 */
@Composable
fun PieChart(s1: Float = 0f, s2: Float = 0f, s3: Float = 0f, s4: Float = 0f, s5: Float = 0f) {
    val donutChartData = PieChartData(
        slices = listOf(
            PieChartData.Slice("Exhausted", s1, Color(0xFFF53844)),
            PieChartData.Slice("Tired", s2, Color(0xFFEC9F05)),
            PieChartData.Slice("Satisfied", s3, Color(0xFF20BF55)),
            PieChartData.Slice("Energized", s4, Color(0xFF8A2BE2)),
            PieChartData.Slice("Excellent", s5, Color(0xF00538ff))


        ),
        plotType = PlotType.Donut
    )
    val donutChartConfig = PieChartConfig(
        labelTypeface = Typeface.DEFAULT_BOLD,
        strokeWidth = 85f,
        activeSliceAlpha = .9f,
        isAnimationEnable = true,
        showSliceLabels = true,
        labelVisible = true,
        labelColor = Color.Black,
        labelFontSize = 28.sp,
        labelColorType = PieChartConfig.LabelColorType.SLICE_COLOR,
        labelType = PieChartConfig.LabelType.PERCENTAGE
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        DonutPieChart(
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp),
            donutChartData,
            donutChartConfig
        )
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            donutChartData.slices.forEach { slice ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(slice.color)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = slice.label, fontSize = 16.sp, color = Color.Black)
                }
            }
        }
    }
}

/**
 * Composable function for displaying total workouts.
 *
 * @param viewModel The view model responsible for providing data for total workouts.
 */
@Composable
fun TotalWorkouts(viewModel: StatViewModel) {
    val totalWorkouts by viewModel.totalWorkouts.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Total Workouts",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = totalWorkouts.toString(),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}


/**
 * Composable function for displaying statistics screen preview.
 */
@Preview
@Composable
fun StatScreenPreview() {
    StatScreen(navController = rememberNavController())
}