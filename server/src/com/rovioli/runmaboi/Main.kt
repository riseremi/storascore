package com.rovioli.runmaboi

import com.rovioli.runmaboi.model.Repository
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.*

private val repository = Repository()
private val router = Router(repository)

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
    router.start(this)
}
