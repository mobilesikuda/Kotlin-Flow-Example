package com.mindorks.kotlinFlow.learn.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindorks.kotlinFlow.R
import com.mindorks.kotlinFlow.data.model.ApiUser
//import kotlinx.android.synthetic.main.item_layout.view.*

class ApiUserAdapter(
    private val users: ArrayList<ApiUser>
) : RecyclerView.Adapter<ApiUserAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: ApiUser) {

            val UserName: TextView = itemView.findViewById(R.id.textViewUserName)
            val UserEmail: TextView = itemView.findViewById(R.id.textViewUserEmail)
            val ViewAvatar: ImageView = itemView.findViewById(R.id.imageViewAvatar)

            UserName.text = user.name
            UserEmail.text = user.email
            Glide.with(ViewAvatar.context)
                .load(user.avatar)
                .into(ViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<ApiUser>) {
        users.addAll(list)
    }

}