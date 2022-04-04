package com.studiowash.mumong.presentation.practice

import com.studiowash.mumong.ClickEvent

sealed class PracticeClickEvent : ClickEvent {
    object OnClickCalendarIcon: PracticeClickEvent()
}