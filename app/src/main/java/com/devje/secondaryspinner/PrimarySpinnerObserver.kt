package com.devje.secondaryspinner

interface PrimarySpinnerObserver {
    fun subscribe(observable: PrimarySpinnerObservable)
    fun unsubscribe(observable: PrimarySpinnerObservable)
    fun notifyPosition(position: Int)
}