package com.example.mobilliumtask3.part1

import androidx.lifecycle.ViewModel

class CounterVM : ViewModel(){


    private var counter = 0

    // "incrementCounter" fonksiyonu ile sayaç değerini arttırma
    fun incrementCounter() {
        counter++
    }

    // "getCounter" fonksiyonu ile sayaç değerini integer döndürme
    fun getCounter(): Int {
        return counter
    }
}