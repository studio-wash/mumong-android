package com.studiowash.mumong.presentation.practice.home

import android.view.View
import androidx.lifecycle.ViewModel
import com.studiowash.mumong.presentation.SingleLiveData
import com.studiowash.mumong.presentation.practice.PracticeClickEvent

class PracticeHomeViewModel : ViewModel() {
    val clickEventLiveData = SingleLiveData<PracticeClickEvent>()

    fun onClickCalendarIcon(v: View) {
        clickEventLiveData.value = PracticeClickEvent.OnClickCalendarIcon
    }
}