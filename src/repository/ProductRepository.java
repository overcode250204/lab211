/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import model.Product;

/**
 *
 * @author ACER
 */
public class ProductRepository extends HashMap<String, Product> implements I_CRUD<String, Product> {

    @Override
    public int create(Product newItem) {
        int result = 0;
        try {
            this.put(newItem.getId(), newItem);
            result = 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public HashMap<String, Product> read() {
        try {
            return this;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Product read(String id) {
        Product result = null;
        try {
            result = this.get(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int update(Product editItem) {
        int result = 0;
        try {
            this.replace(editItem.getId(), editItem);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int delete(String id) {
        int result = 0;
        try {
            this.remove(id);
            result = 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    
    public void readDataFromFile(String fileName) {
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                return;
            }
            
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);

            String line;

            while ((line = bfr.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");

                
                String id = stk.nextToken().trim();
                String name = stk.nextToken().trim();
                String brand_id = stk.nextToken().trim();
                String category_id = stk.nextToken().trim();
                String model_year = stk.nextToken().trim();
                String list_price = stk.nextToken().trim();
                
                int intModelYear = Integer.parseInt(model_year.trim());
                double doubleListPrice = Double.parseDouble(list_price.trim());
                Product product = new Product(id, name, brand_id, category_id, intModelYear, doubleListPrice);

                
                this.create(product);
            }

            bfr.close();
            fr.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void writeDataToFile(String fileName) {
        if (this.isEmpty()) {
            System.out.println("Current Repository is empty");
        }
        try {

            File f = new File(fileName);
            
            FileWriter fw = new FileWriter(f);

            
            PrintWriter pw = new PrintWriter(fw);

            for (Entry<String, Product> entry : this.entrySet()) {
                Product item = entry.getValue();
                pw.println(item.getId() + ", " + item.getName() + ", " + item.getBrand_id() + ", " + item.getCategory_id() + ", " + item.getModel_year() + ", " + item.getList_price());
            }
            pw.close();
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
