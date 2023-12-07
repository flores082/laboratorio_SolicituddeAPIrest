package com.example.flores.laboratorio_solicituddeapirest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flores.laboratorio_solicituddeapirest.dao.ProductDao
import com.example.flores.laboratorio_solicituddeapirest.dao.UserDao
import com.example.flores.laboratorio_solicituddeapirest.entity.User
import com.example.flores.laboratorio_solicituddeapirest.entity.Product


@Database(entities = [User::class, Product::class], version = 4, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
}