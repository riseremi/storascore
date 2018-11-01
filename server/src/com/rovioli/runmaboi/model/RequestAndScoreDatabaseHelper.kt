package com.rovioli.runmaboi.model

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Pretty ugly helper, but enough for its purpose.
 */
class RequestAndScoreDatabaseHelper {

    lateinit var database: Database
        private set

    fun connect() {
        database = Database.connect(
                url      = "jdbc:h2:mem:test",
                driver   = "org.h2.Driver",
                user     = "get_from_config",
                password = "get_from_config"
        )
    }

    fun createTables() = transaction(database) {
        SchemaUtils.create(Clients)
        SchemaUtils.create(Scores)
    }
}