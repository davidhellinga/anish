package main.kotlin.webshop

import com.google.gson.Gson
import java.io.File
import java.io.FileNotFoundException

class WDMText : IWebshopDataManager{
    private val docPath="${javax.swing.JFileChooser().fileSystemView.defaultDirectory}/animalShelter.txt"
    private val specialDelimiter="&separator"

    override fun saveWebshop(products: List<ISellable>) {
        val gson = Gson()
        val output: MutableList<String> = mutableListOf()
        File(docPath).delete()
        File(docPath).createNewFile()
        for (prod in products) {
            val str = gson.toJson(prod)
            output.add(prod::class.java.toString().split(" ")[1] + specialDelimiter + str)
        }
        File(docPath).printWriter().use { out ->
            output.forEach { out.println(it) }
        }
    }

    override fun loadWebshop(): List<ISellable> {
        val gson = Gson()
        val productList: MutableList<ISellable> = mutableListOf()
        try {
            File(docPath).forEachLine {
                productList.add(
                    gson.fromJson(
                        it.split(specialDelimiter)[1],
                        Class.forName(it.split(specialDelimiter)[0])
                    ) as ISellable
                )
            }
        } catch (e: FileNotFoundException) {
            File(docPath).createNewFile()
        }
        return productList
    }
}