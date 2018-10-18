package com.rovioli

data class Record(val client: Client, val score: Score)

data class Score(val name: String, val score: Int)

data class Client(val apiKey: String)
