package com.winision.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateUI("")
    }


    val operationList: MutableList<String> = arrayListOf()
    val numCache: MutableList<String> = arrayListOf()

    private fun extractString (items: List<String>, connect: String = ""): String {
        if (items.isEmpty()) return ""
        return items.reduce { acc, s ->  acc + connect + s}
    }

    private fun updateUI (mainUIString: String) {
        val calculationString = extractString(operationList, "")
        var calculationTxtView = findViewById<View>(R.id.numberDisp) as TextView
        calculationTxtView.text = calculationString

        val ans = findViewById<View>(R.id.display) as TextView
        ans.text = mainUIString
    }

    fun numberSmash (view: View) {
        val button = view as Button
        val numString = button.text

        numCache.add (numString.toString())
        val text = extractString(numCache)
        updateUI(text)
    }

    fun operatorSmash (view: View) {
        val button = view as Button
        if (numCache.isEmpty()) return

        operationList.add(extractString(numCache))
        numCache.clear()
        operationList.add(button.text.toString())
        updateUI(button.text.toString())
    }

    private fun clearCache () {
        operationList.clear()
        numCache.clear()
    }

    fun allClear (view: View) {
        clearCache()
        updateUI("")
    }

    fun equalSmash (view: View) {
        operationList.add (extractString(numCache))
        numCache.clear()

        val calculator = StringCalculator()
        val answer = calculator.calculate(operationList)

        updateUI("=" + answer.toString())
        clearCache()

    }

}
