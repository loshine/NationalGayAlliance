package xyz.loshine.nga.data.entity

import android.os.Parcel
import android.os.Parcelable

data class ForumGroup(
        val name: String,
        val index: Int,
        val forumList: MutableList<Forum>
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString()!!,
            source.readInt(),
            source.createTypedArrayList(Forum.CREATOR)!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeInt(index)
        writeTypedList(forumList)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ForumGroup> = object : Parcelable.Creator<ForumGroup> {
            override fun createFromParcel(source: Parcel): ForumGroup = ForumGroup(source)
            override fun newArray(size: Int): Array<ForumGroup?> = arrayOfNulls(size)
        }
    }
}