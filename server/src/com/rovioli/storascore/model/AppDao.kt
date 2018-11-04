package com.rovioli.storascore.model

interface AppDao<Key, Type> {
    fun insert(data: Type): Any

    fun find(key: Key): List<Type>

    fun findAll(amount: Int = 0): List<Type>
}