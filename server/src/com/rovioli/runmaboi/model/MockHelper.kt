package com.rovioli.runmaboi.model

class MockHelper : RequestAndScoreDao {

    override fun findApiKey(key: String) = "1234qwerbnkl"

    override fun insertScore(data: Score) = 1

    override fun insertApiKey(key: String) = 2

    override fun readHighScores(amount: Int) = listOf(
            Score("Weitali", 300),
            Score("Luoman", 200),
            Score("Limin", 100)
    )
}