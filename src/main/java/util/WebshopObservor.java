package util;

import models.ProductModel;

import java.util.List;

public interface WebshopObservor {
    void update(List<ProductModel> products);
}
