package fr.isen.buton.androidcontactds

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.buton.androidcontactds.databinding.ActivityRepertoryBinding


class RepertoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepertoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.w("rep","test")

        binding = ActivityRepertoryBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

    }
}