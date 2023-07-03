package com.example.bakolwifiapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bakolwifiapp.R
import com.example.bakolwifiapp.application.WifiApp
import com.example.bakolwifiapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val wifiViewModel: WifiViewModel by viewModels {
        WifiViewModelFactory((applicationContext as WifiApp).repository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = WIfiListAdapter {wifi->
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(wifi)
            findNavController().navigate(action)
        }

        binding.DatarecyclerView.adapter = adapter
        binding.DatarecyclerView.layoutManager = LinearLayoutManager(context)
        wifiViewModel.allWifi.observe(viewLifecycleOwner) { tires ->
            tires.let {
                if (tires.isEmpty()) {
                    binding.empty.visibility = View.VISIBLE
                    binding.ilustrasi.visibility = View.VISIBLE
                } else {
                    binding.empty.visibility = View.GONE
                    binding.ilustrasi.visibility = View.GONE
                }
                adapter.submitList(tires)
            }
        }

        binding.addFAB.setOnClickListener {
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(null)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}