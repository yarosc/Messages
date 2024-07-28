package test.muzz.domain.main

import test.muzz.db.messages.MessageEntity
import test.muzz.main.models.Author
import test.muzz.main.models.Message
import java.time.LocalDateTime

private const val OWNER = "Me"

fun entityToMessageBulk(entities: List<MessageEntity>): List<Message> {
    val messages = mutableListOf<Message>()
    entities.forEach { messages += entityToMessage(it) }
    return messages
}

fun entityToMessage(entity: MessageEntity): Message {
    entity.apply {
        return Message(
            Author(
                name = author,
                owner = author == OWNER
            ),
            body = body,
            timestamp = LocalDateTime.parse(timestamp)
        )
    }
}

fun messageToEntity(message: Message): MessageEntity {
    message.apply {
        return MessageEntity(
            author = author.name,
            owner = author.owner,
            timestamp = timestamp.toString(),
            body = body,
        )
    }
}