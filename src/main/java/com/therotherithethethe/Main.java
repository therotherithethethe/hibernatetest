package com.therotherithethethe;

import com.therotherithethethe.dao.GenericDAO;
import com.therotherithethethe.entity.Product;
import com.therotherithethethe.entity.Recipe;
import com.therotherithethethe.entity.Resource;
import java.util.HashSet;
import java.util.List;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        GenericDAO<Recipe> recipeDao = new GenericDAO<>("recipes", sessionFactory, Recipe.class);
        GenericDAO<Product> productDAO = new GenericDAO<>("Product", sessionFactory, Product.class);

        productDAO.save(new Product(null, "hleb", 100));

        Product product = productDAO.findBy("name", "hleb").getFirst();
        Recipe recipe = new Recipe(null, new HashSet<Resource>(List.of(new Resource(product, 12))), "hd");
        recipeDao.save(recipe);

    }
}
