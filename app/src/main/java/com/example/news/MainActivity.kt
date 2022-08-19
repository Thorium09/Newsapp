package com.example.news

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class MainActivity : AppCompatActivity(), Eclick {
    private lateinit var adapter : NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rc = findViewById<RecyclerView>(R.id.news_list)

        rc.layoutManager = LinearLayoutManager(this)
        fetchdata()
        adapter = NewsAdapter(this)
        rc.adapter = adapter

    }
    fun fetchdata() {
        val url : String = "https://newsapi.org/v2/top-headlines?country=in&apiKey=715b3b846f81448e954ce8fbc0ac6a62"
        val jReq = object :JsonObjectRequest(com.android.volley.Request.Method.GET,url,null, {

            val ja = it.getJSONArray("articles")
            val ls = ArrayList<News>()

            for (i in 0 until ja.length()){
                val jao = ja.getJSONObject(i)

                val E = News(
                    jao.getString("urlToImage"),
                    jao.getString("title"),
                    jao.getString("url")

                )
                ls.add(E)
            }
            adapter.updateNews(ls)

        }, {

        }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        MySingleton.getInstance(this).addToRequestQueue(jReq)

    }
    override fun onclick(item: News) {
        super.onclick(item)
        val url = item.getUrl()
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))


    }
}
