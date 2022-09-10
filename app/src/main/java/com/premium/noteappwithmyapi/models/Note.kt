package com.example.model.notes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "notes")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val primaryKeyId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("note")
    val note: String,
    @SerializedName("userId")
    val userId: Int
        ): Serializable