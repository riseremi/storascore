package com.rovioli.runmaboi.model

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Pretty ugly helper, but enough for its purpose.
 */
class RequestAndScoreDatabaseHelper : RequestAndScoreDao {
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

    override fun insertScore(data: Score) = transaction {
        Scores.insertAndGetId {
            it[name] = data.name
            it[score] = data.score
        }.value
    }

    override fun insertApiKey(key: String) = transaction {
        Clients.insertAndGetId { it[apiKey] = key }.value
    }

    override fun findApiKey(key: String) = transaction {
        Clients.select { Clients.apiKey eq key }.toString()
    }

    override fun readHighScores(amount: Int) = transaction {
        Scores.slice(Scores.score)
                .selectAll()
                .fetchSize(amount)
                .orderBy(Scores.score, isAsc = false)
                .toList()
    }
}