package com.studiowash.mumong.presentation.screen.common.instrument

sealed class Instrument(val name: String) {
    object Piano: Instrument("piano")
    object Guitar: Instrument("guitar")
    class Etc(name: String): Instrument(name)
}