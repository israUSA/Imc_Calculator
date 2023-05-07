package com.example.imccalculator.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imccalculator.R
import kotlin.math.pow

class CalculatorViewModel: ViewModel() {

    private val _isHombre = MutableLiveData<Boolean>()
    val isHombre: LiveData<Boolean> = _isHombre

    private val _altura = MutableLiveData<Double>(1.20)
    val altura: LiveData<Double> = _altura

    private val _edad = MutableLiveData<Int>(18)
    val edad: LiveData<Int> = _edad

    private val _peso = MutableLiveData<Double>(60.0)
    val peso: LiveData<Double> = _peso

    private val _imc = MutableLiveData<Double>()
    val imc: LiveData<Double> = _imc

    private val _imageResource= MutableLiveData<Int>()
    val imagenResource: LiveData<Int> = _imageResource

    private val _categoria = MutableLiveData<String>()
    val categoria: LiveData<String> = _categoria


    fun setGenero(isMale: Boolean){
        _isHombre.value = isMale
    }

    fun cambioAltura(altura: Double){
        _altura.value = altura
    }

    fun sumarEdad(){
        _edad.value = _edad.value?.plus(1)
    }

    fun restarEdad(){
        _edad.value = _edad.value?.minus(1)
    }


    fun sumarPeso(){
        _peso.value = _peso.value?.plus(1)
    }

    fun restarPeso(){
        _peso.value = _peso.value?.minus(1)
    }

    fun calcularIMC(){

        val imcTemporal = peso.value?.div((altura.value!!).pow(2))
        val roundedImc = "%.1f".format(imcTemporal)

        _imc.value = roundedImc.toDouble()
        bodyImageView()
        setCategoria()
    }

    private fun bodyImageView() {
        val imageViewRes: Int
        if (isHombre.value == true) {
            imageViewRes = when {
                imc.value!! < 18.5 -> R.drawable.img_hombre_delgado
                imc.value!! < 25 -> R.drawable.img_hombre_normal
                imc.value!! < 30 -> R.drawable.img_hombre_gordo
                else -> R.drawable.img_hombre_sobrepeso
            }

            _imageResource.value = imageViewRes
        }

        else{
            imageViewRes = when {
                imc.value!! < 18.5 -> R.drawable.img_mujer_delgada
                imc.value!! < 25 -> R.drawable.img_mujer_normal
                imc.value!! < 30 -> R.drawable.img_mujer_gorda
                else -> R.drawable.img_mujer_sobrepeso
            }
            _imageResource.value = imageViewRes
        }
    }

    private fun setCategoria(){


            _categoria.value = when {
                imc.value!! < 18.5 -> "Bajo Peso"
                imc.value!! < 25 -> "Peso saludable"
                imc.value!! < 30 -> "Sobrepeso"
                else -> "Obesidad"
            }




    }


}