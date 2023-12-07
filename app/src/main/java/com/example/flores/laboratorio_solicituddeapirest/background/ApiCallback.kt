package com.example.flores.laboratorio_solicituddeapirest.background

interface ApiCallback {
    fun onRequestComplete(result: String)
}