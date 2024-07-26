package test.muzz.db.messages

import androidx.room.Entity
import androidx.room.PrimaryKey

const val MESSAGE_ENTITY = "MESSAGE_ENTITY"

/**
 * Message Table
 *
 * For simplicity I have omitted codifying any complex relationships between 2 parts in a
 * conversation (owner and interlocutor). For a real chat it would necessitate a way to
 * identify a conversation between two parties. For example, a conversation ID could allow
 * self-join on MESSAGE_ENTITY. This way, multiple conversations could be stored in the same
 * table.
 */

@Entity(
    tableName = MESSAGE_ENTITY
)
data class MessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String,
    val owner: Boolean,
    val timestamp: String,
    val body: String
)
