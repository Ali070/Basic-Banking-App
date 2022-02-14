package com.alisamir.basicbankingapp.ui.transactions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.alisamir.basicbankingapp.R
import com.alisamir.basicbankingapp.data.TransfersAdapter
import com.alisamir.basicbankingapp.databinding.FragmentTransactionsBinding


class TransactionsFragment : Fragment() {
    val transactionViewModel:TransactionsViewModel by viewModels()
    lateinit var binding:FragmentTransactionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransactionsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactionViewModel.transactions.observe(viewLifecycleOwner,{
            if(it.isEmpty()){
                binding.noTransTv.visibility = View.VISIBLE
                binding.transfersList.visibility = View.GONE
            }else {
                binding.noTransTv.visibility = View.GONE
                binding.transfersList.visibility = View.VISIBLE
                val adapter = TransfersAdapter(it.reversed())
                binding.transfersList.adapter = adapter
            }
        })
    }


}