package com.CodeAlpha.codealpha_task1.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.CodeAlpha.codealpha_task1.databinding.CardItemBinding
import com.CodeAlpha.codealpha_task1.models.Cards

class CardsAdapter(val cards: List<Cards>) : RecyclerView.Adapter<CardsAdapter.CardViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardItemBinding.inflate(inflater, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CardViewHolder,
        position: Int
    ) {
        holder.bind(cards[position])
    }

    override fun getItemCount(): Int = cards.size

    class CardViewHolder(val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Cards){
            binding.cardText.text = card.question
        }
    }
}