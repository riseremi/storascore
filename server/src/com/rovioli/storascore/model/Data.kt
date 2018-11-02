package com.rovioli.storascore.model

data class Request(val apiKey: String?, val score: Score)

data class Score(val name: String, val score: Int)