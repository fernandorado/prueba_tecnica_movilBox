package com.android.pruebatecnicamovilbox.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.android.pruebatecnicamovilbox.R

class ApplicationPresentationFragment : Fragment() {
    lateinit var btnAceptar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_application_presentation, container, false)
        btnAceptar = vista.findViewById(R.id.buttonContinue)
        eventClick()

        // Verificar si el fragmento ya se ha mostrado antes
        if (isOnBoardingFinished()) {
            // Navegar a la siguiente pantalla si el fragmento ya se ha mostrado
            findNavController().navigate(R.id.action_applicationPresentationFragment_to_producListFragment)
        }

        return vista
    }

    private fun eventClick() {
        btnAceptar.setOnClickListener{
            // Guardar en SharedPreferences que el fragmento se ha mostrado
            onBoardingFinished()

            // Navegar a la siguiente pantalla
            findNavController().navigate(R.id.action_applicationPresentationFragment_to_producListFragment)
        }
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

    private fun isOnBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}
