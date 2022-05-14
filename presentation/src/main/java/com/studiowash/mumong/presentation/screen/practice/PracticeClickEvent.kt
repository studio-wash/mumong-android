package com.studiowash.mumong.presentation.screen.practice

import com.studiowash.mumong.presentation.ClickEvent

sealed class PracticeClickEvent : ClickEvent {
    object OnClickCalendarIcon: PracticeClickEvent()
    object OnClickAddNewPractice: PracticeClickEvent()
}