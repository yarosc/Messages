package test.muzz.domain.main

import test.muzz.all.date.convertDbStringToFormatted
import test.muzz.db.messages.MessageEntity
import test.muzz.main.models.Author
import test.muzz.main.models.Message

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
            formattedTimeStamp = convertDbStringToFormatted(timestamp),
            rawTimestamp = timestamp
        )
    }
}

fun messageToEntityBulk(messages: List<Message>): List<MessageEntity> {
    val entities = mutableListOf<MessageEntity>()
    messages.forEach { entities += messageToEntity(it) }
    return  entities
}

fun messageToEntity(message: Message): MessageEntity {
    message.apply {
        return MessageEntity(
            author = author.name,
            owner = author.owner,
            timestamp = rawTimestamp,
            body = body,
        )
    }
}