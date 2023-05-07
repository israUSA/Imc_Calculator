package com.example.imccalculator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.imccalculator.databinding.FragmentGeneroBinding
import com.example.imccalculator.model.CalculatorViewModel


class GeneroFragment : Fragment() {

    private lateinit var _binding: FragmentGeneroBinding
    private val binding get() = _binding
    private val sharedViewModel: CalculatorViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGeneroBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnHombre.setOnClickListener { escogerGenero(true) }
        binding.btnMujer.setOnClickListener { escogerGenero(false) }
    }

    private fun goCalculator(){
        findNavController().navigate(R.id.action_generoFragment_to_calculadoraFragment)
    }

    private fun escogerGenero(isMale: Boolean){
        sharedViewModel.setGenero(isMale)
        Log.d("GENERO_IMC", "${sharedViewModel.isHombre.value}")
        findNavController().navigate(R.id.action_generoFragment_to_calculadoraFragment)
    }


}