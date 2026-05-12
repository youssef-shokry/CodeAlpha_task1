package com.CodeAlpha.codealpha_task1

import android.content.Context
import com.CodeAlpha.codealpha_task1.models.CardDataModel

object CardsDataSource {
    private val cardDataModelList: MutableList<CardDataModel> = mutableListOf()
    private var isLoaded = false

    private fun initCardsList(context: Context) {
        if (isLoaded) return

        val questionsInputStream = context.applicationContext.assets.open("questions.txt")
        val questionsReader = questionsInputStream.bufferedReader()

        val answersInputStream = context.applicationContext.assets.open("answers.txt")
        val answersReader = answersInputStream.bufferedReader()

        var questionLine = questionsReader.readLine()
        var answerLine = answersReader.readLine()

        while (questionLine != null && answerLine != null) {
            cardDataModelList.add(
                CardDataModel(
                    questionLine.trim(),
                    answerLine.trim()
                )
            )

            questionLine = questionsReader.readLine()
            answerLine = answersReader.readLine()
        }

        isLoaded = true
    }

    fun getCardsList(context: Context): List<CardDataModel> {
        initCardsList(context)
        return cardDataModelList
    }
}