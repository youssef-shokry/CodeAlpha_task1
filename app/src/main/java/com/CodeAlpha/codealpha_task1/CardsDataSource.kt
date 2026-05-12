package com.CodeAlpha.codealpha_task1

import android.content.Context
import com.CodeAlpha.codealpha_task1.models.Cards
import kotlin.also

object CardsDataSource {
    private val questionsList: MutableList<String> = mutableListOf()
    private val answersList: MutableList<String>  = mutableListOf()
    private var isLoaded = false

    private val cardsList: List<Cards> = mutableListOf<Cards>().also { cardsList ->
        for(i in 0 until answersList.size){
            cardsList.add(Cards(questionsList[i], answersList[i]))
        }
    }.toList()

    private fun initCardsList(context: Context){
        if (isLoaded) return

        val questionsInputStream = context.applicationContext.assets.open("questions.txt")
        val questionsReader = questionsInputStream.bufferedReader()

        val answersInputStream = context.applicationContext.assets.open("answers.txt")
        val answersReader = answersInputStream.bufferedReader()

        var questionsLine = questionsReader.readLine()
        var answersLine = answersReader.readLine()

        while (questionsLine != null){
            questionsList.add(questionsLine.trim())
            answersList.add(answersLine.trim())
            questionsLine = questionsReader.readLine()
            answersLine = answersReader.readLine()
        }
        questionsList.toList()
        answersList.toList()

        isLoaded = true
    }
    fun getCardsList(context: Context): List<Cards>{
        initCardsList(context)
        return cardsList
    }
}