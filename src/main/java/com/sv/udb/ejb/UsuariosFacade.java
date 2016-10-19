/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author REGISTRO
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "POOPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }

    @Override
    public Usuarios findByAcceAndCont(Object acce, Object cont) {
        Query q = getEntityManager().createQuery("SELECT u FROM Usuarios u WHERE u.acceUsua = :acceUsua AND u.contUsua = :contUsua AND u.estaUsua = :estaUsua", Usuarios.class);        
        q.setParameter("acceUsua", acce);
        q.setParameter("contUsua", cont);
        q.setParameter("estaUsua", 1);
        List resu = q.getResultList();
        return resu.isEmpty() ? null : (Usuarios)resu.get(0);
    }

    @Override
    public boolean findPermByAcceAndRole(Object acce, Object role) {
        String query = "SELECT CASE COUNT(*) WHEN 0 THEN 'false' ELSE 'true' END AS permiso FROM " +
                        "(SELECT roles.*, usuarios.acce_usua, GET_URL_PATH(roles.codi_role) as dire_role_full FROM usuarios " +
                        "INNER JOIN usuarios_roles on usuarios.codi_usua = usuarios_roles.codi_usua " +
                        "INNER JOIN roles on roles.codi_role = usuarios_roles.codi_role " +
                        "WHERE esta_role = 1 AND usuarios.esta_usua = 1 AND usuarios_roles.esta_usua_role = 1) T " +
                        "WHERE T.acce_usua = ?1 AND T.dire_role_full = ?2";
        Query q = getEntityManager().createNativeQuery(query);
        q.setParameter(1, acce);
        q.setParameter(2, role);
        return ((String)q.getSingleResult()).equals("true");
    }
    
}
