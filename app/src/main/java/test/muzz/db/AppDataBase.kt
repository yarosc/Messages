package test.muzz.db

import androidx.room.Database
import androidx.room.RoomDatabase
import test.muzz.db.messages.MessageEntity
import test.muzz.db.messages.MessagesDao

@Database(
    entities = [
        MessageEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMessagesDao(): MessagesDao

    companion object {
        const val APP_DB_NAME = "app_db"
    }

}