package com.example.task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.OrientationHelper
import com.example.task2.adapter.PostsAdapter
import com.example.task2.databinding.ActivityFeedBinding
import com.example.task2.models.Post
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog


class FeedActivity : AppCompatActivity() {
    lateinit var binding: ActivityFeedBinding
    lateinit var postsList: ArrayList<Post>
    private lateinit var postsAdapter: PostsAdapter
    private val postImageList = listOf(R.raw.feed1, R.raw.feed2, R.raw.feed3)
    private val profileImageList = listOf(R.raw.user1, R.raw.user2, R.raw.user3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postsList = ArrayList()
        postsList.add(Post("muhammad", postImageList.random(), profileImageList.random()))
        postsList.add(Post("seif", postImageList.random(), profileImageList.random()))
        postsList.add(Post("abdullah", postImageList.random(), profileImageList.random()))
        postsList.add(Post("ahmad", postImageList.random(), profileImageList.random()))
        setUpRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // handle button activities
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.itemId
        if (id == R.id.action_add) {
            val dialog = BottomSheetDialog(this)
            // on below line we are inflating a layout file which we have created.
            val view = layoutInflater.inflate(R.layout.bottom_sheet, null)
            val btnClose = view.findViewById<Button>(R.id.bottomSheetCancelButton)
            btnClose.setOnClickListener {
                dialog.dismiss()
            }
            val btnAdd = view.findViewById<Button>(R.id.bottomSheetAddButton)
            val nameEditText = view.findViewById<EditText?>(R.id.bottomSheetNameEditText)
            btnAdd.setOnClickListener {
                if (nameEditText.text.toString().isEmpty()) {
                    Toast.makeText(this, "you didn't add anything", Toast.LENGTH_SHORT).show()
                } else {
                    postsList.add(Post(nameEditText?.text.toString(), postImageList.random(), profileImageList.random()))
                    dialog.dismiss()
                    setUpRecyclerView()
                }
            }
            // below line is use to set cancelable to avoid
            // closing of dialog box when clicking on the screen.
            dialog.setCancelable(true)
            dialog.setContentView(view)
            dialog.show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpRecyclerView() {
        postsAdapter = PostsAdapter(this, postsList)
        binding.rvPosts.addItemDecoration(
            DividerItemDecoration(
                this,
                OrientationHelper.VERTICAL
            )
        ) // to make divider
        binding.rvPosts.adapter = postsAdapter
    }

    fun deleteContact(post: Post, position: Int) {
        postsList.remove(post)
//        setUpRecyclerView()
        postsAdapter.notifyItemRemoved(position)

    }


}
