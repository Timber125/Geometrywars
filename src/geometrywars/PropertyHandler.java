/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geometrywars;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class PropertyHandler {
    private File file;
    private String comment = "Last edited by ImprovedPropertyHandler on";
    private Map<String, String> map = new HashMap<>();
    public PropertyHandler(File f){
        this.file = f;
        readExistingProperties();
    }
    public PropertyHandler(File f, String comment){
        this.file = f;
        readExistingProperties();
        this.comment = comment;
    }
    public String getProperty(String s){
        return map.get(s);
    }
    public boolean existsKey(String key){
        return (map.keySet().contains(key));
    }
    public void setProperty(String key, String newVal){
        map.put(key, newVal);
    }
    private void readExistingProperties() {
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            prop.load(is);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to initialize inputstream");
        } catch (IOException ex) {
            System.out.println("Failed to load properties from clean inputstream");
        }
        
        for(Object s : prop.keySet()){
            String key = (String)s;
            String val = prop.getProperty(key);
            map.put(key, val);
        }
        if(is != null){
            try {
                is.close();
            } catch (IOException ex) {
                System.out.println("inputstream cannot be closed.");
            }
        }
    }
    
    public void saveChanges(){
        Properties prop = new Properties();
        OutputStream out = null;
        for(String key : map.keySet()){
            prop.setProperty(key, map.get(key));
        }
        try {
            out = new FileOutputStream(file);
            prop.store(out, comment);
        } catch (FileNotFoundException ex) {
            System.out.println("Failed to initialize outputstream");
        } catch (IOException ex) {
            System.out.println("Failed to store properties");
        }
        
        
        
        
    }
    
    public Map<String, String> getAll(){
        return map;
    }
}
