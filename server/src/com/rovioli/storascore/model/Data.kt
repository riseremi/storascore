package com.rovioli.storascore.model

data class Request(val apiKey: String?, val score: Data)

data class Data(val name: String, val score: Int)