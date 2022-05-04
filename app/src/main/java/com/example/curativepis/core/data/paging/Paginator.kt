package com.example.curativepis.core.data.paging

interface Paginator<Key,Item> {
   suspend fun loadNextItems()
    fun reset()
}