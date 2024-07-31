/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atelievestidos.modelo.dao;

import com.mycompany.atelievestidos.modelo.entidade.Modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ModeloDao extends GenericoDAO<Modelo>{
    
    public void salvar(Modelo m){
        String insert = "INSERT INTO MODELOS(MODELO) VALUES (?)";
        save(insert, m.getModelo());
    }
    
    public void alterar(Modelo m){
        String update = "UPDATE MODELOS SET MODELO=? WHERE ID=?";
        save(update,m.getModelo());
    }
    
    public void excluir(Modelo m){
        String delete="DELETE FROM MODELOS WHERE ID=?";
        save(delete, m.getModelo());
    }
    
    public Modelo buscarPorId(int id){
        String select = "SELECT * FROM MODELOS WHERE ID=?";
        return buscarPorId(select, new ModeloRowMapper(), id);
    }
    
    public List<Modelo> buscarTodas(){
         String select = "SELECT * FROM MODELOS";
        return buscarTodos(select, new ModeloRowMapper());
    }
    
    public static class ModeloRowMapper implements RowMapper<Modelo>{

        @Override
        public Modelo mapRow(ResultSet rs) throws SQLException {
            Modelo modelo = new Modelo();
            modelo.setCodigoModelo(rs.getInt("ID"));
            modelo.setNomeModelo(rs.getString("MODELO"));
            return modelo;
        }
        
    }
    
}
