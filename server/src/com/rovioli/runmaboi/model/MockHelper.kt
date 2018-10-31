package com.rovioli.runmaboi.model

class MockHelper {

     fun findApiKey(key: String) = "1234qwerbnkl"

     fun insertScore(data: Score) = 1

     fun insertApiKey(key: String) = 2

     fun readHighScores(amount: Int) = listOf(
            Score("Weitali", 300),
            Score("Luoman", 200),
            Score("Limin", 100)
    )
}