package com.demo.calculatormigration.view

import android.os.Bundle
import android.widget.GridView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.demo.calculatormigration.viewmodel.CalculatorEngineViewModel
import com.demo.calculatormigration.R
import com.demo.calculatormigration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: CalculatorEngineViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val gridView = findViewById<GridView>(R.id.buttonGridView)
        gridView.adapter = ButtonAdapter(this, viewModel::processKeyPress)
    }
}