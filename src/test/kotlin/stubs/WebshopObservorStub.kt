package stubs

import models.ProductModel
import util.WebshopObservor

class WebshopObservorStub : WebshopObservor {
    var observorTriggered=false

    override fun update(products: MutableList<ProductModel>?) {
        observorTriggered=true
    }
}