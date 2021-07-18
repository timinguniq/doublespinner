package com.devje.secondaryspinner

interface SecondarySpinnerObserver {
    fun subscribe(observable: SecondarySpinnerObservable)
    fun unsubscribe(observable: SecondarySpinnerObservable)
    fun notifyPosition(position: Int)
}