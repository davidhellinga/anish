package animal

import main.kotlin.animal.Cat
import main.kotlin.animal.Dog
import main.kotlin.animal.Gender

interface IAnimalFactory {
    fun newCat(name: String, gender: Gender, badHabits: String): Cat
    fun newCat(name: String, gender: Gender, badHabits: String, price: Double): Cat

    fun newDog(name: String, gender: Gender): Dog
    fun newDog(name: String, gender: Gender, price: Double): Dog
}
