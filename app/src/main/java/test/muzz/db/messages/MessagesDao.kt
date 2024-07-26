package test.muzz.db.messages

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MessagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessages(messageList: List<MessageEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMessage(message: MessageEntity): Long

    @Query("SELECT * FROM $MESSAGE_ENTITY ORDER BY timestamp DESC LIMIT 1")
    fun getLatestMessage(): Flow<MessageEntity>


    @Query("SELECT * FROM $MESSAGE_ENTITY")
    suspend fun getMessageHistory(): List<MessageEntity>


    // Variation using Flow
    @Query("SELECT * FROM $MESSAGE_ENTITY")
    fun getMessageHistoryFlow(): Flow<List<MessageEntity>>

}