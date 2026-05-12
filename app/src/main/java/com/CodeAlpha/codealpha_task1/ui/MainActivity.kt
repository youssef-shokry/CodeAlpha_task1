package com.CodeAlpha.codealpha_task1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.CodeAlpha.codealpha_task1.CardsDataSource.getCardsList
import com.CodeAlpha.codealpha_task1.databinding.ActivityMainBinding
import com.CodeAlpha.codealpha_task1.models.CardDataModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cards: MutableList<CardDataModel>
    private val questionButtonText = "Show Answer"
    private val answerButtonText = "Return To Question"
    private var index = 0
    private var isCardFlipped = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cards = getCardsList(this).toMutableList()

        initBinding()
        initClickListeners()
    }

    private fun initBinding() {
        binding.cardText.text = cards[index].question
        if (binding.cardText.text == cards[index].question) {
            binding.cardButton.text = questionButtonText
        } else {
            binding.cardButton.text = answerButtonText
        }
    }

    private fun initClickListeners() {
        binding.nextButton.setOnClickListener {
            if (binding.cardText.text == cards[(cards.size - 1)].question) {
                return@setOnClickListener
            } else if (isCardFlipped) {
                flipCard()
            }
            binding.cardText.text = cards[index + 1].question
            index++
        }

        binding.previousButton.setOnClickListener {
            if (binding.cardText.text == cards[0].question) {
                return@setOnClickListener
            } else if (isCardFlipped) {
                flipCard()
            }
            binding.cardText.text = cards[(index - 1)].question
            index--
        }

        binding.addButton.setOnClickListener {
            //TODO make a sheet
        }

        binding.cardButton.setOnClickListener {
            flipCard()
        }
    }

    fun animationEffect() {
        binding.card.animate().apply {
            duration = 1000
            rotationYBy(180f)
            binding.cardText.animate().rotationYBy(180f)
            binding.cardButton.animate().rotationYBy(180f)
        }.start()
    }

    fun flipCard() {
        if (!isCardFlipped) {
            isCardFlipped = true
            animationEffect()
            binding.cardText.text = cards[index].answer
            binding.cardButton.text = answerButtonText
        } else {
            isCardFlipped = false
            animationEffect()
            binding.cardText.text = cards[index].question
            binding.cardButton.text = questionButtonText
        }
    }
}