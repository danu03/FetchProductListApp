package com.danusuhendra.fetchproductlistapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danusuhendra.fetchproductlistapp.databinding.ItemProductBinding
import com.danusuhendra.fetchproductlistapp.model.Product

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val list = ArrayList<Product?>()
    private var itemCallback: OnItemClickCallback? = null

    fun setClickCallback(ItemClickCallback: OnItemClickCallback) {
        this.itemCallback = ItemClickCallback
    }

    fun setList(product: List<Product?>) {
        list.clear()
        list.addAll(product)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.root.setOnClickListener {
                itemCallback?.onItemClicked(product)
            }
            binding.apply {
                Glide.with(itemView)
                    .load(product.image)
                    .centerCrop()
                    .into(ivProduct)
                tvTitle.text = product.title
                tvDesc.text = product.description
                tvPrice.text = "$ " + product.price.toString()
                tvRating.text = "★ " + product.rating?.rate
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder((view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position]?.let { holder.bind(it) }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Product)
    }
}