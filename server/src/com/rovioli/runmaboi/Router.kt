package com.rovioli.runmaboi

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rovioli.runmaboi.model.Request
import com.rovioli.runmaboi.model.Repository
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
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

private val gson: Gson = GsonBuilder().create()

class Router(private val repository: Repository) {
    fun start(application: Application) = application.routing {
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

        get("/registerme") {
            register(call)
        }
    }

    private fun validate(request: Request): HttpStatusCode = with(request) {
        if (apiKey == null || score.name.isEmpty()) return UnprocessableEntity
        if (apiKey.isEmpty()) return Unauthorized
        // if (!keyRegistered(apiKey)) return Forbidden
        return Accepted
    }

    private suspend fun register(call: ApplicationCall) {
        if (canGenerateNewKey()) {
            call.respond(HttpStatusCode.OK, "St0bstring4)ogjabod2")
        } else {
            call.respond(HttpStatusCode.RequestTimeout, "Try to register tomorrow")
        }
    }

    private fun canGenerateNewKey() = true
}