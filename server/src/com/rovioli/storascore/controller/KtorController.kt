package com.rovioli.storascore.controller

import io.ktor.application.Application
import io.ktor.routing.Routing

interface KtorController {
    fun attach(application: Application) : Routing
}