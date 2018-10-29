package com.rovioli.runmaboi.controller

import com.google.gson.Gson
import com.rovioli.runmaboi.model.Repository
import com.rovioli.runmaboi.model.Request
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.Accepted
import io.ktor.http.HttpStatusCode.Companion.Unauthorized
import io.ktor.http.HttpStatusCode.Companion.UnprocessableEntity
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing

class ScoresController(
        private val repository: Repository,
        private val gson: Gson
) : AppController {

    override fun attach(application: Application) = application.routing {
        post("/save") {
            // TODO: can we use call.receive<Request>() instead?
            val record = gson.fromJson<Request>(call.receiveText(), Request::class.java)
            println("recorod is $record")
            val code = validate(record)
            if (code == Accepted) {
                repository.putScore(record.score)
                call.respond(code, "Adding!\n")
            } else {
                call.respond(code, "Error!\n")
            }
        }

        get("/alien") {
            call.respondText(repository.getAlien())
        }

        get("/highscores") {
            call.respondText(gson.toJson(repository.lastScores()))
        }

    }

    private fun validate(request: Request): HttpStatusCode = with(request) {
        if (apiKey == null || score.name.isEmpty()) return UnprocessableEntity
        if (apiKey.isEmpty()) return Unauthorized
        // if (!keyRegistered(apiKey)) return Forbidden
        return Accepted
    }
}