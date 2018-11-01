package com.rovioli.runmaboi.model

import com.rovioli.runmaboi.util.API_KEY_LENGTH
import org.jetbrains.exposed.dao.IntIdTable

const val NAME_LENGTH = 50

object Clients : IntIdTable() {
    val apiKey = varchar("apiKey", API_KEY_LENGTH)
}

object Scores : IntIdTable() {
    val name  = varchar("name", NAME_LENGTH)
    val score = integer("score")
}