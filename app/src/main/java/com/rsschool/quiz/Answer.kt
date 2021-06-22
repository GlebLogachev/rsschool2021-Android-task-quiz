package com.rsschool.quiz

data class Answer(
    val id: Int,
    val questionText: String,
    val questionNumber: Int,
    val answerText: String,
    val isRight: Boolean
)
