package com.example.mini_tech_talk.navigation

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.gson.Gson

sealed class Screen(
    val route: String,
    val navArgument: List<NamedNavArgument> = emptyList()
) {

    object HomeScreen: Screen("home")

    object StackScreen: Screen("checkout")

    object NavParamScreen: Screen("navparam")

    object NavParamStartScreen: Screen("navparam/start")

    object ArgumentScreen: Screen(
        route = "argument/{string}/{int}/{float}",
        navArgument = listOf(
            navArgument("string") { type = NavType.StringType },
            navArgument("int") { type = NavType.IntType },
            navArgument("float") { type = NavType.FloatType }
        )
    ) {

        data class ArgumentArg(
            val string: String,
            val int: Int,
            val float: Float
        )

        fun getNavArg(backStackEntry: NavBackStackEntry): ArgumentArg {
            return with(backStackEntry.arguments) {
                val string = this?.getString("string")
                val int = this?.getInt("int")
                val float = this?.getFloat("float")

                requireNotNull(string) { "'string' param cannot be null" }
                requireNotNull(int) { "'int' param cannot be null" }
                requireNotNull(float) { "'float' param cannot be null" }

                ArgumentArg(string, int, float)
            }
        }

        fun createRoute(string: String, int: Int, float: Float): String {
            return "argument/$string/$int/$float"
        }
    }

    object BundleScreen: Screen(
        route = "bundle/{bundleArg}",
        navArgument = listOf(
            navArgument("bundleArg") { type = BundleParamType }
        )
    ) {
        data class BundleArg(
            val dataSet: List<String>,
            val counter: Int
        )

        object BundleParamType: NavType<BundleArg>(
            isNullableAllowed = false
        ) {
            override fun get(bundle: Bundle, key: String): BundleArg? {
                return bundle.getString(key)?.let { parseValue(it) }
            }

            override fun parseValue(value: String): BundleArg {
                return Gson().fromJson(value, BundleArg::class.java)
            }

            override fun put(bundle: Bundle, key: String, value: BundleArg) {
                bundle.putString(key, serializeAsValue(value))
            }

            override fun serializeAsValue(value: BundleArg): String {
                return Gson().toJson(value)
            }
        }

        fun getNavArg(backStackEntry: NavBackStackEntry): BundleArg {
            val rawParams = backStackEntry.arguments?.getString("bundleArg")
            requireNotNull(rawParams)
            return BundleParamType.parseValue(rawParams)
        }

        fun createRoute(bundleArg: BundleArg): String {
            return "bundle/${BundleParamType.serializeAsValue(bundleArg)}"
        }
    }
}


