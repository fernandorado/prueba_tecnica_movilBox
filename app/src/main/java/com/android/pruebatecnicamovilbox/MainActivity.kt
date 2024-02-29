package com.android.pruebatecnicamovilbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.pruebatecnicamovilbox.fragments.ApplicationPresentationFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun presentacionApp() {
        val presentacionFragment= ApplicationPresentationFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedorFragmentsPre, presentacionFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}