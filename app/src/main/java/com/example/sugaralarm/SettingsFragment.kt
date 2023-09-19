package com.example.sugaralarm

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sugaralarm.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null   //define the binding class
    private val binding get() = _binding!!   // !! so it can not be null now
    val pref = activity?.getPreferences(Context.MODE_PRIVATE)
    val prefEditor = pref?.edit()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val themeColor = pref?.getString("themeColor", "pink")
        setDynamicTheme(themeColor)

        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        binding.toolbar.toolbar.inflateMenu(R.menu.menu)
        binding.toolbar.toolbar.setTitle(R.string.settings_text)
        binding.toolbar.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)  // set toolbar back arrow
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set toolbar back arrow navigation
        binding.toolbar.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        // theme buttons
        binding.buttonBlue.setOnClickListener {
            prefEditor?.putString("themeColor", "blue")
            prefEditor?.apply()
            binding.text1.text = pref?.getString("themeColor", "none")

        }

        binding.buttonPink.setOnClickListener {
            prefEditor?.putString("themeColor", "pink")
            prefEditor?.apply()
            binding.text1.text = pref?.getString("themeColor", "none")
        }

        // github button
        binding.buttonGithub.setOnClickListener {
            var github_link = "https://github.com/Staszek15"
            val githubIntent = Intent(Intent.ACTION_VIEW, Uri.parse(github_link))
            startActivity(githubIntent)
        }

        binding.switch2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) prefEditor?.putBoolean("alarms", true)
            else prefEditor?.putBoolean("alarms", false)
        }
        // binding.switch2.setOnCheckedChangeListener {buttonView, isChecked -> MainVm.intent="alarm"}
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun setDynamicTheme(themeColor: String?) {
        when (themeColor) {
            "pink" -> activity?.theme?.applyStyle(R.style.Theme_SugarAlarm, true)
            "blue" -> activity?.theme?.applyStyle(R.style.Theme_BlueMode, true)
        }
    }

}
