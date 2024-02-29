package com.android.pruebatecnicamovilbox.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.pruebatecnicamovilbox.R
import com.android.pruebatecnicamovilbox.fragments.ApplicationPresentationFragment

class ActivityPresentation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentation)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragmentInicio =  ApplicationPresentationFragment();

        fragmentTransaction.replace(R.id.contenedorFragmentsPre, fragmentInicio).commit()
    }
}