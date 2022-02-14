package com.alisamir.basicbankingapp.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alisamir.basicbankingapp.R
import com.alisamir.basicbankingapp.databinding.UserItemBinding
import com.alisamir.basicbankingapp.pojo.Users
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*

class UsersAdapter:ListAdapter<Users,UsersAdapter.myViewHolder>(DiffCallback) {
    interface onClickListner{
        fun onClick(users: Users)
    }
    var listner: onClickListner? = null
    fun setOnClickListner(l:onClickListner){
        listner = l
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Users>() {
        override fun areItemsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Users, newItem: Users): Boolean {
            return oldItem.name == newItem.name
        }


    }

    class myViewHolder(private val binding: UserItemBinding, private val listner: onClickListner) : RecyclerView.ViewHolder(binding.root) {
            fun bind(users: Users){
                if (users.gender == "male"){
                    Picasso.get().load(R.drawable.man).resize(60,60).into(binding.circleImageView)
                }else if(users.gender == "female"){
                    Picasso.get().load(R.drawable.women).resize(60,60).into(binding.circleImageView)
                }
                binding.nameTv.text = users.name

                binding.moneyTv.text = NumberFormat.getCurrencyInstance(Locale.getDefault()).format(users.current_balance)
                binding.root.setOnClickListener {
                    listner.onClick(users)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.myViewHolder {
        return myViewHolder(UserItemBinding.inflate(LayoutInflater.from(parent.context),parent,false),listner!!)
    }

    override fun onBindViewHolder(holder: UsersAdapter.myViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}