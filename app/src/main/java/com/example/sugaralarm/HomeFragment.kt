package com.example.sugaralarm

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.example.sugaralarm.databinding.FragmentHomeBinding
import java.util.Calendar

class HomeFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentHomeBinding
    private val sharedPref by lazy { getDefaultSharedPreferences(requireContext()) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        //requireActivity is a menu host
        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadSettings()
        setupClickListeners()
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_action_bar, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem) = when (menuItem.itemId) {
        R.id.settings -> {
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
            true
        }
        else -> false
    }


    private fun loadSettings() {
        val notificationType = sharedPref.getBoolean("notificationType", true)
        val language = sharedPref.getString("language", "English")
        val theme = sharedPref.getString("theme", "Pink")
    }

    private fun setupClickListeners() {
        binding.button10.setOnClickListener { startActivity(selectIntent(10)) }
        binding.button20.setOnClickListener { startActivity(selectIntent(20)) }
        binding.button30.setOnClickListener { startActivity(selectIntent(30)) }
        binding.button60.setOnClickListener { startActivity(selectIntent(60)) }
    }


    private fun selectIntent(minutes: Int): Intent {
        val notificationType = sharedPref.getBoolean("notificationType", true)

        return if (notificationType) {
            Intent(AlarmClock.ACTION_SET_TIMER).apply {
                putExtra(AlarmClock.EXTRA_LENGTH, minutes)
            }
        } else {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis() + minutes*60000
            Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_HOUR, calendar.get(Calendar.HOUR_OF_DAY))
                putExtra(AlarmClock.EXTRA_MINUTES, calendar.get(Calendar.MINUTE))
            }
        }
    }


}


