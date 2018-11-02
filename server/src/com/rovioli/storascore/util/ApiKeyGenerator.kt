package com.rovioli.storascore.util

import java.util.*
import kotlin.streams.asSequence

const val API_KEY_LENGTH = 20

private const val NUM    = "1234567890"
private const val SOURCE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz$NUM"

fun generate() = Random()
        .ints(API_KEY_LENGTH.toLong(), 0, SOURCE.length)
        .asSequence()
        .map(SOURCE::get)
        .joinToString("")