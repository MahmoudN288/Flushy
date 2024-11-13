package com.elTohamy.flushy.domain.use_case.emojis

import com.elTohamy.flushy.domain.EmojisRepository
import javax.inject.Inject

class DeleteReact @Inject constructor(private val emojisRepository: EmojisRepository) {
    suspend operator fun invoke(fixtureId: String, customId: String, userId: String) =
        emojisRepository.deleteReactFromFirestore(fixtureId, customId, userId)
}