/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo;

import java.io.File;

/**
 *
 * @author AMD
 */
public interface iCrud {
    
    public abstract boolean insertar();
    public abstract boolean modificar();
    public abstract boolean eliminar();
    public abstract File ValidarArchivo(String archivo);
    
    
    
}
