package com.example.sp_fitapp01.ui.HomeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sp_fitapp01.R
import com.example.sp_fitapp01.ui.MainApp

/**
 * Composable function for displaying the main screen of the Fitness Application.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopBarMain(name = "FitApp", motto = "your fitness journey, today", desc = "MENU")
        }
    ) { innerPadding ->
        // Use Scrollable Column to enable scrolling
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)  // This will apply the Scaffold's padding
                .verticalScroll(rememberScrollState()) // Enables scrolling
                .padding(horizontal = 16.dp),  // Add any additional padding you need
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            MainLogo()
            MainButtons(navController = navController)
        }
    }
}

/**
 * Composable function for displaying a button in the footer section.
 *
 * @param text The text to display on the button.
 * @param onClick The callback function to execute when the button is clicked.
 */
@Composable
fun FooterButton(text: String, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .width(110.dp)
    ) {
        Text(text = text)
    }
}

/**
 * Composable function for displaying the top bar of the main screen.
 *
 * @param name The name to display on the top bar.
 * @param motto The motto to display on the top bar.
 * @param desc The description to display on the top bar.
 */
@Composable
fun TopBarMain(name: String, motto:String, desc: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
            .background(Color(0xFF8EBDEF))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = motto,
                fontSize = 16.sp,
                color = Color.Black,
                textDecoration = TextDecoration.Underline
            )
            Text(
                text = desc,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

/**
 * Composable function for displaying the logo on the main screen.
 */
@Composable
fun MainLogo() {
        Image(
            painter = painterResource(id = R.drawable.logo_transparent),
            contentDescription = null,
            modifier = Modifier
                .size(400.dp)
                .padding(16.dp),
            contentScale = ContentScale.Fit
        )
}

/**
 * Composable function for displaying the main buttons on the main screen.
 *
 * @param navController The navigation controller for navigating between screens.
 */
@Composable
fun MainButtons(navController: NavHostController) {
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("plan_screen") },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            Text(
                text = "Workout",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            FooterButton(text = "Exercises") { navController.navigate("exercise_screen") }
            //FooterButton(text = "Plans") { navController.navigate("plan_screen") }
            FooterButton(text = "Stats") { navController.navigate("stat_screen") }
        }
}

/**
 * Composable function for displaying the top bar with name on various screens.
 *
 * @param navController The navigation controller for navigating between screens.
 * @param name The name to display on the top bar.
 */
@Composable
fun TopBarName(navController: NavHostController, name: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp))
            .background(Color(0xFF8EBDEF))
            .padding(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.weight(0.8f))
            Text(
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

/**
 * Composable function for displaying the top bar icon.
 */
@Composable
fun TopBarIcon() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(bottomEnd = 32.dp, bottomStart = 32.dp))
            .background(Color(0xFF8EBDEF)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_transparent),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .scale(3f),
            contentScale = ContentScale.Fit
        )
    }
}

/**
 * Preview function for displaying the main screen.
 */
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainApp()
}
