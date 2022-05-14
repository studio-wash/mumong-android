package com.studiowash.mumong.presentation.screen.practice.home

import android.view.View
import androidx.lifecycle.ViewModel
import com.studiowash.mumong.presentation.SingleLiveEvent
import com.studiowash.mumong.presentation.screen.practice.PracticeClickEvent

class PracticeHomeViewModel : ViewModel() {
    val onClickLiveEvent = SingleLiveEvent<PracticeClickEvent>()

    fun onClickCalendarIcon(v: View) {
        onClickLiveEvent.value = PracticeClickEvent.OnClickCalendarIcon
    }

    fun onClickAddPractice(v: View) {
        onClickLiveEvent.value = PracticeClickEvent.OnClickAddNewPractice
    }
}