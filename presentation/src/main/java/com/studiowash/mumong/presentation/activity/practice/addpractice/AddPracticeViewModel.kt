package com.studiowash.mumong.presentation.activity.practice.addpractice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddPracticeViewModel: ViewModel() {
    val metronomeTunerStatus: MutableLiveData<MetronomeTunerStatus> get() = _metronomeTunerStatus
    private val _metronomeTunerStatus = MutableLiveData(MetronomeTunerStatus.None)

    fun updateMetronomeTunerStatus(status: MetronomeTunerStatus) {
        _metronomeTunerStatus.value = status
    }

    enum class MetronomeTunerStatus { Metronome, Tuner, None }
}