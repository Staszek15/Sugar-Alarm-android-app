package com.example.sugaralarm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
        binding.toolbar.toolbar.inflateMenu(R.menu.menu)
        binding.toolbar.toolbar.setTitle(R.string.settings_text)
        binding.toolbar.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)  // set toolbar back arrow
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.toolbar.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
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
            "pink" -> activity?.theme?.applyStyle(R.style.Theme_PinkTheme, true)
            "blue" -> activity?.theme?.applyStyle(R.style.Theme_BlueTheme, true)
        }
        sharedPref.edit { putString("themeColor", themeColor) }
    }

}
