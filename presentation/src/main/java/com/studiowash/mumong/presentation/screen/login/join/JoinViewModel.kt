
package com.studiowash.mumong.presentation.screen.login.join

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studiowash.mumong.presentation.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
) : ViewModel() {
    val availableData: LiveData<Boolean> get() = _availableData
    private val _availableData = MutableLiveData<Boolean>()

    val moveNextPageEvent: SingleLiveEvent<Boolean> get() = _moveNextPageEvent
    private val _moveNextPageEvent = SingleLiveEvent<Boolean>()

    fun setDataAvailability(isAvailable: Boolean) {
        _availableData.value = isAvailable
    }

    fun moveToNextPage() {
        moveNextPageEvent.value = true
    }
}