package com.example.task2.adapter

import com.example.task2.R


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.task2.FeedActivity
import com.example.task2.models.Post
import com.mikhaellopez.circularimageview.CircularImageView

class PostsAdapter(var context: FeedActivity, var postsList: ArrayList<Post>) :

    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val postName: TextView = itemView.findViewById(R.id.nameText)
        private val postImage: ImageView = itemView.findViewById(R.id.postImage)
        private val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)


        private val postProfilePicture: CircularImageView =
            itemView.findViewById(R.id.profilePicture)


        fun bind(post: Post, position: Int) {
            postName.text = post.name
            postImage.setImageResource(post.postImage)
            postProfilePicture.setImageResource(post.profileImage)
            itemView.setOnClickListener {
                Toast.makeText(
                    itemView.context,
                    "Poster Name = ${post.name}",
                    Toast.LENGTH_SHORT
                ).show()
                //                Toast.makeText(itemView.context, "Contact Name = ${contactList[position].name}", Toast.LENGTH_SHORT).show()

            }

            deleteButton.setOnClickListener {
                context.deleteContact(post, position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post: Post = postsList[position]
//        Post(
//            postsList[position].name,
//            postsList[position].postImage,
//            postsList[position].profileImage
//        )
        holder.bind(post, position)
    }

    override fun getItemCount(): Int {
        return postsList.size
    }
}