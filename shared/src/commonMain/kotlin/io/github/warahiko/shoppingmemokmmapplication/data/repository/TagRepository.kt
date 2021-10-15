package io.github.warahiko.shoppingmemokmmapplication.data.repository

import io.github.warahiko.shoppingmemokmmapplication.data.mapper.toTag
import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import io.github.warahiko.shoppingmemokmmapplication.data.network.api.TagApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class TagRepository(
    private val tagApi: TagApi,
) {

    private val _tags: MutableStateFlow<List<Tag>?> = MutableStateFlow(null)
    val tags: StateFlow<List<Tag>?> get() = _tags

    suspend fun getOrFetchTags(): List<Tag> {
        return _tags.value ?: fetchTags()
    }

    private suspend fun fetchTags(): List<Tag> {
        val result = withContext(Dispatchers.Default) {
            tagApi.getTags()
        }
        return result.results.map {
            it.toTag()
        }
    }
}