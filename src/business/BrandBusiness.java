/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.Map;
import java.util.Scanner;
import model.Brand;
import repository.BrandRepository;
import utils.Utils;

/**
 *
 * @author ACER
 */
public class BrandBusiness {

    private BrandRepository brandRepository;

    public BrandRepository getBrandRepository() {
        return brandRepository;
    }

    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public BrandBusiness() {
        brandRepository = new BrandRepository();
        brandRepository.readDataFromFile("Brand.txt");
    }

    public BrandBusiness(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public void createBrand() {
        Scanner scanner = new Scanner(System.in);

        String id = scanner.nextLine();
        //Bxxx
        String name = scanner.nextLine();

        String country = scanner.nextLine();
        Brand brand = new Brand(id, name, country);
        brandRepository.create(brand);

    }

    public void showBrandList() {
        if (!brandRepository.isEmpty()) {
            for (Map.Entry<String, Brand> enty : brandRepository.entrySet()) {
                Utils.display(enty.getValue().toString());
            }
        }

    }

}
