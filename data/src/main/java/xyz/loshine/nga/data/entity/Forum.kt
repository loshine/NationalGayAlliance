package xyz.loshine.nga.data.entity

import android.os.Parcel
import android.os.Parcelable

data class Forum(
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
        val CREATOR: Parcelable.Creator<Forum> = object : Parcelable.Creator<Forum> {
            override fun createFromParcel(source: Parcel): Forum = Forum(source)
            override fun newArray(size: Int): Array<Forum?> = arrayOfNulls(size)
        }
    }
}