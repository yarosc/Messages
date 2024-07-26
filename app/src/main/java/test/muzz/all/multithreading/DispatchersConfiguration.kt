package test.muzz.all.multithreading

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

// For testing purposes

interface Needle {
    fun main(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
}

class ProdNeedle @Inject constructor(
    //Inject boolean value that helps identify if in Prod or Testing
) : Needle {
    override fun main() = Dispatchers.Main

    override fun io() = Dispatchers.IO

}


/**
 *
 *
class TestNeedle @Inject constructor(

): Needle {
    override fun main() {

        //Use test dispatcher immediate
    }

    override fun io() {
        //Use test dispatcher
    }

}
 */