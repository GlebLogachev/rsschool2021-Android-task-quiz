package com.rsschool.quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MainActivityInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override var listOfQuestion: MutableList<Answer> = mutableListOf()

    override fun updateAnswerList(answer: Answer) {
        listOfQuestion.add(answer)
    }
}