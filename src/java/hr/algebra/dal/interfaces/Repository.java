/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.interfaces;

import java.util.List;

/**
 *
 * @author IgorKvakan
 */
public interface Repository <T extends Object> {
    
     List<T> getEntity() throws Exception;
     T getEntityById(int id) throws Exception;
    void addEntity(T entity) throws Exception;
    void updateEntity(T entity) throws Exception;
    void deleteEntity(int id) throws Exception;
        
    
}
