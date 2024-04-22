package com.therotherithethethe;

import com.therotherithethethe.dao.GenericDAO;
import com.therotherithethethe.entity.Product;
import com.therotherithethethe.entity.Recipe;
import com.therotherithethethe.entity.RecipeIngredient;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {
        var sessionFactory = HibernateUtil.getSessionFactory();
        GenericDAO<Product> productDAO = new GenericDAO<>("products", sessionFactory, Product.class);
        GenericDAO<Recipe> recipeDAO = new GenericDAO<>("recipes", sessionFactory, Recipe.class);
        GenericDAO<RecipeIngredient> recipeIngredientDAO = new GenericDAO<>("recipe_ingredient", sessionFactory, RecipeIngredient.class);
    }
}
