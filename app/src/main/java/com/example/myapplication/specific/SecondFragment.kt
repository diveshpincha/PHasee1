package com.example.myapplication.specific

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.CountriesDataBase
import com.example.myapplication.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSecondBinding.inflate(inflater)

        val countryId =arguments?.let{ SecondFragmentArgs.fromBundle(it) }

        val reqId = countryId?.key

        val application= requireNotNull(this.activity).application

        val dataSource= CountriesDataBase.getInstance(application).countriesDao

        val viewModelFactory= reqId?.let { SecondViewModelFactory(dataSource, it) }

        val viewModel = viewModelFactory?.let {
            ViewModelProvider(this,
                it
            ).get(SecondViewModel::class.java)
        }
        binding.data= viewModel?.disData?.value
        binding.lifecycleOwner=this

        binding.executePendingBindings()

        //binding.population.text=

        return binding.root
    }

}