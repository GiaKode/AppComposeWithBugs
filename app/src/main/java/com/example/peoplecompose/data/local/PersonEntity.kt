package com.example.peoplecompose.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.peoplecompose.data.model.Id
import com.example.peoplecompose.data.model.Name
import com.example.peoplecompose.data.model.Person
import com.example.peoplecompose.data.model.PicturePerson

@Entity(tableName = "persons")
data class PersonEntity(

    @PrimaryKey
    val id:String,

    val firstName: String,
    val lastName: String,
    val email: String,
    val cell: String,
    val thumbnail: String
){
    fun toPerson(): Person {
        return Person(
            id = this.id,
            name = Name(first = this.firstName, last = this.lastName),
            email = this.email,
            cell = this.cell,
            picture = PicturePerson(thumbnail = this.thumbnail),
            isFavorite = false
        )
    }
}
