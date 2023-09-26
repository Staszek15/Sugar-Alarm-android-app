package com.example.sugaralarm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.os.LocaleListCompat
import androidx.preference.*

class SettingsFragment : PreferenceFragmentCompat() {

    private val sharedPref by lazy { PreferenceManager.getDefaultSharedPreferences(requireContext()) }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

//        view!!.findViewById<SwitchCompat>(R.id.switch2).isChecked =
//            sharedPref.getBoolean("notificationType", true)


        findPreference<SwitchPreferenceCompat>("notificationType")?.setOnPreferenceChangeListener { _, newValue ->
            view!!.findViewById<SwitchCompat>(R.id.switch2).isChecked = newValue as Boolean
            true
        }

        findPreference<ListPreference>("language")?.setOnPreferenceChangeListener { _, newValue ->
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(newValue as String?))
            true
        }

        findPreference<Preference>("github")?.setOnPreferenceClickListener {
            val githubLink = "https://github.com/Staszek15"
            val githubIntent = Intent(Intent.ACTION_VIEW, Uri.parse(githubLink))
            startActivity(githubIntent)
            true
        }


    }
}

    //view!!.findViewById<SwitchCompat>(R.id.switch2).isChecked = true

//    private fun setChosenTheme(themeColor: String?) {
//        when (themeColor) {
//            "pink" -> activity?.setTheme(R.style.Theme_PinkTheme)
//            "blue" -> activity?.setTheme(R.style.Theme_BlueTheme)
//        }
//        sharedPref.edit { putString("themeColor", themeColor) }
//        ActivityCompat.recreate(requireActivity())
//    }

