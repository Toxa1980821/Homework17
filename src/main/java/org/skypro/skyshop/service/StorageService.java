package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        fillTestData();
    }

    private void fillTestData() {
        Product p1 = new SimpleProduct(UUID.randomUUID(), "Ноутбук", 50000);
        Product p2 = new DiscountedProduct(UUID.randomUUID(), "Мышь", 1500, 20);
        Product p3 = new FixPriceProduct(UUID.randomUUID(), "Клавиатура");
        Product p4 = new SimpleProduct(UUID.randomUUID(), "Монитор", 20000);
        Product p5 = new DiscountedProduct(UUID.randomUUID(), "Наушники", 2500, 10);
        Product p6 = new SimpleProduct(UUID.randomUUID(), "Веб-камера", 4000);

        products.put(p1.getId(), p1);
        products.put(p2.getId(), p2);
        products.put(p3.getId(), p3);
        products.put(p4.getId(), p4);
        products.put(p5.getId(), p5);
        products.put(p6.getId(), p6);

        Article a1 = new Article(
                UUID.randomUUID(),
                "Как выбрать ноутбук",
                "Ноутбук — выбирай с умом. Ноутбук должен подходить вам."
        );
        Article a2 = new Article(
                UUID.randomUUID(),
                "Лучшие мыши для работы",
                "Эргономичные мыши снижают нагрузку на кисть."
        );
        Article a3 = new Article(
                UUID.randomUUID(),
                "Фикс-прайс: что это значит",
                "Товары с фиксированной ценой удобны для бюджета."
        );

        articles.put(a1.getId(), a1);
        articles.put(a2.getId(), a2);
        articles.put(a3.getId(), a3);
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }

    public Collection<Searchable> getAllSearchable() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(products.values());
        result.addAll(articles.values());
        return result;
    }
}
