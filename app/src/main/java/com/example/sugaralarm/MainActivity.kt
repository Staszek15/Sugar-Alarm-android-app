package com.example.sugaralarm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.example.sugaralarm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navController by lazy { findNavController(R.id.main_fragment_container) }
    private val sharedPref by lazy { getDefaultSharedPreferences(this) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setPreferredTheme()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applySettings()
        setupActionBarWithNavController(navController)

        // turn off night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    private fun setPreferredTheme() {
        when (sharedPref.getString("themeColor", "pink")) {
            "blue" -> setTheme(R.style.Theme_BlueTheme)
            "beige" -> setTheme(R.style.Theme_BeigeTheme)
            else -> setTheme(R.style.Theme_PinkTheme)
        }
    }

    private fun applySettings() {
        val lang = sharedPref.getString("language", "en")
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(lang))

    }

}