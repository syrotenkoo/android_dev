package com.example.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.customview.databinding.PostViewBinding

class PostView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val binding: PostViewBinding

    init {
        val inflatedView = inflate(context, R.layout.post_view, this)
        binding = PostViewBinding.bind(inflatedView)
    }

    fun setUpperString(message: String) {
        binding.upperString.text = message
    }

    fun setLowerString(message: String) {
        binding.lowerString.text = message
    }
}