package com.rovioli.runmaboi.controller

import io.ktor.application.Application
import io.ktor.routing.Routing

interface AppController {
    fun attach(application: Application) : Routing
}