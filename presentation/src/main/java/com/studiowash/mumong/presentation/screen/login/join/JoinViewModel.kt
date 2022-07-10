
package com.studiowash.mumong.presentation.screen.login.join

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
) : ViewModel() {
    val availableData: LiveData<Boolean> get() = _availableData
    private val _availableData = MutableLiveData<Boolean>()

    fun setDataAvailability(isAvailable: Boolean) {
        _availableData.value = isAvailable
    }
}