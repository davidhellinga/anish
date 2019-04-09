package main.kotlin

import main.kotlin.animal.Animal
import main.kotlin.animal.Cat
import main.kotlin.animal.Dog
import main.kotlin.animal.Gender
import java.util.*

class Reservation() {
    var animals: MutableList<Animal> = ArrayList()
        private set

    fun NewCat(name: String, gender: Gender, badHabits: String, price: Double) =
        animals.add(Cat(name, gender, badHabits, price))

    fun NewDog(name: String, gender: Gender, price: Double) = animals.add(
        Dog(
            name,
            gender,
            price
        )
    )
}