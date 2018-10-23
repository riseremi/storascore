package com.rovioli.runmaboi

import java.util.*
import kotlin.streams.asSequence

const val API_KEY_LENGTH = 20

private val num    = "1234567890"
private val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz$num"

fun generate() = Random()
        .ints(API_KEY_LENGTH.toLong(), 0, source.length)
        .asSequence()
        .map(source::get)
        .joinToString("")