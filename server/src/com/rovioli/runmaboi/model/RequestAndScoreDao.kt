package com.rovioli.runmaboi.model

interface RequestAndScoreDao {
    fun insertScore(data: Score): Int

    fun insertApiKey(key: String): Int

    fun findApiKey(key: String): String

    fun readHighScores(amount: Int): List<Score>
}