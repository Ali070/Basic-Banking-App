package com.alisamir.basicbankingapp.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.alisamir.basicbankingapp.data.UsersAdapter
import com.alisamir.basicbankingapp.databinding.FragmentUsersBinding
import com.alisamir.basicbankingapp.pojo.Users


class UsersFragment : Fragment() {
    val usersModel:UsersViewModel by viewModels()
    lateinit var binding: FragmentUsersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentUsersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UsersAdapter()
        binding.usersList.adapter = adapter
        usersModel.users.observe(viewLifecycleOwner,{
            adapter.submitList(it)
            adapter.setOnClickListner(object :UsersAdapter.onClickListner{
                override fun onClick(users: Users) {
                    findNavController().navigate(UsersFragmentDirections.actionUsersFragmentToUserProfileFragment(users))
                }

            })

        })

    }


}