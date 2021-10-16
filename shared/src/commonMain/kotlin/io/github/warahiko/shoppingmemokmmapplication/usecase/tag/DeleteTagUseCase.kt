package io.github.warahiko.shoppingmemokmmapplication.usecase.tag

import io.github.warahiko.shoppingmemokmmapplication.data.model.Tag
import io.github.warahiko.shoppingmemokmmapplication.data.repository.TagRepository

class DeleteTagUseCase(
    private val tagRepository: TagRepository,
) {

    suspend operator fun invoke(tag: Tag) {
        tagRepository.deleteTag(tag)
    }
}
