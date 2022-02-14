package com.alisamir.basicbankingapp.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alisamir.basicbankingapp.databinding.TransferItemBinding
import com.alisamir.basicbankingapp.pojo.Transfer
import java.text.NumberFormat
import java.util.*

class TransfersAdapter(val transferList: List<Transfer>): RecyclerView.Adapter<TransfersAdapter.TransfersViewHolder>() {

    class TransfersViewHolder(val binding: TransferItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(transfer: Transfer){
            binding.fromUserTv.text = transfer.fromUser
            binding.toUserTv.text = transfer.toUser
            binding.amountTv.text = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(transfer.amount)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransfersViewHolder {
        return TransfersViewHolder(TransferItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TransfersViewHolder, position: Int) {
        holder.bind(transferList[position])
    }

    override fun getItemCount(): Int {
        return transferList.size
    }
}