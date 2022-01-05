package com.studiowash.mumong.practice.home

import android.view.View
import androidx.lifecycle.ViewModel
import com.studiowash.mumong.SingleLiveData
import com.studiowash.mumong.practice.PracticeClickEvent

class PracticeHomeViewModel : ViewModel() {
    val clickEventLiveData = SingleLiveData<PracticeClickEvent>()

    fun onClickCalendarIcon(v: View) {
        clickEventLiveData.value = PracticeClickEvent.OnClickCalendarIcon
    }
}