package dev.matyaqubov.facebookui.model

import android.net.Uri
import androidx.core.net.toUri
import java.io.Serializable

class Post:Serializable{

    var profile:Int=0
    var fullname:String=""
    var photos:ArrayList<Int> = ArrayList()
    var photo:Uri="".toUri()
    var title:String=""
    var post:String=""
    var website:String=""

    constructor(profile:Int,fullname:String,photos:ArrayList<Int>){
        this.fullname=fullname
        this.profile=profile
        this.photos=photos
    }

    constructor(profile:Int,fullname:String,photo:String,title:String,post:String,website:String){
        this.fullname=fullname
        this.profile=profile
        this.photo=photo.toUri()
        this.post=post
        this.title=title
        this.website=website
    }

}