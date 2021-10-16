package io.github.warahiko.shoppingmemokmmapplication.usecase.tag

import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import io.github.warahiko.shoppingmemokmmapplication.data.repository.TagRepository

class AddTagUseCase(
    private val tagRepository: TagRepository,
) {

    suspend operator fun invoke(tag: Tag) {
        tagRepository.addTag(tag)
    }
}
