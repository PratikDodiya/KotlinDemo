package com.app.kotlindemo.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.kotlindemo.R
import com.app.kotlindemo.interfaces.RecyclerItemClickListener
import com.app.kotlindemo.model.Results
import com.app.kotlindemo.utils.loadUrl
import kotlinx.android.synthetic.main.adapter_user.view.*

/**
 * Created by Pratik on 7/5/19.
 */
class RandomUserListAdapter(
    private val arrayList: ArrayList<Results>,
    private val recyclerItemClickListener: RecyclerItemClickListener
) :
    RecyclerView.Adapter<RandomUserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position], recyclerItemClickListener)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindItems(
            results: Results,
            recyclerItemClickListener: RecyclerItemClickListener
        ) {
            itemView.textViewUsername.text = "${results.name.first} ${results.name.last}"
            itemView.textViewEmailAddress.text = results.email
            itemView.imageViewPic.loadUrl(results.picture.large, R.drawable.ic_launcher_foreground)
            itemView.setOnClickListener {
                recyclerItemClickListener.onItemClickEvent(itemView, adapterPosition, 1)
            }
        }
    }
}