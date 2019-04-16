package main.kotlin

import main.kotlin.animal.*
import main.kotlin.webshop.ISellable
import main.kotlin.webshop.Product
import dal.WDMText
import models.ProductModel
import util.WebshopObservor
import kotlin.properties.Delegates

class Webshop : IWebshop {
    private val dataManager= WDMText()

    private var productList: List<ISellable> by Delegates.observable(mutableListOf()) { _, oldValue, newValue ->
        onproductListChanged?.invoke(oldValue, newValue)

    }

    private var onproductListChanged: (((List<ISellable>), (List<ISellable>)) -> Unit)? = null

    override fun observe(o: WebshopObservor) {
        onproductListChanged = { _, _ ->
            o.Update(getProducts())
        }
    }

    override fun getProductL(): List<ISellable> = productList

    override fun getProducts(): List<ProductModel> {
        val pl: MutableList<ProductModel> = mutableListOf()
        for (p in productList) {
            when (p) {
                is Cat -> {
                    pl.add(ProductModel(p.name, p.badHabits, p.price, p.gender, Cat::class.java))
                    addReservor(p, pl)
                }

                is Dog -> {
                    pl.add(ProductModel(p.name, p.needsWalk.toString(), p.price, p.gender, Dog::class.java))
                    addReservor(p, pl)
                }
                !is Animal -> pl.add(ProductModel(p.name, "", p.price, Gender.NaG, Product::class.java))
            }
        }
        return pl
    }

    private fun addReservor(p: Animal, pl: MutableList<ProductModel>) {
        if (p.reservedBy != null) pl.last().reservor = p.reservedBy as Reservor
    }

    override fun removeProductModel(removable: ProductModel) {
        when (removable.type) {
            Cat::class.java -> {
                val item = findCatFromModel(removable)
                removeProduct(item)
            }

            Dog::class.java -> {
                val item = findDogFromModel(removable)
                removeProduct(item)
            }
            Product::class.java -> removeProduct(Product(removable.name, removable.price))
        }
    }

    private fun findCatFromModel(prodMod: ProductModel): ISellable {
        return productList.filterIsInstance<Cat>()
            .find { it.name == prodMod.name && it.gender == prodMod.genderAsGender && it.badHabits == prodMod.property && it.price == prodMod.price } as ISellable
    }

    private fun findDogFromModel(prodMod: ProductModel): ISellable {
        return productList.filterIsInstance<Dog>()
            .find { it.name == prodMod.name && it.gender == prodMod.genderAsGender && it.price == prodMod.price } as ISellable
    }

    private fun findAnimalFromModel(prodMod: ProductModel): Animal {
        return when (prodMod.type) {
            Cat::class.java -> findCatFromModel(prodMod) as Cat
            Dog::class.java -> findDogFromModel(prodMod) as Dog
            else -> throw Exception("Animal not Found")
        }

    }

    override fun addProduct(product: ISellable): Boolean {
        productList = productList + product
        return true
    }

    override fun removeProduct(product: ISellable): Boolean {
        productList = productList - product
        return true
    }

    override fun reserveAnimal(animal: ProductModel, reservor: String): Boolean =
        findAnimalFromModel(animal).reserve(reservor)

    override fun unreserveAnimal(animal: ProductModel): Boolean = findAnimalFromModel(animal).unreserve()

    override fun saveWebshop() {
        dataManager.saveWebshop(this.productList)
    }

    override fun loadWebshop() {
        this.productList=dataManager.loadWebshop()
    }


}