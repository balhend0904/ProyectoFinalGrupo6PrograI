/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AMD
 */
public class Vehiculo implements iCrud{
    String placa;
    String marca;
    String modelo;
    String anio;
    String color;
    String duenio;
    
    
    public Vehiculo(){
        
    }
    
    public Vehiculo(String placa, String marca, String modelo, String anio, String color, String duenio){
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
        this.duenio = duenio;
        
        
        
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDuenio() {
        return duenio;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    @Override
    public boolean insertar() {
        FileWriter archivo = null;
        PrintWriter pw = null;
        File bd;
        String registro;
        
        bd= this.ValidarArchivo("bdVehiculos.txt");
        
        try {
            archivo = new FileWriter(bd,true);
            pw = new PrintWriter(archivo);
            
            registro=MessageFormat.format("{0} {1} {2} {3} {4} {5}",
                    this.placa, 
                    this.marca, 
                    this.modelo, 
                    this.anio, 
                    this.color, 
                    this.duenio);
            pw.println(registro);
            return true;
        } catch (IOException ex) {
            System.out.println("Error al conectarse al archivo");
            return false;
        } finally{
            pw.close();
            try {
                archivo.close();
                
            } catch (IOException ex) {
                    Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
            
        }
        
        
    }

    @Override
    public boolean modificar() {
        File bd= this.ValidarArchivo("bdVehiculos.txt");
        ArrayList<Vehiculo> listVehiculos=this.getDatosVehiculos();
        bd.delete();
        
        for (Vehiculo v : listVehiculos) {
            if (this.placa.equalsIgnoreCase(v.getPlaca())){
                v.setPlaca(this.placa);
                v.setMarca(this.marca);
                v.setModelo(this.modelo);
                v.setAnio(this.anio);
                v.setColor(this.color);
                v.setDuenio(this.duenio);
                
            }
            v.insertar();
        }
        return true;
    }

    @Override
    public boolean eliminar() {
        File bd= this.ValidarArchivo("bdVehiculos.txt");
        ArrayList<Vehiculo> listVehiculos=this.getDatosVehiculos();
        bd.delete();
        
        for (Vehiculo v : listVehiculos) {
            if (!this.placa.equalsIgnoreCase(v.getPlaca())){
                v.insertar();
                
            }
            
        }
        return true;
    }

    @Override
    public File ValidarArchivo(String archivo) {
        String URL= "src\\archivos\\"+archivo;
        
        File bd=new File(URL);
        
        if(!bd.exists()){
            try {
                bd.createNewFile();
            } catch (IOException ex) {
                System.out.println("Error al abrir el archivo");
            }
        }
        return bd;
    }
    
    public ArrayList<Vehiculo> getDatosVehiculos(){
        ArrayList<Vehiculo> listVehiculos = new ArrayList<Vehiculo>();
        Scanner in = null;
        File bd = this.ValidarArchivo("bdVehiculos.txt");
        String placa, marca, modelo, anio, color, duenio;
        try {
            in = new Scanner(bd);
            
            while (in.hasNext()){
                placa=in.next();
                marca=in.next();
                modelo=in.next();
                anio=in.next();
                color=in.next();
                duenio=in.next();
                
                listVehiculos.add(new Vehiculo(placa, 
                        marca, 
                        modelo, 
                        anio, 
                        color, 
                        duenio));
  
            }
            
        } catch (FileNotFoundException ex){
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally{
            in.close();
        }
        return listVehiculos;
        
    }
            
    

    
}
