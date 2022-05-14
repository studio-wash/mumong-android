package com.studiowash.mumong.presentation.screen.practice.addpractice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class AddPracticeViewModel: ViewModel() {
    val metronomeTunerStatus: MutableLiveData<MetronomeTunerStatus> get() = _metronomeTunerStatus
    private val _metronomeTunerStatus = MutableLiveData(MetronomeTunerStatus.None)

    val todayPracticeTimeSec: MutableLiveData<Int> get() = _todayPracticeTimeSec
    private val _todayPracticeTimeSec = MutableLiveData(0)

    private var practiceTimer: Timer? = null

    fun updateMetronomeTunerStatus(status: MetronomeTunerStatus) {
        _metronomeTunerStatus.value = status
    }

    fun startCountingTime() {
        practiceTimer = Timer()
        // todo: create fun
        val timerTask = object: TimerTask() {
            override fun run() {
                println("RUN TASK")
                _todayPracticeTimeSec.postValue(_todayPracticeTimeSec.value?.plus(1))
            }
        }
        practiceTimer?.schedule(timerTask, 0, SECOND_IN_MILLI_LONG)
    }

    fun stopCountingTime() {
        practiceTimer?.cancel()
    }

    enum class MetronomeTunerStatus { Metronome, Tuner, None }

    private companion object {
        const val SECOND_IN_MILLI_LONG = 1000L
    }
}