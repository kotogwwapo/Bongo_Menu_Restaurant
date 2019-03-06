package mjabellanosa02.gmail.com.RecallableClasses

class RandomMessages {
    private var messages = ArrayList<String>()

    private fun loadMessages(){
        messages.addAll(listOf<String>(
            "Don't forget about flowers",
            "You look nice today",
            "Have a nice day",
            "Remember to call your parents",
            "It's such a beautiful day",
            "Smile more",
            "You're a good person",
            "Always thank God for everything",
            "You're Amazing Person",
            "Take it easy from time to time",
            "Dogs can't digest chocolate",
            "Peanut butter pairs well with chocolate",
            "Don't get a haircut a day before the party"))
    }

    fun getRandomMessage():String{
        loadMessages()
        val randomNumber = (0 until messages.size).random()

        return messages[randomNumber]
    }

}