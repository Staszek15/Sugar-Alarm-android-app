package com.example.sugaralarm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.widget.SwitchCompat
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat

class SettingsFragment2 : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        findPreference<Preference>("github")?.setOnPreferenceClickListener {
            val githubLink = "https://github.com/Staszek15"
            val githubIntent = Intent(Intent.ACTION_VIEW, Uri.parse(githubLink))
            startActivity(githubIntent)
            true
        }

        findPreference<SwitchPreferenceCompat>("notificationType")?.setOnPreferenceChangeListener { _, newValue ->
            view!!.findViewById<SwitchCompat>(R.id.switch2).isChecked = newValue as Boolean
            true}
    }

}