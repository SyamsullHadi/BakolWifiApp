package com.example.bakolwifiapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.bakolwifiapp.application.WifiApp
import com.example.bakolwifiapp.databinding.FragmentSecondBinding
import com.example.bakolwifiapp.model.Wifi

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val wifiViewModel: WifiViewModel by viewModels {
        WifiViewModelFactory((applicationContext as WifiApp).repository)
    }
    private val args : SecondFragmentArgs by navArgs()
    private var wifi: Wifi? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wifi = args.wifi
        if (wifi != null) {
            binding.deleteButton.visibility = View.VISIBLE
            binding.saveButton.text = "Ubah"
            binding.nameEditText.setText(wifi?.name)
            binding.tlpEditText.setText(wifi?.tlp)
            binding.addressEditText.setText(wifi?.address)
        }
        val name = binding.nameEditText.text
        val tlp = binding.tlpEditText.text
        val address = binding.addressEditText.text
        binding.saveButton.setOnClickListener {
            if (name.isEmpty()) {
                Toast.makeText(context, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (tlp.isEmpty()) {
                Toast.makeText(context, "Nomor tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (address.isEmpty()) {
                Toast.makeText(context, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                if (wifi == null) {
                    val wifi = Wifi(0, name.toString(), tlp.toString(), address.toString())
                    wifiViewModel.insert(wifi)
                } else {
                    val wifi = Wifi(wifi?.id!!, name.toString(), tlp.toString(), address.toString())
                    wifiViewModel.update(wifi)
                }

                findNavController().popBackStack()  //Untuk dismiss halaman ini
            }
        }

        binding.deleteButton.setOnClickListener {
            wifi?.let { wifiViewModel.delete(it) }
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}