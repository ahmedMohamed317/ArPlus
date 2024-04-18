package com.example.ar_task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ar_task.navigation.BottomNavigationBar
import com.example.ar_task.presentation.favorite.FavoriteScreen
import com.example.ar_task.presentation.search.SearchScreen
import com.example.ar_task.util.connectivity.ConnectivityObserver
import com.example.ar_task.util.connectivity.NetworkConnectivityObserver
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var connectivityObserver: ConnectivityObserver = NetworkConnectivityObserver(this)
        super.onCreate(savedInstanceState)

        val connectivityStatus = connectivityObserver.observe().stateIn(
            scope = lifecycleScope,
            started = SharingStarted.Eagerly,
            initialValue = ConnectivityObserver.Status.Lost
        )

        setContent {
            MyApp(connectivityStatus)
        }
    }
}
@Composable
fun MyApp(connectivityStatus: StateFlow<ConnectivityObserver.Status>) {
    val navController = rememberNavController()
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(0) }

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    selectedTab = selectedTab,
                    onTabSelected = { tab ->
                        setSelectedTab(tab)
                    },
                    navController = navController
                )
            }
        ) {
            NavHost(navController = navController, startDestination = "search") {
                composable("search") {
                    SearchScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                            .padding(20.dp),
                         connectivityStatus

                    )
                }
                composable("favorite") {
                    FavoriteScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                            .padding(20.dp),
                        connectivityStatus
                    )
                }
            }
        }
    }
}
