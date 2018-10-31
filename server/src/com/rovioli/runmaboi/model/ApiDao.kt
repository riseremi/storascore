package com.rovioli.runmaboi.model

import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.UnsupportedOperationException

class ApiDao(private val helper: RequestAndScoreDatabaseHelper) : AppDao<String, String> {

    override fun insert(data: String) = transaction {
        Clients.insertAndGetId { it[apiKey] = data }.value
    }

    override fun find(key: String) = transaction {
        Clients.select { Clients.apiKey eq key }.toString()
    }

    override fun findAll(amount: Int): List<String> {
        throw UnsupportedOperationException()
    }
}