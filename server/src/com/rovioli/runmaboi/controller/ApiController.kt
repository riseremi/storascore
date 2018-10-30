package com.rovioli.runmaboi.controller

import com.rovioli.runmaboi.model.Repository
import com.rovioli.runmaboi.util.Delayer
import com.rovioli.runmaboi.util.generate
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing

class ApiController(
        private val repository: Repository,
        private val delayer: Delayer
) : AppController {

    override fun attach(application: Application) = application.routing {
        get("/register") {
            register(call)
        }

        get("/check") {
            val key = call.parameters["key"]
            if (key == null || key.isEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "No key provided")
            } else {
                call.respond(HttpStatusCode.OK, "$key is ${if (repository.isRegistered(key)) "not " else ""}registered")
            }
        }
    }

    private suspend fun register(call: ApplicationCall) {
        if (delayer.isDelayed()) {
            call.respond(HttpStatusCode.Forbidden, "Try to register later")
        } else {
            val key = generate()
            delayer.delay()
            call.respond(HttpStatusCode.OK, key)
            repository.register(key)
        }
    }
}