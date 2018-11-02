package com.rovioli.storascore

import com.google.gson.GsonBuilder
import com.rovioli.storascore.controller.ApiController
import com.rovioli.storascore.controller.ScoresController
import com.rovioli.storascore.model.*
import com.rovioli.storascore.util.Delayer
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.*

private val helper = DatabaseHelper()
private val scoresController = ScoresController(ScoreDao(helper), GsonBuilder().create())
private val apiController = ApiController(ApiDao(helper), Delayer(2)) // TODO take delay time from configuration file

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
    helper.createTables(Clients, Scores)
    apiController.attach(this)
    scoresController.attach(this)
}
