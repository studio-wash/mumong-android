package com.studiowash.mumong.presentation.activity.social.friend

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.studiowash.mumong.domain.social.entity.OnlineFriendEntity
import com.studiowash.mumong.presentation.databinding.DialogCheerFriendBinding

class CheerFriendDialog private constructor(context: Context, private val friend: OnlineFriendEntity): Dialog(context) {
    private lateinit var binding: DialogCheerFriendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogCheerFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        initView()
    }

    fun initView() {
        binding.item = friend
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }
    
    companion object {
        fun newInstance(context: Context, friend: OnlineFriendEntity) = CheerFriendDialog(context, friend)
    }
}