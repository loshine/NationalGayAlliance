package xyz.loshine.nga.data.entity

import android.os.Parcel
import android.os.Parcelable

data class ForumBoard(
        val fid: Int,
        val name: String,
        val category: Int
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString()!!,
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(fid)
        writeString(name)
        writeInt(category)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ForumBoard> = object : Parcelable.Creator<ForumBoard> {
            override fun createFromParcel(source: Parcel): ForumBoard = ForumBoard(source)
            override fun newArray(size: Int): Array<ForumBoard?> = arrayOfNulls(size)
        }
    }
}