package com.CodeAlpha.codealpha_task1.ui.action_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.CodeAlpha.codealpha_task1.databinding.FragmentActionBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ActionFragment : BottomSheetDialogFragment() {

    var cardAction: CardAction? = null
    private lateinit var binding: FragmentActionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.removeButton.setOnClickListener {
            cardAction?.onRemoveCard()
            dismissNow()
        }

        binding.editButton.setOnClickListener {
            cardAction?.onEditCard()
            dismissNow()
        }
    }
}
