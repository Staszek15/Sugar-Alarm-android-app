package com.example.sugaralarm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.example.sugaralarm.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private val sharedPref by lazy { getDefaultSharedPreferences(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.buttonBlue.setOnClickListener {setChosenTheme("blue")}
        binding.buttonPink.setOnClickListener {setChosenTheme("pink")}
        binding.buttonGithub.setOnClickListener {
            val githubLink = "https://github.com/Staszek15"
            val githubIntent = Intent(Intent.ACTION_VIEW, Uri.parse(githubLink))
            startActivity(githubIntent)
        }
    }

    private fun setChosenTheme(themeColor: String?) {
        when (themeColor) {
            "pink" -> activity?.setTheme(R.style.Theme_PinkTheme)
            "blue" -> activity?.setTheme(R.style.Theme_BlueTheme)
        }
        sharedPref.edit { putString("themeColor", themeColor) }
        recreate(requireActivity())
    }

}
