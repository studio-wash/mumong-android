package com.studiowash.mumong.practice

import com.studiowash.mumong.ClickEvent

sealed class PracticeClickEvent : ClickEvent {
    object OnClickCalendarIcon: PracticeClickEvent()
}