/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Usuarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author REGISTRO
 */
@Local
public interface UsuariosFacadeLocal {

    void create(Usuarios usuarios);

    void edit(Usuarios usuarios);

    void remove(Usuarios usuarios);

    Usuarios find(Object id);

    Usuarios findByAcceAndCont(Object acce, Object cont);

    boolean findPermByAcceAndRole(Object acce, Object role);

    List<Usuarios> findAll();

    List<Usuarios> findRange(int[] range);

    int count();
    
}
