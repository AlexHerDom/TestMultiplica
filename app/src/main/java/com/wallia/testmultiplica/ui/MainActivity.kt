package com.wallia.testmultiplica.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.text.isDigitsOnly
import com.wallia.testmultiplica.databinding.ActivityMainBinding
import com.wallia.testmultiplica.viewmodel.MyViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val myViewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()
    }

    override fun onResume() {
        super.onResume()

        binding.btnStart.setOnClickListener {

            disableButton()

            myViewModel.doCount(
                if (binding.etMinutes.text.toString().isEmpty()) emptyValue() else binding.etMinutes.text.toString().toInt(),
                if (binding.etSeconds.text.toString().isEmpty()) emptyValue() else binding.etSeconds.text.toString().toInt()
            )
        }
    }

    private fun initObservers() {
        myViewModel.count.observe(this, {
            if (it == "00:00")
                enableButton()

            binding.tvCount.text = it
        })
    }

    private fun emptyValue(): Int {
        enableButton()
        return 0
    }

    private fun enableButton() {
        binding.btnStart.isClickable = true
    }

    private fun disableButton() {
        binding.btnStart.isClickable = false
    }
}