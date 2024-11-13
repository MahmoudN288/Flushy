package com.elTohamy.flushy.domain.use_case.emojis

import com.elTohamy.flushy.domain.EmojisRepository
import javax.inject.Inject

class GetSadReact @Inject constructor(private val emojisRepository: EmojisRepository) {
    operator fun invoke(fixtureId: String, customId: String, react: String) =
        emojisRepository.getSadReacts(fixtureId, customId, react)
}