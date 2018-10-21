package com.rovioli.runmaboi.model

import org.jetbrains.exposed.dao.IntIdTable

const val NAME_LENGTH    = 8
const val API_KEY_LENGTH = 20

object Clients : IntIdTable() {
    val apiKey = varchar("apiKey", API_KEY_LENGTH)
}

object Scores : IntIdTable() {
    val name  = varchar("name", NAME_LENGTH)
    val score = integer("score")
}