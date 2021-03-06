package com.rovioli.storascore.model

import com.rovioli.storascore.util.API_KEY_LENGTH
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

const val NAME_LENGTH = 50

object Clients : IntIdTable() {
    val apiKey = varchar("apiKey", API_KEY_LENGTH)
}

object Scores : IntIdTable() {
    val name  = varchar("name", NAME_LENGTH)
    val score = integer("score")
}

class Score(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Score>(Scores)

    var name  by Scores.name
    var score by Scores.score
}