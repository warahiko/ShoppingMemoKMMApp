@file:Suppress("unused")

package io.github.warahiko.shoppingmemokmmapplication.data.repository

import io.github.warahiko.shoppingmemokmmapplication.data.common.asAnyFlow

class TagIosRepository(
    tagRepository: TagRepository,
) {
    val tags = tagRepository.tags.asAnyFlow()
}
