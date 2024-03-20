package com.example.mapfortravelers

import android.R.color.white
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class PMarkActivity: AppCompatActivity() {

    private lateinit var image: ImageView
    private lateinit var like: Button
    private lateinit var author: TextView
    private lateinit var description: TextView
    private lateinit var name: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pmark)

        image = findViewById(R.id.imageView2)
        like = findViewById(R.id.like)
        author = findViewById(R.id.txvAuthor)
        description = findViewById(R.id.txvDescription)
        name = findViewById(R.id.txvPmark)

        var cnt_liked = 0
        like.setOnClickListener{
            cnt_liked++
            if(cnt_liked%2!=0) {
                like.background = ContextCompat.getDrawable(
                    this@PMarkActivity,
                    R.drawable.baseline_favorite_red_24
                )
            }
            else {
                like.background = ContextCompat.getDrawable(
                    this@PMarkActivity,
                    R.drawable.baseline_favorite_shadow_24
                )
            }

        }
        author.setOnClickListener {
            val dialogAuthorFragment = DialogAuthorFragment()
            val manager = supportFragmentManager
            dialogAuthorFragment.show(manager, "authorDialog")
        }

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Метка"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}


