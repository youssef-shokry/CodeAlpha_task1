package com.CodeAlpha.codealpha_task1.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.CodeAlpha.codealpha_task1.CardsDataSource
import com.CodeAlpha.codealpha_task1.databinding.ActivityMainBinding
import com.CodeAlpha.codealpha_task1.models.Cards

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var cards: MutableList<Cards> = mutableListOf()
    private  lateinit var adapter: CardsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initRv()
        Log.e("cards", "cards: ${cards[0].question}, ${cards[0].answer}")
    }

    private fun initRv() {
        adapter = CardsAdapter(cards)
        binding.cardRecyclerview.adapter = adapter
    }

    private fun initVariables() {
        cards = CardsDataSource.getCardsList(this).toMutableList()
    }
}