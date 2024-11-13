package com.elTohamy.flushy.data.repos.emojis

import com.elTohamy.flushy.domain.EmojiResponse
import com.elTohamy.flushy.domain.EmojisRepository
import com.elTohamy.flushy.domain.EventsReaction
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class EmojisRepositoryImplementation @Inject constructor(private val reactRef: CollectionReference): EmojisRepository {
    override fun getReactsFromFirestore(
        fixtureId: String, customId: String
    ) = callbackFlow {
        val snapshotListener =
            reactRef.document(fixtureId).collection(customId).orderBy("react").addSnapshotListener { snapshot, e ->
                val reactResponse = if (snapshot != null) {
                    val react = snapshot.toObjects(EventsReaction::class.java)
                    EmojiResponse.ReactSuccess(react)
                } else {
                    EmojiResponse.ReactFailure(e)
                }
                trySend(reactResponse)
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getLoveReacts(fixtureId: String, customId: String, react: String) = callbackFlow {
        val snapshotListener =
            reactRef.document(fixtureId).collection(customId).whereEqualTo("react", "love")
                .addSnapshotListener { snapshot, e ->
                    val loveResponse = if (snapshot != null) {
                        val loveReacts = snapshot.toObjects(EventsReaction::class.java)
                        EmojiResponse.ReactSuccess(loveReacts)
                    } else {
                        EmojiResponse.ReactFailure(e)
                    }
                    trySend(loveResponse)
                }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getPartyReacts(fixtureId: String, customId: String, react: String) = callbackFlow {
        val snapshotListener =
            reactRef.document(fixtureId).collection(customId).whereEqualTo("react", "party")
                .addSnapshotListener { snapshot, e ->
                    val partyResponse = if (snapshot != null) {
                        val partyReacts = snapshot.toObjects(EventsReaction::class.java)
                        EmojiResponse.ReactSuccess(partyReacts)
                    } else {
                        EmojiResponse.ReactFailure(e)
                    }
                    trySend(partyResponse)
                }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getCoolReacts(fixtureId: String, customId: String, react: String) = callbackFlow {
        val snapshotListener =
            reactRef.document(fixtureId).collection(customId).whereEqualTo("react", "cool")
                .addSnapshotListener { snapshot, e ->
                    val coolResponse = if (snapshot != null) {
                        val coolReacts = snapshot.toObjects(EventsReaction::class.java)
                        EmojiResponse.ReactSuccess(coolReacts)
                    } else {
                        EmojiResponse.ReactFailure(e)
                    }
                    trySend(coolResponse)
                }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getSadReacts(fixtureId: String, customId: String, react: String) = callbackFlow {
        val snapshotListener =
            reactRef.document(fixtureId).collection(customId).whereEqualTo("react", "sad")
                .addSnapshotListener { snapshot, e ->
                    val sadResponse = if (snapshot != null) {
                        val sadReacts = snapshot.toObjects(EventsReaction::class.java)
                        EmojiResponse.ReactSuccess(sadReacts)
                    } else {
                        EmojiResponse.ReactFailure(e)
                    }
                    trySend(sadResponse)
                }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getAngryReacts(fixtureId: String, customId: String, react: String) = callbackFlow {
        val snapshotListener =
            reactRef.document(fixtureId).collection(customId).whereEqualTo("react", "angry")
                .addSnapshotListener { snapshot, e ->
                    val angryResponse = if (snapshot != null) {
                        val angryReacts = snapshot.toObjects(EventsReaction::class.java)
                        EmojiResponse.ReactSuccess(angryReacts)
                    } else {
                        EmojiResponse.ReactFailure(e)
                    }
                    trySend(angryResponse)
                }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addReactToFirestore(
        index: Int, fixtureId: String, customId: String, userId: String, react: String
    ) =
        try {
            val eventReaction = EventsReaction(
                index, customId, userId, react
            )
            reactRef.document(fixtureId).collection(customId).document(userId).set(eventReaction).await()
            EmojiResponse.ReactSuccess(true)
        } catch (e: Exception) {
            EmojiResponse.ReactFailure(e)
        }

    override suspend fun updateReact(
        fixtureId: String, customId: String, userId: String, react: String
    ) =
        try {
            reactRef.document(fixtureId).collection(customId).document(userId).update("react", react).await()
            EmojiResponse.ReactSuccess(true)
        } catch (e: Exception) {
            EmojiResponse.ReactFailure(e)
        }

    override suspend fun deleteReactFromFirestore(
        fixtureId: String, customId: String, userId: String
    ) = try {
        reactRef.document(fixtureId).collection(customId).document(userId).delete().await()
        EmojiResponse.ReactSuccess(true)
    } catch (e: Exception) {
        EmojiResponse.ReactFailure(e)
    }
}