package main.kotlin

import main.kotlin.webshop.ISellable
import util.ProductModel
import util.WebshopObservor

interface IWebshop {
    fun getProducts(): List<ProductModel>
    fun addProduct(product: ISellable): Boolean
    fun removeProduct(product: ISellable): Boolean
    fun reserveAnimal(animal: ProductModel, reservor: String): Boolean
    fun saveWebshop()
    fun loadWebshop()
    fun observe(o: WebshopObservor)
    fun getProductL(): List<ISellable>
    fun removeProductModel(removable: ProductModel)
    fun unreserveAnimal(animal: ProductModel): Boolean
}