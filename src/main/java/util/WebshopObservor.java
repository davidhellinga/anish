package util;

import main.kotlin.webshop.ISellable;

import java.util.List;

public interface WebshopObservor {
    void Update(List<ProductModel> products);
}
