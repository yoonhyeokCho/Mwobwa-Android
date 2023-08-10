package com.bongku.mwobwa.ui.select

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.bongku.mwobwa.R
import com.bongku.mwobwa.databinding.ActivitySelectBinding
import com.bongku.mwobwa.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.locks.ReentrantLock

@AndroidEntryPoint
class SelectActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectBinding
    private val viewModel: SelectViewModel by viewModels()

    private var flagNetflix = false
    private var flagDisney = false
    private var flagCoupang = false

    private var ottCompany = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSelectView()
        observeContentsSetFlag()
    }

    private fun moveMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun setSelectButtonColor(view: View, flag: Boolean, id: Int) {
        view.findViewById<TextView>(id).setTextColor(if (flag) Color.BLACK else Color.WHITE)
    }

    private fun updateSelectButtonState() {
        binding.run {
            selectBtn.isEnabled = flagNetflix || flagDisney || flagCoupang
            if (selectBtn.isEnabled) {
                selectBtn.visibility = View.VISIBLE
            } else {
                selectBtn.visibility = View.INVISIBLE
            }
        }
    }

    private fun updateOttCompany() {
        if (flagNetflix) {
            ottCompany.add("NETFLIX")
        } else {
            ottCompany.remove("NETFLIX")
        }

        if (flagDisney) {
            ottCompany.add("DISNEY")
        } else {
            ottCompany.remove("DISNEY")
        }

        if (flagCoupang) {
            ottCompany.add("COUPANG")
        } else {
            ottCompany.remove("COUPANG")
        }
    }

    private fun initSelectView() {

        binding.run {
            selectNetflix.setOnClickListener {
                flagNetflix = !flagNetflix
                setSelectButtonColor(it, flagNetflix, R.id.select_netflix)
                updateOttCompany()
                updateSelectButtonState()
            }

            selectDisney.setOnClickListener {
                flagDisney = !flagDisney
                setSelectButtonColor(it, flagDisney, R.id.select_disney)
                updateOttCompany()
                updateSelectButtonState()
            }

            selectCoupang.setOnClickListener {
                flagCoupang = !flagCoupang
                setSelectButtonColor(it, flagCoupang, R.id.select_coupang)
                updateOttCompany()
                updateSelectButtonState()
            }

            selectBtn.setOnClickListener {
                viewModel.setContents(ottCompany)
            }
        }
    }

    private fun observeContentsSetFlag() {
        viewModel.ottSetFlag.observe(this, Observer { ottSet ->
            if (ottSet) {
                moveMainActivity()
            }
        })
    }

}