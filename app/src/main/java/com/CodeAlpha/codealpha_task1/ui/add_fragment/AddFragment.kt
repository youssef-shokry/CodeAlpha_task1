package com.CodeAlpha.codealpha_task1.ui.add_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.CodeAlpha.codealpha_task1.databinding.FragmentAddBinding
import com.CodeAlpha.codealpha_task1.models.CardDataModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddFragment : BottomSheetDialogFragment() {

    var cardSubmitted: CardSubmitted? = null
    private lateinit var binding: FragmentAddBinding
    private var answer: String = ""
    private var question: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.submitButton.setOnClickListener {
            question = binding.questionEditTextLayout.editText?.text.toString()
            answer = binding.answerEditTextLayout.editText?.text.toString()

            dismissNow()
        }

        passData()
    }

    fun passData(){
        cardSubmitted?.onCardSubmitted(CardDataModel(question, answer))
    }
}