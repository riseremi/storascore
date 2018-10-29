package com.rovioli.runmaboi

import com.google.gson.GsonBuilder
import com.rovioli.runmaboi.controller.ApiController
import com.rovioli.runmaboi.controller.ScoresController
import com.rovioli.runmaboi.model.Repository
import com.rovioli.runmaboi.model.RequestAndScoreDatabaseHelper
import com.rovioli.runmaboi.util.Delayer
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.*

private val helper = RequestAndScoreDatabaseHelper()
private val repository = Repository(helper)
private val scoresController = ScoresController(repository, GsonBuilder().create())
private val apiController = ApiController(repository, Delayer(2))

fun Application.main() {
    // This feature sets a Date and Server headers automatically.
    install(DefaultHeaders)
    // This feature enables compression automatically when accepted by the client.
    install(Compression)
    // Logs all the requests performed
    install(CallLogging)
    // Automatic '304 Not Modified' Responses
    install(ConditionalHeaders)
    // For each GET header, adds an automatic HEAD handler (checks the headers of the requests
    // without actually getting the payload to be more efficient about resources)
    install(AutoHeadResponse)
    helper.connect()
    helper.createTables()
    apiController.attach(this)
    scoresController.attach(this)
}
