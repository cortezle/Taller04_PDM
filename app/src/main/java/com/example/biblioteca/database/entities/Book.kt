package com.example.biblioteca.database.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    var title : String,
    var author : String,
    var cover : String,
    var edition : Int,
    var editorial : String,
    var synopsis : String,
    @PrimaryKey var idBook : String,
    var tags : String,
    var favorite : Int
) : Parcelable {
    constructor(parcel: Parcel) : this (
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(author)
        dest.writeString(cover)
        dest.writeInt(edition)
        dest.writeString(editorial)
        dest.writeString(synopsis)
        dest.writeString(idBook)
        dest.writeString(tags)
        dest.writeInt(favorite)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}