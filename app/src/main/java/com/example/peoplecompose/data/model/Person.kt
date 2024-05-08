package com.example.peoplecompose.data.model

import android.graphics.Picture
import com.example.peoplecompose.data.local.PersonEntity
import com.google.gson.annotations.SerializedName
import java.util.jar.Attributes

data class PersonWrapper(

    @SerializedName("results")
    val results: List<Person>

)

data class Person(
    val id: String,

    @SerializedName("name")
    val name: Name,

    @SerializedName("email")
    val email: String,

    @SerializedName("cell")
    val cell: String,

    @SerializedName("picture")
    val picture: PicturePerson,

    var isFavorite: Boolean = false
){
    fun toPersonEntity(): PersonEntity {
        return PersonEntity(
            id = this.id,
            firstName = this.name.first,
            lastName = this.name.last,
            email = this.email,
            cell = this.cell,
            thumbnail = this.picture.thumbnail
        )
    }
}

data class Name(
    @SerializedName("first")
    val first: String,

    @SerializedName("last")
    val last: String
)

data class PicturePerson(
    @SerializedName("thumbnail")
    val thumbnail: String
)

data class Id(
    val value: String,
    val type: String
)
