package com.example.sugaralarm

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment2 : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}