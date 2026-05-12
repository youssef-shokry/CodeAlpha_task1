package com.CodeAlpha.codealpha_task1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.CodeAlpha.codealpha_task1.CardsDataSource.getCardsList
import com.CodeAlpha.codealpha_task1.databinding.ActivityMainBinding
import com.CodeAlpha.codealpha_task1.models.CardDataModel
import com.CodeAlpha.codealpha_task1.ui.add_fragment.AddFragment
import com.CodeAlpha.codealpha_task1.ui.add_fragment.CardSubmitted

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cards: MutableList<CardDataModel>
    private var index = 0
    private var isCardFlipped = false
    private val questionButtonText = "Show Answer"
    private val answerButtonText = "Return To Question"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cards = getCardsList(this).toMutableList()

        savedInstanceState?.let {
            index = it.getInt("current_index", 0)
            isCardFlipped = it.getBoolean("is_flipped", false)
        }


        if (cards.isEmpty()) {
            binding.cardText.text = "No cards available"
            binding.cardButton.isEnabled = false
            return
        }

        if (index >= cards.size) index = 0

        showCurrentCard()
        initClickListeners()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("current_index", index)
        outState.putBoolean("is_flipped", isCardFlipped)
    }

    private fun showCurrentCard() {
        val currentCard = cards[index]

        binding.cardText.text = if (isCardFlipped) currentCard.answer else currentCard.question
        binding.cardButton.text = if (isCardFlipped) answerButtonText else questionButtonText
    }

    private fun initClickListeners() {
        binding.nextButton.setOnClickListener { goToNextCard() }
        binding.previousButton.setOnClickListener { goToPreviousCard() }
        binding.addButton.setOnClickListener { showAddFragment() }
        binding.cardButton.setOnClickListener { flipCard() }
    }

    private fun goToNextCard() {
        if (index >= cards.size - 1) return

        resetToQuestionSide()
        index++
        showCurrentCard()
    }

    private fun goToPreviousCard() {
        if (index <= 0) return

        resetToQuestionSide()
        index--
        showCurrentCard()
    }

    private fun resetToQuestionSide() {
        isCardFlipped = false
    }

    private fun flipCard() {
        isCardFlipped = !isCardFlipped
        animationEffect()
        showCurrentCard()
    }

    private fun animationEffect() {
        binding.card.animate().apply {
            duration = 600
            rotationYBy(180f)
        }.start()

        binding.cardText.animate().apply {
            duration = 600
            rotationYBy(180f)
        }.start()

        binding.cardButton.animate().apply {
            duration = 600
            rotationYBy(180f)
        }.start()
    }

    private fun showAddFragment() {
        val addFragment = AddFragment()
        addFragment.show(supportFragmentManager, "AddFragment")

        addFragment.cardSubmitted = object : CardSubmitted {
            override fun onCardSubmitted(card: CardDataModel) {
                cards.add(0, card)
                index = 0
                isCardFlipped = false
                showCurrentCard()
            }
        }
    }
}