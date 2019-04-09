package main.kotlin.animal

import animal.IAnimalFactory
import main.kotlin.AnimalShelter

class AnimalFactory : IAnimalFactory {
    override fun newCat(name: String, gender: Gender, badHabits: String): Cat {
        val cat= Cat(name,gender,badHabits)
        AnimalShelter.webshop.addProduct(cat)
        return cat
    }

    override fun newCat(name: String, gender: Gender, badHabits: String, price: Double): Cat {
        val cat= Cat(name,gender,badHabits, price)
        AnimalShelter.webshop.addProduct(cat)
        return cat
    }

    override fun newDog(name: String, gender: Gender): Dog {
        val dog= Dog(name,gender)
        AnimalShelter.webshop.addProduct(dog)
        return dog
    }

    override fun newDog(name: String, gender: Gender, price: Double): Dog {
        val dog= Dog(name,gender, price)
        AnimalShelter.webshop.addProduct(dog)
        return dog
    }
}