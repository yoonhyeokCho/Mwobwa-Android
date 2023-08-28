package com.bongku.mwobwa.ui.select

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.bongku.mwobwa.R
import com.bongku.mwobwa.databinding.ActivitySelectBinding
import com.bongku.mwobwa.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectBinding
    private val viewModel: SelectViewModel by viewModels()

    private var flagNetflix = false
    private var flagDisney = false
    private var flagAppleTV = false

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
            selectBtn.isEnabled = flagNetflix || flagDisney || flagAppleTV
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

        if (flagAppleTV) {
            ottCompany.add("Apple tv")
        } else {
            ottCompany.remove("Apple tv")
        }

        Log.d("test", "updateOttCompany: ${ottCompany}")
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

            selectAppleTv.setOnClickListener {
                flagAppleTV = !flagAppleTV
                setSelectButtonColor(it, flagAppleTV, R.id.select_appleTv)
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