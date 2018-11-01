package com.rovioli.runmaboi.model

interface AppDao<Key, Type> {
    fun insert(data: Type): Int

    fun find(key: Key): Type

    fun findAll(amount: Int = 0): List<Type>
}