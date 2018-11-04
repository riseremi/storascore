package com.rovioli.storascore.model

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

// TODO: find a way to return lists without creating a mutableList
class ScoreDao(private val helper: DatabaseHelper) : AppDao<String, Data> {

    override fun findAll(amount: Int) = transaction(helper.database) {
        val scores = mutableListOf<Data>()
        Scores.selectAll()
                .fetchSize(amount)
                .orderBy(Scores.score, isAsc = false)
                .forEach {
                    scores.add(Data(it[Scores.name], it[Scores.score]))
                }
        scores
    }

    override fun insert(data: Data) = transaction(helper.database) {
        Score.new {
            name  = data.name
            score = data.score
        }
    }

    override fun find(key: String) = transaction(helper.database) {
        val scores = mutableListOf<Data>()
        Score.find {
            Scores.name eq key
        }.forEach {
            scores.add(Data(it.name, it.score))
        }
        scores
    }
}