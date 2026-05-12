package com.CodeAlpha.codealpha_task1.ui.edit_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.CodeAlpha.codealpha_task1.databinding.FragmentAddBinding
import com.CodeAlpha.codealpha_task1.models.CardDataModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditFragment : BottomSheetDialogFragment() {

    var cardEdited: CardEdited? = null
    private lateinit var binding: FragmentAddBinding
    private var currentCard: CardDataModel? = null

    fun setCurrentCard(card: CardDataModel) {
        currentCard = card
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        currentCard?.let {
            binding.questionEditTextLayout.editText?.setText(it.question)
            binding.answerEditTextLayout.editText?.setText(it.answer)
        }

        binding.submitButton.setOnClickListener {
            val question = binding.questionEditTextLayout.editText?.text.toString()
            val answer = binding.answerEditTextLayout.editText?.text.toString()

            cardEdited?.onCardEdited(CardDataModel(question, answer))
            dismissNow()
        }
    }
}
