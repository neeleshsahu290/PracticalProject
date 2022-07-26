package com.example.practicalproject.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicalproject.R
import com.example.practicalproject.databinding.CoinitemBinding
import com.example.practicalproject.modelclass.CoinDetails

class HomeAdapter(var mcontext:Context,var itemList:List<CoinDetails>?=null) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coinitem,parent,false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = itemList?.get(position)

        holder.binding.txttitle.text=item?.name

        Glide
            .with(mcontext)
            .load(item?.pictures?.front?.url)
            .fitCenter()
            .dontAnimate()
            .optionalFitCenter()
            .placeholder(R.drawable.coin)
            .into(holder.binding.imgItem)



    }

    override fun getItemCount(): Int {
        return itemList?.size?:0
    }

    inner class HomeViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        val binding :CoinitemBinding  = CoinitemBinding.bind(itemView)

    }
}