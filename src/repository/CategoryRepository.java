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
import java.util.Map;
import java.util.StringTokenizer;
import model.Brand;
import model.Category;

/**
 *
 * @author ACER
 */
public class CategoryRepository extends HashMap<String, Category> implements I_CRUD<String, Category>{

    @Override
    public int create(Category newItem) {
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
    public HashMap<String, Category> read() {
        try {
            return this;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Category read(String id) {
        Category result = null;
        try {
            result = this.get(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int update(Category editItem) {
        int result = 0;
        try {
            Category oldItem = this.get(editItem.getId());
            oldItem = editItem;
            result = 1;
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
    
    // Đọc dữ liệu từ file và lưu vào Repository
    public void readDataFromFile(String fileName) {
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                return;
            }
            // Tạo luồng đọc dữ liệu
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);

            String line;

            while ((line = bfr.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(line, ",");

                // Cắt các thông tin thành phần
                String _id = stk.nextToken();
                String _name = stk.nextToken();

                // Tái tạo Java Object
                Category category = new Category(_id, _name);

                // Đưa object vào Repository
                this.create(category);
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
            return;
        }
        try {
            // Ghi file
            File f = new File(fileName);

            // Tạo ra luồng ghi file
            FileWriter fw = new FileWriter(f);

            // Tạo ra đối tượng ghi file xuống storage
            PrintWriter pw = new PrintWriter(fw);

            for (Map.Entry<String, Category> entry : this.entrySet()) {
                System.out.println();
                Category item = entry.getValue();
                pw.println(item.getId() + ", " + item.getName());
            }

            pw.close();
            fw.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
