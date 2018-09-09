package com.toddperkins.myfirstapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

enum class CalculatorMode {
        none,addition,subtraction,multipliction
    }
class MainActivity : AppCompatActivity() {
    var labelString = ""
    var currentMode = CalculatorMode.none
    var savedNumber = 0
    var lastButtonWasMode = false
    var lastButtonWasEqual = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupCalculator()
    }

    fun setupCalculator() {
        val buttons = arrayOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9)
        for(i in buttons.indices) {
            buttons[i].setOnClickListener {
                didPressNumber(i)


            }
        }
        buttonPlus.setOnClickListener { changeMode(CalculatorMode.addition) }
        buttonMin.setOnClickListener { changeMode(CalculatorMode.subtraction) }
        buttonEqual.setOnClickListener { didpressEqual() }
        buttonC.setOnClickListener { didPressClear() }
        buttonMul.setOnClickListener{ changeMode(CalculatorMode.multipliction)}
    }


    fun didpressEqual() {
        if(lastButtonWasMode) return
        if (lastButtonWasEqual) return
        var result = 0

        when (currentMode) {
            CalculatorMode.addition -> result = savedNumber + labelString.toInt()
            CalculatorMode.subtraction -> result = savedNumber - labelString.toInt()
            CalculatorMode.multipliction -> result = savedNumber * labelString.toInt()
        }
        labelString = result.toString()
        updatetext()
        lastButtonWasEqual =  true

    }
    fun didPressClear() {
        currentMode = CalculatorMode.none
        savedNumber = 0
        lastButtonWasMode = false
        lastButtonWasEqual = false
        labelString = "0"
        updatetext()

    }

    fun didPressNumber(num:Int) {
        if(lastButtonWasEqual) return
        lastButtonWasEqual = false

        if(lastButtonWasMode){
            labelString = "$num"
            lastButtonWasMode = false

        } else {
            labelString = "$labelString$num"
        }
        updatetext()

    }

    fun changeMode(mode:CalculatorMode) {
        lastButtonWasEqual = false
        currentMode = mode
        savedNumber = labelString.toInt()
        lastButtonWasMode = true



    }

    fun updatetext() {

        val labelInt = labelString.toInt()
        labelString = labelInt.toString()
        //labelString = labelString.toInt().toString()
        if (labelString.length>5)
        {
            textView.setText("ERROR")
            return
        }

        textView.setText(labelString)

    }
}




