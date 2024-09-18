package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.routes.Route
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    NavHost(navController, startDestination = Route.Companion.UserListRoute.path!!) {
        Route.Screens.map { screen ->
            if (screen.argument.isNullOrBlank()) {
                composable(
                    screen.path!!,
                    arguments = listOf(navArgument(screen.argument!!) {
                        type = screen.argumentType!!
                    })
                ) { backStackEntry ->
                    screen.composable!!(
                        modifier,
                        navController,
                        backStackEntry.arguments?.getString(screen.argument).toString()
                    )
                }
            } else {
                composable(screen.path!!) {
                    screen.composable!!(
                        modifier,
                        navController,
                        null
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        fontSize = 30.sp,
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}