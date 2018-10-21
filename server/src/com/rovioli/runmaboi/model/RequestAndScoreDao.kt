package com.rovioli.runmaboi.model

import org.jetbrains.exposed.sql.ResultRow

interface RequestAndScoreDao {
    fun insertScore(data: Score): Int

    fun insertApiKey(key: String): Int

    fun findApiKey(key: String): String

    fun readHighScores(amount: Int): List<ResultRow>
}
