package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val listner: Eclick) : RecyclerView.Adapter<NewsVh>() {
    private val list: ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsVh {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent, false)
        val vh = NewsVh(view)
        view.setOnClickListener {
            listner.onclick(list[vh.adapterPosition])
        }
        return vh
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewsVh, position: Int) {
        val current = list[position]
        holder.NewS.text = current.getNews()
        Glide.with(holder.itemView.context).load(current.getImage()).into(holder.image)


    }

    fun updateNews(ls: ArrayList<News>) {
        list.clear()
        list.addAll(ls)
        notifyDataSetChanged()
    }


}


class NewsVh(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    val image: ImageView = ItemView.findViewById(R.id._img)
    val NewS: TextView = ItemView.findViewById(R.id._text)

}

interface Eclick {
    fun onclick(item: News) {

    }
}