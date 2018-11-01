package com.rovioli.runmaboi.model

import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ScoreDao(private val helper: DatabaseHelper) : AppDao<String, Score> {

    override fun findAll(amount: Int) = transaction {
        val scores = mutableListOf<Score>()
        Scores.selectAll()
                .fetchSize(amount)
                .orderBy(Scores.score, isAsc = false)
                .forEach {
                    scores.add(Score(it[Scores.name], it[Scores.score]))
                }
        scores
    }

    override fun insert(data: Score) = transaction {
        Scores.insertAndGetId {
            it[name] = data.name
            it[score] = data.score
        }.value
    }

    override fun find(key: String) = transaction(helper.database) {
        Scores.select { Scores.name eq key } // TODO: return Score object from DB!
        Score("Remi", 1488)
    }
}