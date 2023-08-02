package com.example.mobilliumtask3.part2

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mobilliumtask3.databinding.FragmentGuessNumberBinding
import com.google.android.material.snackbar.Snackbar




class GuessNumberFragment : Fragment() {

    private lateinit var binding: FragmentGuessNumberBinding
    private val viewModel: GuessNumberVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentGuessNumberBinding.inflate(inflater)
        uiView()
        viewModel.randomValues()
        toDeailFragment()
        return binding.root
    }

    private fun uiView() = with(binding) {
        guessBtn.setOnClickListener {
            try {
                val guess = guessNumberEdittxt.text.toString().toInt()
                checkGuess(guess)
            } catch (e: NumberFormatException) {
                val snack: Snackbar = Snackbar.make(binding.root,"Please Enter A Number!",Snackbar.LENGTH_LONG).setBackgroundTint(
                    Color.rgb(114, 111, 255))
                val view = snack.view
                val params = view.layoutParams as FrameLayout.LayoutParams
                params.gravity = Gravity.TOP
                view.layoutParams = params
                snack.show()
            }


        }
        newGameBtn.setOnClickListener {
            guessNumberEdittxt.text.clear()
            viewModel.randomValues()
        }


    }

    private fun checkGuess(guess: Int) {
        val isMatch = viewModel.checkGuess(guess)
        if (isMatch) {
            val snack: Snackbar = Snackbar.make(binding.root,"True!",Snackbar.LENGTH_LONG).setBackgroundTint(
                Color.rgb(114, 255, 111))
            val view = snack.view
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP
            view.layoutParams = params
            snack.show()

        } else {
            val snack: Snackbar = Snackbar.make(binding.root, "Retry!", Snackbar.LENGTH_LONG).setBackgroundTint(
                Color.rgb(255, 114, 111))
            val view = snack.view
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.TOP
            view.layoutParams = params
            snack.show()
            binding.guessNumberEdittxt.text.clear()

        }
    }

    private fun toDeailFragment() = with(binding) {
        guessNumber.setOnClickListener {
            findNavController().navigate(GuessNumberFragmentDirections
                .actionGuessNumberFragmentToDetailFragment(viewModel.getNumber())
            )
        }
    }

}