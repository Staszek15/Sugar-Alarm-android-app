package com.example.sugaralarm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.os.LocaleListCompat
import androidx.navigation.fragment.findNavController
import androidx.preference.*

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)


        findPreference<SwitchPreferenceCompat>("notificationType")?.setOnPreferenceChangeListener { _, newValue ->
            view!!.findViewById<SwitchCompat>(R.id.switch2).isChecked = newValue as Boolean
            true
        }

        findPreference<Preference>("timeAndMessages")?.setOnPreferenceClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_settingsFragment2)
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

        findPreference<Preference>("mail")?.setOnPreferenceClickListener {
            val emailAddress = "mateusz.stasiak15@wp.pl"
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto: $emailAddress")
                putExtra(Intent.EXTRA_SUBJECT, "Sugar Alarm App email")
            }
            startActivity(emailIntent)
            true
        }

    }


}





