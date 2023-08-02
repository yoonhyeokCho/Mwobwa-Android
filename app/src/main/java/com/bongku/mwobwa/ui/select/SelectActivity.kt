package com.bongku.mwobwa.ui.select

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.bongku.mwobwa.databinding.ActivitySelectBinding
import com.bongku.mwobwa.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectBinding
    private val viewModel: SelectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.checkFirstAccess()
        viewModel.firstAccess.observe(this, Observer {
            if(it){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                viewModel.setFirstAccess()
            }
        })
    }
}