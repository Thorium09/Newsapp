package com.example.news

class News(img: String, news: String,url : String) {
    private val Img : String = img
    private val News : String = news
    private val url : String = url


    fun getNews() : String{
        return News
    }
    fun getImage() : String{
        return  Img
    }

    fun getUrl() : String{
        return url
    }

}