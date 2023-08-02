package com.example.mobilliumtask3.part2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GuessNumberVM: ViewModel() {
    private val _randomNumber = MutableLiveData<Int>()
    val number: LiveData<Int> = _randomNumber

    fun randomValues() {
        _randomNumber.value = (0..9).random()
    }

    fun getNumber(): String{
        return _randomNumber.value.toString()
    }


    fun checkGuess(guess: Int): Boolean {
        return guess == _randomNumber.value
    }
}