package com.rovioli.storascore.model

import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.UnsupportedOperationException

class ApiDao(private val helper: DatabaseHelper) : AppDao<String, String> {

    override fun insert(data: String) = transaction(helper.database) {
        Clients.insertAndGetId { it[apiKey] = data }.value
    }

    override fun find(key: String) = transaction(helper.database) {
        val list = mutableListOf<String>()
        Clients.select {
            Clients.apiKey eq key
        }.forEach {
            list.add(it[Clients.apiKey])
        }
        list
    }

    override fun findAll(amount: Int): List<String> {
        throw UnsupportedOperationException()
    }
}