package com.example.imccalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.imccalculator.databinding.FragmentCalculadoraBinding
import com.example.imccalculator.databinding.FragmentResultadoBinding
import com.example.imccalculator.model.CalculatorViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultadoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultadoFragment : Fragment() {

    private lateinit var _binding: FragmentResultadoBinding
    private val binding get() = _binding
    private val sharedViewModel: CalculatorViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultadoBinding.inflate(inflater, container, false)
        val view = _binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnReintentar.setOnClickListener{reintentar()}
        binding.btnInfoSalud.setOnClickListener { dialogInfoSalud() }
        binding.btnInfoImc.setOnClickListener { dialogInfoImc() }

        sharedViewModel.imc.observe(viewLifecycleOwner, {newIMC -> binding.tvResultado.text = newIMC.toString()})
        sharedViewModel.imagenResource.observe(viewLifecycleOwner, {newImageBody -> binding.imagenResultado.setImageResource(newImageBody)})
    }

    private fun reintentar(){
        findNavController().navigate(R.id.action_resultadoFragment_to_startFragment)
    }

    private fun dialogInfoSalud(){
        MaterialAlertDialogBuilder(requireContext(), R.style.Theme_App_MaterialDialogAlert)
            .setTitle("Info HEALTH")
            .setMessage(getString(R.string.Edad, sharedViewModel.edad.value)
                    + "\n\n"
                    + getString(R.string.Altura, sharedViewModel.altura.value)
                    + "\n\n"
                    + getString(R.string.Peso, sharedViewModel.peso.value))
            .setCancelable(true)
            .show()
    }

    private fun dialogInfoImc(){
        MaterialAlertDialogBuilder(requireContext(), R.style.Theme_App_MaterialDialogAlert2)
            .setTitle(sharedViewModel.categoria.value)
            .setMessage("Es importante tener en cuenta que estos valores son solo orientativos y que el IMC " +
                    "no es el único indicador para evaluar el estado de salud de una persona.")
            .setCancelable(true)
            .show()
    }
}
