package com.rsschool.quiz

interface MainActivityInterface {
    var listOfQuestion: MutableList<Answer>
    fun updateAnswerList(answer: Answer)
}