/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import business.BrandBusiness;
import business.CategoryBusiness;
import business.ProductBusiness;
import utils.Utils;

/**
 *
 * @author ACER
 */
public class MainProgram {

    public static void main(String[] args) {
        int choice = 0;
        boolean loop = true;
        boolean checkLoop = false;
        boolean modified = false;
        String exit = "";
        CategoryBusiness categoryBusiness = new CategoryBusiness();
        BrandBusiness brandBusiness = new BrandBusiness();
        ProductBusiness productBusiness = new ProductBusiness();
        Menu menuApp = new Menu();
        menuApp.add("1. Create a product");
        menuApp.add("2. Search product information by name");
        menuApp.add("3. Update product information");
        menuApp.add("4. Delete product information");
        menuApp.add("5. Save to file");
        menuApp.add("6. Print all lists from file");
        menuApp.add("7. Exit");

        do {
            menuApp.displayMenu();
            choice = Utils.getInt("Enter your choice: ", 1, menuApp.size());
            switch (choice) {
                case 1:
                    do {
                        if (productBusiness.addProduct() == 1) {
                            modified = true;
                            Utils.display("Success!!!");
                        } else {
                            Utils.display("Failure");
                        }
                        checkLoop = menuApp.confirmYesNo("Do you want to back to the menu ? (Y/N)");
                    } while (!checkLoop);
                    break;
                case 2:
                    do {
                        productBusiness.showProductList(productBusiness.searchProductByName(Utils.getString("Enter product name: ")));
                        checkLoop = menuApp.confirmYesNo("Do you want to back to the menu ? (Y/N)");
                    } while (!checkLoop);
                    break;
                case 3:
                    do {
                        if (productBusiness.updateProduct(Utils.getString("Enter ID product that you want to update: ")) == 1) {
                            modified = true;
                            Utils.display("Success!!!");
                        } else {
                            Utils.display("Failure!!!");
                        }
                        checkLoop = menuApp.confirmYesNo("Do you want to back to the menu ? (Y/N)");
                    } while (!checkLoop);
                    break;
                case 4:
                    do {
                        if (productBusiness.deleteProduct(Utils.getString("Enter ID product that you want to delete: ")) == 1) {
                            modified = true;
                            Utils.display("Success!!!");
                        } else {
                            Utils.display("Failure!!!");
                        }
                        checkLoop = menuApp.confirmYesNo("Do you want to back to the menu ? (Y/N)");
                    } while (!checkLoop);
                    break;
                case 5:
                    if (productBusiness.save() == 1) {
                        modified = false;
                        Utils.display("Success");
                    } else {
                        Utils.display("Failure!!!");
                    }
                    break;
                case 6:
                    productBusiness.showProductListByPriceAndName();
                    break;
                case 7:
                    
                    exit = Utils.getString("Do you want to exit? (Y/N)");
                    if ("Y".equalsIgnoreCase(exit)) {
                        if (modified) {
                            if ("Y".equalsIgnoreCase(Utils.getString("You already have modified program!!! (NOTE: If you don't want to save anything press Y!)"))) {
                                loop = false;
                            } 
                        } else {
                            loop = false;
                        }
                    }
                    break;
            }

        } while (loop);

    }

}
