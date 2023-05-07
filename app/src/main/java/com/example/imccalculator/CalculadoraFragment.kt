package com.example.imccalculator

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.imccalculator.databinding.FragmentCalculadoraBinding
import com.example.imccalculator.model.CalculatorViewModel
import java.text.DecimalFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CalculadoraFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var _binding: FragmentCalculadoraBinding
    private val binding get() = _binding!!
    private val sharedViewModel: CalculatorViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculadoraBinding.inflate(inflater, container, false)
        val view = _binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setColorGenero()
        binding.btnCalcular.setOnClickListener { calcular() }
        binding.rsHeight.addOnChangeListener { _, value, _ ->
            setAltura(value)
        }
        binding.btnSumarEdad.setOnClickListener{
            sharedViewModel.sumarEdad()
            Log.d("EDAD_IMC", "${sharedViewModel.edad.value}")}

        binding.btnRestarEdad.isEnabled = sharedViewModel.edad.value!! > 5
        binding.btnRestarEdad.setOnClickListener {
            if (sharedViewModel.edad.value!! > 5) {
                sharedViewModel.restarEdad()
                binding.btnRestarEdad.isEnabled = true
            }

            Log.d("EDAD_IMC", "${sharedViewModel.edad.value}")}

        binding.btnSumarPeso.setOnClickListener {
            sharedViewModel.sumarPeso()
            Log.d("PESO_IMC", "${sharedViewModel.edad.value}")}

        binding.btnRestarPeso.setOnClickListener {
            sharedViewModel.restarPeso()
        Log.d("PESO_IMC", "${sharedViewModel.edad.value}")}


        sharedViewModel.edad.observe(viewLifecycleOwner,{newEdad -> binding.tvEdad.text = newEdad.toString()})
        sharedViewModel.peso.observe(viewLifecycleOwner,{newPeso -> binding.tvPeso.text = newPeso.toString()})

    }

    private fun calcular() {
        sharedViewModel.calcularIMC()
        findNavController().navigate(R.id.action_calculadoraFragment_to_resultadoFragment)
        Log.d("GENERO_IMC", "${sharedViewModel.isHombre.value}")
        Log.d("ALTURA_IMC", "${sharedViewModel.altura.value}")
        Log.d("PESO_IMC", "${sharedViewModel.peso.value}")
        Log.d("IMC", "${sharedViewModel.imc.value}")
    }

    private fun setAltura(value: Float){
        val df = DecimalFormat("#.##")
        sharedViewModel.cambioAltura(df.format(value).toDouble())
        binding.tvAltura.text = "${sharedViewModel.altura.value} m"

        Log.d("ALTURA_IMC", "${sharedViewModel.altura.value}")
    }

    private fun setColorGenero() {
        if (sharedViewModel.isHombre.value == false) {
            binding.cardviewAltura.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.femenino
                )
            )
            binding.cardviewEdad.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.femenino
                )
            )
            binding.cardviewPeso.setCardBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.femenino
                )
            )

        }
    }


}