package com.example.sugaralarm

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.view.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.sugaralarm.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null  //define the binding class
    private val binding get() = _binding!!  // so it can not be null now

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.toolbar.toolbar.inflateMenu(R.menu.menu)      // set toolbar
        binding.toolbar.toolbar.setTitle(R.string.app_name)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button10.setOnClickListener {
            startActivity(chooseIntent(600))
        }
        binding.button20.setOnClickListener {
            startActivity(chooseIntent(1200))
        }
        binding.button30.setOnClickListener {
            startActivity(chooseIntent(1800))
        }
        binding.button60.setOnClickListener {
            startActivity(chooseIntent(3600))
        }

        binding.toolbar.toolbar.setOnMenuItemClickListener {
             when (it.itemId) {
                R.id.settings -> {
                    findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
                    true
                }
                else -> false
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

    //choice between timer intent and alarm intent (based on switch position)
    fun chooseIntent(time: Int): Intent {
        val bool = false
            //intent.getBooleanExtra("alarms_boolean", false)
        var selectedIntent = Intent()

        // this if is always false coz var bool should be taken from intent.getBoolean
        selectedIntent = if (bool) {
            Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_HOUR, 18)
                putExtra(AlarmClock.EXTRA_MINUTES, 10)
            }
        } else {
            Intent(AlarmClock.ACTION_SET_TIMER).apply {
                putExtra(AlarmClock.EXTRA_LENGTH, time)
            }
        }
        return selectedIntent
    }



