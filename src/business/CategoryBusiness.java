/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import repository.CategoryRepository;

/**
 *
 * @author ACER
 */
public class CategoryBusiness {
    private CategoryRepository categoryRepository;

    public CategoryBusiness() {
        categoryRepository = new CategoryRepository();
        categoryRepository.readDataFromFile("Category.txt");
    }

    public CategoryBusiness(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    
    
    
}
