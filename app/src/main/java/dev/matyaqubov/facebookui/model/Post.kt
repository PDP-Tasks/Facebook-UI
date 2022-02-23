package dev.matyaqubov.facebookui.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import androidx.core.net.toUri
import java.io.Serializable

class Post :Parcelable{

    var profile:Int=0
    var fullname:String=""
    var photos:ArrayList<Int> = ArrayList()
    var photo:Uri="".toUri()
    var title:String=""
    var post:String=""
    var website:String=""

    constructor(parcel: Parcel) {
        profile = parcel.readInt()
        fullname = parcel.readString()!!
        photo = parcel.readParcelable(Uri::class.java.classLoader)!!
        title = parcel.readString()!!
        post = parcel.readString()!!
        website = parcel.readString()!!
    }

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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(profile)
        parcel.writeString(fullname)
        parcel.writeParcelable(photo, flags)
        parcel.writeString(title)
        parcel.writeString(post)
        parcel.writeString(website)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }

}