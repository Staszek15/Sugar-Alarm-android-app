package com.example.sugaralarm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.example.sugaralarm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val sharedPref by lazy { getDefaultSharedPreferences(this) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setPreferredTheme()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        // turn off night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun setPreferredTheme() {
        when (sharedPref.getString("themeColor", "pink")) {
            "blue" -> setTheme(R.style.Theme_BlueTheme)
            "green" -> setTheme(R.style.Theme_PinkTheme)
            else -> setTheme(R.style.Theme_PinkTheme)
        }


    }

}