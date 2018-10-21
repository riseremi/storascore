package com.rovioli.runmaboi.model

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Pretty ugly helper, but enough for its purpose.
 */
class RequestAndScoreDatabaseHelper {
    fun connect() {
        Database.connect(
                url      = "jdbc:h2:mem:test",
                driver   = "org.h2.Driver",
                user     = "get_from_config",
                password = "get_from_config"
        )
    }

    fun createTables() = transaction {
        SchemaUtils.create(Clients)
        SchemaUtils.create(Scores)
    }

    fun insertScore(data: Score) = transaction {
        Scores.insert {
            it[name]  = data.name
            it[score] = data.score
        }
    }

    fun insertApiKey(key: String) = transaction {
        Clients.insert { it[apiKey] = key }
    }

    fun findApiKey(key: String) = transaction {
        Clients.select { Clients.apiKey eq key }.toString()
    }

    fun readHighScores(amount: Int) = transaction {
        Scores.slice(Scores.score)
                .selectAll()
                .fetchSize(amount)
                .orderBy(Scores.score, isAsc = false)
                .toList()
    }
}