package main.kotlin.webshop

class Product(override val name: String, override val price: Double) : ISellable {
    override fun toString(): String {
        return "$name for $price"
    }
}