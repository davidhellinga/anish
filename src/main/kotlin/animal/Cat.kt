package main.kotlin.animal

class Cat(
    name: String,
    gender: Gender,
    val badHabits: String,
    override val price: Double = badHabits.let {
        var temp = (350.0 - 20.0 * badHabits.length)
        if (temp < 35.0) temp = 35.0
        temp
    }
) :
    Animal(name, gender, price) {
    override fun toString(): String {
        return super.toString() + ", bad habits: ${badHabits.toLowerCase()}"
    }
}