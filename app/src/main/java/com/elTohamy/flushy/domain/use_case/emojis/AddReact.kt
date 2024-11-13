package com.elTohamy.flushy.domain.use_case.emojis

import com.elTohamy.flushy.domain.EmojisRepository
import javax.inject.Inject

class AddReact @Inject constructor(private val emojisRepository: EmojisRepository) {
    suspend operator fun invoke(
        index: Int, fixtureId: String, customId: String, userId: String, react: String
    ) = emojisRepository.addReactToFirestore(index, fixtureId, customId, userId, react)
}