package com.elTohamy.flushy.domain

import kotlinx.coroutines.flow.Flow

typealias emojiValue = List<EventsReaction>
typealias ReactResponse = EmojiResponse<emojiValue>
typealias LoveReactResponse = EmojiResponse<emojiValue>
typealias PartyReactResponse = EmojiResponse<emojiValue>
typealias CoolReactResponse = EmojiResponse<emojiValue>
typealias SadReactResponse = EmojiResponse<emojiValue>
typealias AngryReactResponse = EmojiResponse<emojiValue>
typealias AddReactResponse = EmojiResponse<Boolean>
typealias UpdateReactResponse = EmojiResponse<Boolean>
typealias DeleteReactResponse = EmojiResponse<Boolean>

interface EmojisRepository {
    fun getReactsFromFirestore(fixtureId: String, customId: String): Flow<ReactResponse>

    fun getLoveReacts(fixtureId: String, customId: String, react: String): Flow<LoveReactResponse>

    fun getPartyReacts(fixtureId: String, customId: String, react: String): Flow<PartyReactResponse>

    fun getCoolReacts(fixtureId: String, customId: String, react: String): Flow<CoolReactResponse>

    fun getSadReacts(fixtureId: String, customId: String, react: String): Flow<SadReactResponse>

    fun getAngryReacts(fixtureId: String, customId: String, react: String): Flow<AngryReactResponse>

    suspend fun addReactToFirestore(
        index: Int, fixtureId: String, customId: String, userId: String, react: String
    ): AddReactResponse

    suspend fun updateReact(
        fixtureId: String, customId: String, userId: String, react: String
    ): UpdateReactResponse

    suspend fun deleteReactFromFirestore(fixtureId: String, customId: String, userId: String): DeleteReactResponse
}