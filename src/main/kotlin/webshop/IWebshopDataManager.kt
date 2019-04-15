package main.kotlin.webshop

interface IWebshopDataManager {

    fun loadWebshop(): List<ISellable>
    fun saveWebshop(products: List<ISellable>)
}