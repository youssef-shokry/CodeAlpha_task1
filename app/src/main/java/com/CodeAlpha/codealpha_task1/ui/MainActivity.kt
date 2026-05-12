package com.CodeAlpha.codealpha_task1.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.CodeAlpha.codealpha_task1.CardsDataSource.getCardsList
import com.CodeAlpha.codealpha_task1.databinding.ActivityMainBinding
import com.CodeAlpha.codealpha_task1.models.CardDataModel
import com.CodeAlpha.codealpha_task1.ui.add_fragment.AddFragment
import com.CodeAlpha.codealpha_task1.ui.add_fragment.CardSubmitted

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cards: MutableList<CardDataModel>
    private lateinit var addedCardsList: MutableList<CardDataModel>
    private val questionButtonText = "Show Answer"
    private val answerButtonText = "Return To Question"
    private var index = 0
    private var isCardFlipped = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cards = getCardsList(this).toMutableList()

        showCurrentCard()
        initClickListeners()
    }

    private fun showCurrentCard() {
        binding.cardText.text = cards[index].question
        if (binding.cardText.text == cards[index].question) {
            binding.cardButton.text = questionButtonText
        } else {
            binding.cardButton.text = answerButtonText
        }
    }

    private fun initClickListeners() {
        nextButton()

        previousButton()

        addButton()

        cardButton()
    }

    private fun nextButton() {
        binding.nextButton.setOnClickListener {
            if (index == cards.size - 1) {
                return@setOnClickListener
            } else if (isCardFlipped) {
                flipCard()
            }
            binding.cardText.text = cards[index + 1].question
            index++
        }
    }

    private fun previousButton() {
        binding.previousButton.setOnClickListener {
            if (index == 0) {
                return@setOnClickListener
            } else if (isCardFlipped) {
                flipCard()
            }
            binding.cardText.text = cards[(index - 1)].question
            index--
        }
    }

    private fun addButton() {
        binding.addButton.setOnClickListener {

            val addFragment = AddFragment()
            addFragment.show(supportFragmentManager, "Add")

            addFragment.cardSubmitted = object : CardSubmitted {
                override fun onCardSubmitted(card: CardDataModel) {
                    addedCardsList = mutableListOf()
                    addedCardsList.add(0, card)

                    for (i in 0 until cards.size){
                        addedCardsList.add((i+1), cards[i])
                    }

                    cards = addedCardsList
                    showCurrentCard()
                }
            }
        }
    }

    private fun cardButton() {
        binding.cardButton.setOnClickListener {
            flipCard()
        }
    }

    private fun animationEffect() {
        binding.card.animate().apply {
            duration = 1000
            rotationYBy(180f)
            binding.cardText.animate().rotationYBy(180f)
            binding.cardButton.animate().rotationYBy(180f)
        }.start()
    }

    private fun flipCard() {
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