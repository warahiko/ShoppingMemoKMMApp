package io.github.warahiko.shoppingmemokmmapplication.data.repository

import io.github.warahiko.shoppingmemokmmapplication.data.mapper.toTag
import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import io.github.warahiko.shoppingmemokmmapplication.data.network.api.TagListApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TagListRepository(
    private val tagListApi: TagListApi,
) {

    suspend fun fetchTagList(): List<Tag> {
        val result = withContext(Dispatchers.Default) {
            tagListApi.getTagList()
        }
        return result.results.map {
            it.toTag()
        }
    }
}
