package com.demo.calculatormigration.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import com.demo.calculatormigration.R
import com.demo.calculatormigration.model.CalculatorButton
import com.demo.calculatormigration.model.CalculatorButtons
import com.demo.calculatormigration.model.CalculatorKey

class ButtonAdapter(
    private val context: Context,
    private val onKeyPressed: (CalculatorKey) -> Unit
) : BaseAdapter() {

    private val buttons = CalculatorButtons.All

    override fun getCount(): Int = buttons.size

    override fun getItem(position: Int): CalculatorButton = buttons[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_button, parent, false)
        val buttonView = view.findViewById<Button>(R.id.button)
        val calculatorButton = getItem(position)
        if (calculatorButton.text.isNotEmpty()) {
            buttonView.text = calculatorButton.text
            buttonView.setOnClickListener { onKeyPressed(calculatorButton.key) }
        } else {
            buttonView.visibility = View.INVISIBLE
        }
        return buttonView
    }
}