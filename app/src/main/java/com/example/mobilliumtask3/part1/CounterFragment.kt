package com.example.mobilliumtask3.part1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mobilliumtask3.databinding.FragmentCounterBinding

class CounterFragment : Fragment() {

    private var counter = 0

    // ViewModel sınıfının nesnesi "viewModel"ı oluşturma
    private val viewModel: CounterVM by viewModels()

    private lateinit var binding: FragmentCounterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCounterBinding.inflate(inflater)

        binding.counterBtn.setOnClickListener {
            var buttonValue = binding.counterNumber.text.toString().toInt()
            buttonValue += 1
            binding.counterNumber.text = buttonValue.toString()
        }

        return binding.root
    }

    // Fragment oluşturulduğunda çağrılan onViewCreated() fonksiyonu
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notViewModel()
        // viewModelSwitch adlı switch bileşeninin durumunu dinleme
        binding.viewModelSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // Switch açıldıysa, ViewModel kullanarak sayaç işlemlerini yapacak metodu çağırma
                useViewModel()
            } else {
                // Switch kapalıysa, ViewModel kullanmadan sayaç işlemlerini yapacak metodu çağırma
                notViewModel()
            }
        }
    }

    // ViewModel kullanarak sayaç işlemlerini yapacak metod
    fun useViewModel() = with(binding) {
        // ViewModel'den sayaç değerini alma
        counter = viewModel.getCounter()

        // TextView'e güncel sayaç değerini atama
        counterNumber.text = counter.toString()

        // Arttırma butonuna tıklandığında ViewModel ile sayaç değerini arttırma
        counterBtn.setOnClickListener {
            viewModel.incrementCounter()

            // Arttırılan yeni sayaç değerini alma ve TextView'e atama
            counter = viewModel.getCounter()
            counterNumber.text = counter.toString()
        }
    }

    // ViewModel kullanmadan sayaç işlemlerini yapacak metod.
    fun notViewModel() = with(binding) {
        // TextView'e mevcut sayaç değerini atama
        counterNumber.text = counter.toString()

        // Arttırma butonuna tıklandığında sayaç değerini arttırma
        counterBtn.setOnClickListener {
            ++counter

            // Yeni arttırılmış sayaç değerini TextView'e atama
            counterNumber.text = counter.toString()
        }

    }
}