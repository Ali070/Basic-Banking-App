package com.alisamir.basicbankingapp.ui.profile

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.alisamir.basicbankingapp.R
import com.alisamir.basicbankingapp.databinding.FragmentUserProfileBinding
import com.alisamir.basicbankingapp.pojo.Users
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

class UserProfileFragment : Fragment() {
    lateinit var users: Users
    lateinit var binding: FragmentUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            users = it.get("user") as Users

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userNamTv.text = users.name
        binding.accountNumTv.text = users.accountNumber
        binding.balanceTv.text = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(users.current_balance)
        binding.emailTv.text = users.email
        binding.ifscTv.text = users.IFSC_Code
        if(users.gender == "male"){
            Picasso.get().load(R.drawable.man).resize(150,150).into(binding.user)
        }else if(users.gender == "female"){
            Picasso.get().load(R.drawable.women).resize(150,150).into(binding.user)
        }
        val dialog = BottomSheetDialog(view.context)
        val modalSheetView = layoutInflater.inflate(R.layout.amount_dialog,null)
        dialog.window?.setBackgroundDrawable(ColorDrawable(0))
        dialog.setContentView(modalSheetView)
        dialog.setCancelable(false)
        val okBtn = modalSheetView.findViewById<Button>(R.id.okBtn)

        okBtn.setOnClickListener {
            val amountInput = modalSheetView.findViewById<EditText>(R.id.amountInputET)
            if(amountInput.text.isNotEmpty()){
                val amount = amountInput.text.toString().toDouble()
                if(amount>users.current_balance){
                    amountInput.error = getString(R.string.balance_error)
                    amountInput.requestFocus()
                }else{
                    val action = UserProfileFragmentDirections.actionUserProfileFragmentToChooseUserFragment(amount.toString(),users)
                    findNavController().navigate(action)
                    dialog.dismiss()
                }
            }else{
                amountInput.error = getString(R.string.no_money_input)
                amountInput.requestFocus()
            }
        }
        val cancelBtn = modalSheetView.findViewById<Button>(R.id.cancelBtn)
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        binding.transferBtn.setOnClickListener {
            dialog.show()
        }
    }
}