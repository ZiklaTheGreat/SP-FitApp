package com.example.sp_fitapp01.ui.PlansScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sp_fitapp01.data.Plan
import com.example.sp_fitapp01.data.dummyPlans
import com.example.sp_fitapp01.ui.HomeScreen.TopBarName


/**
 * Composable function for displaying the list of workout plans.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@Composable
fun PlansScreen(navController: NavHostController) {
    val plans = remember { dummyPlans() }
    Scaffold(
        topBar = {
            TopBarName(navController = navController, name = "Plans")
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
                .padding(16.dp)
        ) {
            items(plans) { plan ->
                PlanItem(plan = plan, onClick = { navController.navigate("plan_detail_screen/${plan.name}") })
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

/**
 * Composable function for displaying an item in the list of workout plans.
 *
 * @param plan The workout plan to display.
 * @param onClick The callback function to execute when the plan item is clicked.
 */
@Composable
fun PlanItem(plan: Plan, onClick: (Plan) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(plan) }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = plan.imageResId),
            contentDescription = plan.name,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = plan.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}


