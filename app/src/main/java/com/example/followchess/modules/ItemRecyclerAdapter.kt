package com.example.followchess.modules

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.followchess.R
import com.example.followchess.models.DiffTrn
import com.example.followchess.models.Trn
import kotlinx.android.synthetic.main.recyclerview_item_layout.view.*
import kotlinx.android.synthetic.main.recyclerview_item_layout.view.imgView
import kotlinx.android.synthetic.main.recyclerview_item_layout.view.tvName
import kotlinx.android.synthetic.main.recyclerview_landscape_layout.view.*

class ItemRecyclerAdapter(private val isHorizontal: Boolean) :ListAdapter<Trn, RecyclerView.ViewHolder>(DiffTrn){

    //Setting viewType number for Portrait and Landscape mode
    val IS_HORIZONTAL:Int=1
    val IS_NOT_HORIZONTAL:Int=2

    //Getting View Type For Potrait(Horizontal) and Landscape(Vertical)
    override fun getItemViewType(position: Int): Int {
        return if (isHorizontal){
            IS_HORIZONTAL
        }else{
            IS_NOT_HORIZONTAL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //Checking for orientation
        return if (isHorizontal){
            val binding=LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_layout,parent,false)
            HorizontalViewHolder(binding)
        }else{
            val binding=LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_landscape_layout,parent,false)
            VeticalViewHolder(binding)
        }



    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            IS_HORIZONTAL->{
                initHorizontalLayout(holder as HorizontalViewHolder,position)
            }
            IS_NOT_HORIZONTAL->{
                initVerticalLayout(holder as VeticalViewHolder,position)
            }
        }
    }

    private fun initVerticalLayout(holder: VeticalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun initHorizontalLayout(holder: HorizontalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

//ViewHolder for landscape mode
class VeticalViewHolder(val binding: View) :RecyclerView.ViewHolder(binding) {
    @SuppressLint("SetTextI18n")
    fun bind(item: Trn) {
            binding.run {
                this.tvLandscapeName.text="Name : ${item.name}"
                Glide.with(this.imgLandscapeView.context).load(item.img).placeholder(R.drawable.no_image)
                    .into(this.imgLandscapeView)
            }

    }

}

//ViewHolder for PotraitMode
class HorizontalViewHolder(val binding: View) :RecyclerView.ViewHolder(binding) {
    @SuppressLint("SetTextI18n")
    fun bind(item: Trn) {
            binding.run {
                val numberOfDashes=countOccurences(item.slug,'-')
                val yearText=getYear(item.slug)
                this.tvName.text="Name : ${item.name}"
                this.tvSlug.text="Slug : ${item.slug}"
                this.tvYear.text="Year : ${yearText}"
                this.tvDashes.text="Number of Dash : ${numberOfDashes}"
                Glide.with(this.imgView.context).load(item.img).placeholder(R.drawable.no_image)
                    .into(this.imgView)
            }
    }
    //Function for getting year from slug
    private fun getYear(slug: String): String {
        return slug.substring(slug.length-4,slug.length)
    }
    //Function for getting occurence of '-' in Slug
    private fun countOccurences(slug: String, c: Char): Int {
            return slug.filter { it==c }.count()
    }

}
