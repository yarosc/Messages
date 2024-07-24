package test.muzz.main.all

import test.muzz.main.models.Author
import test.muzz.main.models.Message

val mockMessages = listOf(
    Message(
        author = Author(
            name = "Me"
        ),
        body = "Hello! How are you?"
    ),
    Message(
        author = Author(
            name = "Lisa"
        ),
        body = "Hi!"
    ),
    Message(
        author = Author(
            name = "Lisa"
        ),
        body = "I'm good!"
    ),
    Message(
        author = Author(
            name = "Lisa"
        ),
        body = "How about you?"
    ),
    Message(
        author = Author(
            name = "Me"
        ),
        body = "On vacation, enjoying the weather... Always wanted to visit Tenerife and finally I'" +
                "am here. Best choice in a while!"
    ),
    Message(
        author = Author(
            name = "Lisa"
        ),
        body = "Oh, right! I know, I've been there a year ago. A nice place indeed. However I" +
                " haven't had the chance to swim in the ocean. Have you?"
    ),
    Message(
        author = Author(
            name = "Me"
        ),
        body = "Yes... sort of. I swam in one of those natural pools close to the ocean. They are" +
                "filled with ocean water from the waves. "
    ),
    Message(
        author = Author(
            name = "Lisa"
        ),
        body = "That sounds lovely. What else is there to see?"
    ),
)