package com.rovioli

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rovioli.model.Record
import com.rovioli.model.Repository
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing

private val gson: Gson = GsonBuilder().create()

class Router(private val repository: Repository) {
    fun start(application: Application) {
        application.routing {
            post("/save") {
                // val result = call.receive<Record>()
                val jsonText = call.receiveText()
                val obj = gson.fromJson<Record>(jsonText, Record::class.java)
                println(jsonText)
                println("Result object is $obj")
                call.respond("Got this, ma boooi!")
            }
            get("/alien") {
                call.respondText(repository.getAlien())
            }
        }
    }
}