package com.rovioli.model

data class Record(val apiKey: String?, val score: Score)

data class Score(val name: String, val score: Int)