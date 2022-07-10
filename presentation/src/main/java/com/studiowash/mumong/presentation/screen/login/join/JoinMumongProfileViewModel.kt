package com.studiowash.mumong.presentation.screen.login.join

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studiowash.mumong.presentation.SingleLiveEvent

class JoinMumongProfileViewModel: ViewModel() {
    val availableData: LiveData<Boolean> get() = _availableData
    private val _availableData = MutableLiveData<Boolean>()

    val moveNextPageEvent: SingleLiveEvent<Boolean> get() = _moveNextPageEvent
    private val _moveNextPageEvent = SingleLiveEvent<Boolean>()

    fun checkDataAvailability(id: String, name: String) {
        val available = id.isNotBlank() && name.isNotBlank() // TODO
        _availableData.value = available
    }

    fun moveToNextPage() {
        _moveNextPageEvent.value = true
    }
}