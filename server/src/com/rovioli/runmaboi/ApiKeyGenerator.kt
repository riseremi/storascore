package com.rovioli.runmaboi

import java.util.*
import kotlin.streams.asSequence

const val API_KEY_LENGTH = 20L

private val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
private val num    = "1234567890"

fun generate() = Random()
        .ints(API_KEY_LENGTH, 0, source.length)
        .asSequence()
        .map("$source + ${source.toLowerCase()} + $num"::get)
        .joinToString("")