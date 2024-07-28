package test.muzz.domain.main

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import test.muzz.db.messages.MessagesDao
import test.muzz.main.models.Message
import javax.inject.Inject

/**
 * Main use case
 *
 * It hold the Messages Dao and conversion logic. Messages Dao could have been wrapped in a
 * repository however it is not strictly necessary as it is already a "repository" of sorts.
 *
 * I have avoided another layer of abstraction to speed up the process
 */
class MainUseCase @Inject constructor(
    private val dao: MessagesDao
) {

    suspend fun getMessageHistory(): List<Message> =
        dao.getMessageHistory().map { entityToMessage(it) }

    fun getLatest(): Flow<Message> = dao.getLatestMessage().map { entityToMessage(it) }

    fun populateDatabaseIfEmpty(messages: List<Message>) {
        dao.apply {
            if (noMessages()) insertMessages(
                messages.map {
                    messageToEntity(it)
                }
            )
        }
    }

    fun save(message: Message) {
        dao.insertSingleMessage(
            messageToEntity(message)
        )
    }

    // Alternative variant to getMessageHistory with Flow
    fun getMessageHistoryFlow(): Flow<List<Message>> = dao.getMessageHistoryFlow()
        .map {
            entityToMessageBulk(it)
        }
}