package dal

import main.kotlin.webshop.ISellable

interface IWebshopDataManager {

    fun loadWebshop(): List<ISellable>
    fun saveWebshop(products: List<ISellable>)
}