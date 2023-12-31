package com.duodinamika.advweek4.model

import com.google.gson.annotations.SerializedName

data class Student(
    val id:String?,
    @SerializedName("student_name")
    val name:String?,
    @SerializedName("birth_of_date")
    val bod:String?,
    val phone:String?,
    @SerializedName("photo_url")
    val photoUrl:String

)


data class Motogp(
    val id:Int?,
    val rider:String?,
    val team:String?,
    val bike:Bike?,
    val images:String?
)

data class Bike(
    val manufacturer:String?,
    val model:String?
)
