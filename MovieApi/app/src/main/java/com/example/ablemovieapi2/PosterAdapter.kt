package com.example.ablemovieapi2

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ablemovieapi2.data.Image
import com.example.ablemovieapi2.data.Images

class PosterAdapter(
    private val imagesList: MutableList<Image>
) : RecyclerView.Adapter<PosterAdapter.ViewHolder>() {
//    private val imagesList = mutableListOf<Image>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_image, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = imagesList[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(image: Image) {
            val imageView = itemView.findViewById<ImageView>(R.id.imageView)
            Glide
                .with(itemView.context)
                .load(Uri.parse("https://image.tmdb.org/t/p/original/${image.filePath}"))
                .into(imageView)
        }
    }

    fun setData(images: Images) {
        imagesList.clear()
        imagesList.addAll(images.backdrops)
        notifyDataSetChanged()
    }
}