package io.github.warahiko.shoppingmemokmmapplication.data.repository

import io.github.warahiko.shoppingmemokmmapplication.data.mapper.toTag
import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import io.github.warahiko.shoppingmemokmmapplication.data.network.api.TagListApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class TagListRepository(
    private val tagListApi: TagListApi,
) {

    private val _tagList: MutableStateFlow<List<Tag>?> = MutableStateFlow(null)
    val tagList: StateFlow<List<Tag>?> get() = _tagList

    suspend fun getOrFetchTagList(): List<Tag> {
        return _tagList.value ?: fetchTagList()
    }

    private suspend fun fetchTagList(): List<Tag> {
        val result = withContext(Dispatchers.Default) {
            tagListApi.getTagList()
        }
        return result.results.map {
            it.toTag()
        }
    }
}
