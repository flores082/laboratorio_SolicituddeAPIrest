package com.example.flores.laboratorio_solicituddeapirest.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "user_id") val userid : Int,
    @ColumnInfo(name = "name") val name: String?,
)