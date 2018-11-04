package com.rovioli.storascore.model

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseHelper {

    lateinit var database: Database
        private set

    fun connect() {
        // TODO: get user and password from config, use non-root user
        database = Database.connect(
                url      = "jdbc:h2:mem:storascore;DB_CLOSE_DELAY=-1;",
                driver   = "org.h2.Driver",
                user     = "root",
                password = ""
        )
    }

    fun createTables(vararg tables: Table) = transaction(database) {
        tables.forEach { SchemaUtils.create(it) }
    }
}