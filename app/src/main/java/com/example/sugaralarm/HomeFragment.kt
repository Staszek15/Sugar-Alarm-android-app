package com.example.sugaralarm

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.example.sugaralarm.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val sharedPref by lazy { getDefaultSharedPreferences(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.toolbar.toolbar.inflateMenu(R.menu.menu)      // set toolbar
        binding.toolbar.toolbar.setTitle(R.string.app_name)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.button10.setOnClickListener { startActivity(chooseIntent(600)) }
        binding.button20.setOnClickListener { startActivity(chooseIntent(1200)) }
        binding.button30.setOnClickListener { startActivity(chooseIntent(1800)) }
        binding.button60.setOnClickListener { startActivity(chooseIntent(3600)) }

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


    private fun chooseIntent(time: Int): Intent {
        return Intent()
    }
}


