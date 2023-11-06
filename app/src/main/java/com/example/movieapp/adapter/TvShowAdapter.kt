package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.movieapp.databinding.TvShowLayoutAdapterBinding
import com.example.movieapp.models.TvShowsItem

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding : TvShowLayoutAdapterBinding):
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object  : DiffUtil.ItemCallback<TvShowsItem>(){
        override fun areItemsTheSame(oldItem: TvShowsItem, newItem: TvShowsItem): Boolean {
           return oldItem.id == newItem.id


        }

        override fun areContentsTheSame(oldItem: TvShowsItem, newItem: TvShowsItem): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var tvShows:List<TvShowsItem>
        get() = differ.currentList
        set(value){
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(TvShowLayoutAdapterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val currentTvShow = tvShows[position]

        holder.binding.apply {
            textView.text = currentTvShow.name
            imageView.load(currentTvShow.image.original){
                   crossfade(true)
                crossfade(1000)
            }
        }
    }

    override fun getItemCount() = tvShows.size
}