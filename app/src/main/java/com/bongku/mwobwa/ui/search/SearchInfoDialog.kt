package com.bongku.mwobwa.ui.search

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.bongku.mwobwa.databinding.DialogInfoSearchBinding

class SearchInfoDialog(
    context: Context,
    private val score: Double,
    private val count: Long,
    private val overView: String
) : Dialog(context) {
    private lateinit var binding: DialogInfoSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window?.setBackgroundDrawableResource(android.R.color.transparent)
        binding = DialogInfoSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        setCancelable(false)
        binding.run {
            dialogOk.setOnClickListener {
                dismiss()
            }
            dialogStory.text = overView
            dialogScore.text = "평점 : ${score} 점"
            dialogCount.text = "참여 : ${count} 명"
        }
    }
}