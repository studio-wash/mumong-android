package com.studiowash.mumong.presentation.screen.login.join.instrument

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studiowash.mumong.presentation.screen.common.instrument.Instrument

class JoinMumongInstrumentViewModel: ViewModel() {
    val availableData: LiveData<Boolean> get() = _availableData
    private val _availableData = MutableLiveData<Boolean>()

    val moveMainActivityEvent: LiveData<Boolean> get() = _moveMainActivityEvent
    private val _moveMainActivityEvent = MutableLiveData<Boolean>()

    val selectedInstruments: LiveData<List<Instrument>> get() = _selectedInstruments
    private val _selectedInstruments = MutableLiveData<List<Instrument>>()

    fun checkDataAvailability() {
        val available = true // selectedInstruments.value?.isNotEmpty() ?: false // TODO
        _availableData.value = available
    }

    fun moveToMainActivity() {
        _moveMainActivityEvent.value = true
    }
}