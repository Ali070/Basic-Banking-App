package com.alisamir.basicbankingapp.ui.transfer

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alisamir.basicbankingapp.R
import com.alisamir.basicbankingapp.data.UsersAdapter
import com.alisamir.basicbankingapp.databinding.FragmentChooseUserBinding
import com.alisamir.basicbankingapp.pojo.Transfer
import com.alisamir.basicbankingapp.pojo.Users


class ChooseUserFragment : Fragment() {
    lateinit var binding: FragmentChooseUserBinding
    val usersModel:ChooseUserViewModel by viewModels()
    var amount:Double? = null
    var mainUser:Users? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
           amount = it.getString("amount_money")?.toDouble()
            mainUser = it.get("user") as Users?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val callback = object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val alertDialog = AlertDialog.Builder(requireContext())
                    .setMessage("Do you want to cancel the transaction?")
                    .setIcon(R.drawable.ic_bank_icon)
                    .setPositiveButton("Yes"){ dialoginterface, _ ->
                        Toast.makeText(requireContext(), "Transaction canceled successfully", Toast.LENGTH_SHORT).show()
                        dialoginterface.cancel()
                        findNavController().navigate(R.id.action_chooseUserFragment_to_usersFragment)
                    }
                    .setNegativeButton("No"){ dialoginterface, _ ->
                        dialoginterface.cancel()
                    }
                alertDialog.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callback)
        binding = FragmentChooseUserBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainUser?.id?.let { usersModel.getUsers(it) }
        val adapter = UsersAdapter()
        adapter.setOnClickListner(object : UsersAdapter.onClickListner{
            override fun onClick(users: Users) {
                users.current_balance = users.current_balance + amount!!
                mainUser?.current_balance = mainUser?.current_balance?.minus(amount!!)!!
                usersModel.updateUserMoney(users)
                mainUser?.let { usersModel.updateUserMoney(it) }
                val transfer = Transfer(0, mainUser?.id!!, amount!!,mainUser?.name!!,users.name)
                usersModel.addTransaction(transfer)
                Toast.makeText(view.context, "Transaction made successfully", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_chooseUserFragment_to_usersFragment)

            }

        })
        usersModel.users.observe(viewLifecycleOwner,{
           adapter.submitList(it)
           binding.usersList.adapter = adapter
        })

    }
}