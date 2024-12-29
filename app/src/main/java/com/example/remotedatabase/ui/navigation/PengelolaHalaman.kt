package com.example.remotedatabase.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.remotedatabase.ui.view.mahasiswa.DestinasiDetail
import com.example.remotedatabase.ui.view.mahasiswa.DestinasiEntry
import com.example.remotedatabase.ui.view.mahasiswa.DestinasiHome
import com.example.remotedatabase.ui.view.mahasiswa.DestinasiUpdate
import com.example.remotedatabase.ui.view.mahasiswa.DetailScreen
import com.example.remotedatabase.ui.view.mahasiswa.EntryMhsScreen
import com.example.remotedatabase.ui.view.mahasiswa.HomeScreen
import com.example.remotedatabase.ui.view.mahasiswa.UpdateScreen

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                    println("PengelolaHalaman: nim = $nim")
                }
            )
        }
        composable(DestinasiEntry.route) {
            EntryMhsScreen(navigateBack = {
                navController.navigate(DestinasiHome.route) {
                    popUpTo(DestinasiHome.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.NIM){
                    type = NavType.StringType
                }
            )
        ){ backStackEntry ->
            val nim = backStackEntry.arguments?.getString(DestinasiDetail.NIM)
            nim?.let {
                DetailScreen(
                    nim = it,
                    onEditClick = {
                        // Arahkan ke halaman edit berdasarkan nim
                        navController.navigate("${DestinasiUpdate.route}/$nim")
                    },
                    onBackClick = {
                        navController.popBackStack() // Kembali ke halaman sebelumnya
                    }
                )
            }
        }
        composable(
            route = DestinasiUpdate.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdate.NIM) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nim = backStackEntry.arguments?.getString(DestinasiUpdate.NIM)
            nim?.let {
                UpdateScreen(
                    onBack = { navController.popBackStack() },
                    onNavigate = {
                        navController.navigate(DestinasiHome.route) {
                            popUpTo(DestinasiHome.route) { inclusive = true }
                        }
                    },
                    modifier = modifier,
                )
            }
        }
    }
}