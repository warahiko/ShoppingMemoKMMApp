package io.github.warahiko.shoppingmemokmmapplication.data.repository

import io.github.warahiko.shoppingmemokmmapplication.data.mapper.toProperties
import io.github.warahiko.shoppingmemokmmapplication.data.mapper.toTag
import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import io.github.warahiko.shoppingmemokmmapplication.data.network.api.TagApi
import io.github.warahiko.shoppingmemokmmapplication.data.network.model.AddTagRequest
import io.github.warahiko.shoppingmemokmmapplication.error.InternalError
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

    fun getTags(): List<Tag> {
        return _tags.value ?: throw InternalError("Tag list has not been fetched yet")
    }

    suspend fun fetchTags(): List<Tag> {
        val result = withContext(Dispatchers.Default) {
            tagApi.getTags()
        }
        return result.results
            .map { it.toTag() }
            .also {
                _tags.value = it
            }
    }

    suspend fun addTag(tag: Tag) {
        val requestBody = tag.toProperties()
        val request = AddTagRequest(requestBody)
        val response = withContext(Dispatchers.Default) {
            tagApi.addTag(request)
        }
        val item = response.toTag()
        _tags.value = _tags.value.orEmpty().plus(item)
    }
}
