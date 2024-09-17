/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import model.Product;
import repository.BrandRepository;
import repository.CategoryRepository;
import repository.ProductRepository;
import utils.Utils;

/**
 *
 * @author ACER
 */
public class ProductBusiness {

    private ProductRepository productRepository;

    private BrandRepository brandRepository;

    private CategoryRepository categoryRepository;

    public ProductBusiness() {
        productRepository = new ProductRepository();
        productRepository.readDataFromFile("Product.txt");
        brandRepository = new BrandRepository();
        brandRepository.readDataFromFile("Brand.txt");
        categoryRepository = new CategoryRepository();
        categoryRepository.readDataFromFile("Category.txt");
    }

    public ProductBusiness(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public BrandRepository getBrandRepository() {
        return brandRepository;
    }

    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public int addProduct() {
        int result = 0;
        String id = "";
        String name = "";
        String brand_id = "";
        String category_id = "";
        int model_year = 0;
        double list_price = 0;
        boolean check = false;
        int productCount = 0;
        try {
            productCount = productRepository.size() + 1;

            id = "B" + (productCount < 10 ? "00" : productCount < 100 ? "0" : "") + productCount;

            name = Utils.getString("Enter product name: ");

            do {
                try {
                    check = false;
                    brand_id = Utils.getString("Enter brand id: ");

                    if (!brandRepository.isEmpty()) {
                        if (brandRepository.containsKey(brand_id)) {
                            check = true;
                        } else {
                            Utils.display("Brand id doesn't exist!");
                        }
                    } else {
                        Utils.display("Brand repo is empty!");
                    }

                } catch (Exception e) {
                    Utils.display(e.getMessage());
                }

            } while (!check);

            do {
                try {
                    check = false;
                    category_id = Utils.getString("Enter category id: ");
                    
                    if (!categoryRepository.isEmpty()) {
                        if (categoryRepository.containsKey(category_id)) {
                            check = true;
                        } else {
                            Utils.display("Category ID doesn't exist!");
                        }
                    } else {
                        Utils.display("Category rep is empty!");
                    }

                } catch (Exception e) {
                    Utils.display(e.getMessage());
                }

            } while (!check);

            model_year = Utils.getInt("Enter model year: ", 1950, 2024);
            list_price = Utils.getInt("Enter list price: ", 1000, 100000);

            Product product = new Product(id, name, brand_id, category_id, model_year, list_price);

            productRepository.create(product);
            result = 1;
        } catch (Exception e) {
            Utils.display(e.getMessage());
        }

        return result;
    }

    public List<Product> searchProductByName(String productName) {
        List<Product> result = new ArrayList();
        try {
            if (!productRepository.isEmpty()) {
                for (Product product : productRepository.values()) {
                    if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                        result.add(product);
                    }
                }
            }
            if (!result.isEmpty()) {
                Collections.sort(result, new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return Integer.compare(o1.getModel_year(), o2.getModel_year());
                    }
                });
            } else {
                Utils.display("Have no any Product");
            }
        } catch (Exception e) {
            Utils.display(e.getMessage());
        }

        return result;
    }

    public int updateProduct(String idProduct) {
        int result = 0;
        boolean check = false;
        if (productRepository.containsKey(idProduct)) {
            Product product = productRepository.get(idProduct);
            String name = "";
            String brand_id = "";
            String category_id = "";
            int model_year = 0;
            double list_price = 0;
            name = Utils.getString("Update product name: ", product.getName());
            do {
                check = false;
                brand_id = Utils.getString("Update brand ID of product: ", product.getBrand_id());
                if (brandRepository.containsKey(brand_id)) {
                    check = true;
                } else {
                    Utils.display("Brand ID doesn't exist!!!");
                }
            } while(!check);
            
            do {
                check = false;
                category_id = Utils.getString("Update category ID of product: ", product.getCategory_id());
                if (categoryRepository.containsKey(category_id)) {
                    check = true;
                } else {
                    Utils.display("Category ID doesn't exist!!!");
                }
            } while (!check);
                
            
            model_year = Utils.getInt("Update model year: ", 1950, 2024, product.getModel_year());
            list_price = Utils.getDouble("Update list price: ", 1000, 100000, product.getList_price());

            Product newProduct = new Product(product.getId(), name, brand_id, category_id, model_year, list_price);

            productRepository.update(newProduct);
            result = 1;
        } else {
            Utils.display("Product ID doesn't exist!");
            result = 0;
        }

        return result;
    }

    public int deleteProduct(String idProduct) {
        int result = 0;
        boolean check = false;

        if (productRepository.containsKey(idProduct)) {
            if ("Y".equalsIgnoreCase(Utils.getString("Do you want to delete ? (Y/N)"))) {
                check = true;
            }
            if (check) {
                productRepository.delete(idProduct);
                result = 1;
            }
        } else {
            Utils.display("Product does not exist");
        }

        return result;
    }

    public int save() {
        int result = 0;
        try {
            productRepository.writeDataToFile("Product.txt");
            result = 1;
        } catch (Exception e) {
            Utils.display(e.getMessage());
            result = 0;
        }
        return result;
    }

    public void showProductList() {
        for (Map.Entry<String, Product> entry : productRepository.entrySet()) {
            Utils.getString("" + entry.getValue());
        }
    }

    public void showProductList(List<Product> products) {
        try {
            for (Product product : products) {
                Utils.display(product.toString());
            }
        } catch (Exception e) {
            Utils.display("Product list is empty!!!");
        }

    }

    public void showProductListByPriceAndName() {
        try {
            productRepository.readDataFromFile("Product.txt");
            List<Product> products = new ArrayList<>(productRepository.values());
            if (!products.isEmpty()) {
                Collections.sort(products, new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        int priceComparition = Double.compare(o2.getList_price(), o1.getList_price());
                        if (priceComparition != 0) {
                            return priceComparition;
                        }
                        return o1.getName().compareToIgnoreCase(o2.getName());
                    }
                });
                if (!products.isEmpty()) {
                    for (Product product : products) {
                        String brandName = brandRepository.read(product.getBrand_id()).getName();
                        String categoryName = categoryRepository.read(product.getCategory_id()).getName();
                        Utils.display(product.getId() + ", " + product.getName() + ", " + brandName + ", " + categoryName + ", " + product.getModel_year() + ", " + product.getList_price());
                    }
                }
            } else {
                Utils.display("Repository is empty");
            }

        } catch (Exception e) {
            Utils.display(e.getMessage());
        }

    }

}
