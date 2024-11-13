package com.elTohamy.flushy.domain.use_case.emojis

import com.elTohamy.flushy.domain.EmojisRepository
import javax.inject.Inject

class UpdateReact @Inject constructor(private val emojisRepository: EmojisRepository) {
    suspend operator fun invoke(
        fixtureId: String, customId: String, userId: String, react: String
    ) = emojisRepository.updateReact(fixtureId, customId, userId, react)
}