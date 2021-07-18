package com.devje.secondaryspinner

import android.view.View
import android.widget.AdapterView

class PrimarySpinnerListener : AdapterView.OnItemSelectedListener, PrimarySpinnerObserver{
    private val observables = ArrayList<PrimarySpinnerObservable>()

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        sendSpinnerPosition(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // Another interface callback
    }

    fun sendSpinnerPosition(position: Int){
        notifyPosition(position)
    }

    override fun subscribe(observable: PrimarySpinnerObservable) {
        observables.add(observable)
    }

    override fun unsubscribe(observable: PrimarySpinnerObservable) {
        observables.remove(observable)
    }

    override fun notifyPosition(position: Int) {
        observables.forEach{observable -> observable.updatePrimary(position) }
    }
}