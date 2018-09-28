package xyz.loshine.nga.data.entity

import android.os.Parcel
import android.os.Parcelable

data class ForumBoardCategory(
        val name: String,
        val index: Int,
        val forumBoardList: MutableList<ForumBoard>
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString()!!,
            source.readInt(),
            source.createTypedArrayList(ForumBoard.CREATOR)!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeInt(index)
        writeTypedList(forumBoardList)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ForumBoardCategory> = object : Parcelable.Creator<ForumBoardCategory> {
            override fun createFromParcel(source: Parcel): ForumBoardCategory = ForumBoardCategory(source)
            override fun newArray(size: Int): Array<ForumBoardCategory?> = arrayOfNulls(size)
        }
    }
}