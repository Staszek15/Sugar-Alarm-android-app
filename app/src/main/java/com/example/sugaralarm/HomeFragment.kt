package com.example.sugaralarm

import android.annotation.SuppressLint
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

        val (valuesTimes, valuesMessages) = loadSettings()

        applySettings(valuesTimes)
        setupClickListeners(valuesTimes, valuesMessages)
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


    private fun loadSettings(): Pair<List<Int>, List<String?>> {
        return listOf(
            sharedPref.getString("time_button_one", "10")!!.toInt(),
            sharedPref.getString("time_button_two", "30")!!.toInt(),
            sharedPref.getString("time_button_three", "20")!!.toInt(),
            sharedPref.getString("time_button_four", "60")!!.toInt()
        ) to listOf(
            sharedPref.getString("message_button_one", null),
            sharedPref.getString("message_button_two", null),
            sharedPref.getString("message_button_three", null),
            sharedPref.getString("message_button_four", null)
        )
    }

    @SuppressLint("SetTextI18n")
    private fun applySettings(values_times: List<Int?>) {
        binding.buttonOne.text = values_times[0].toString() + " min"
        binding.buttonTwo.text = values_times[1].toString() + " min"
        binding.buttonThree.text = values_times[2].toString() + " min"
        binding.buttonFour.text = values_times[3].toString() + " min"
    }

    private fun setupClickListeners(values_times: List<Int?>, values_messages: List<String?>) {
        binding.buttonOne.setOnClickListener { startActivity(selectIntent(values_times[0], values_messages[0])) }
        binding.buttonTwo.setOnClickListener { startActivity(selectIntent(values_times[1], values_messages[1])) }
        binding.buttonThree.setOnClickListener { startActivity(selectIntent(values_times[2], values_messages[2])) }
        binding.buttonFour.setOnClickListener { startActivity(selectIntent(values_times[3], values_messages[3])) }
    }


    private fun selectIntent(minutes: Int?, message: String?): Intent {
        val notificationType = sharedPref.getBoolean("notificationType", true)

        return if (notificationType) {
            Intent(AlarmClock.ACTION_SET_TIMER).apply {
                putExtra(AlarmClock.EXTRA_LENGTH, minutes!! * 60)
                putExtra(AlarmClock.EXTRA_MESSAGE, message)
            }
        } else {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis() + minutes!! * 60000
            Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_HOUR, calendar.get(Calendar.HOUR_OF_DAY))
                putExtra(AlarmClock.EXTRA_MINUTES, calendar.get(Calendar.MINUTE))
                putExtra(AlarmClock.EXTRA_MESSAGE, message)
            }
        }
    }


}


