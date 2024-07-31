/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atelievestidos.modelo.dao;

import com.mycompany.atelievestidos.modelo.entidade.Estilista;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EstilistaDao extends GenericoDAO<Estilista>{
    
    public void salvar(Estilista e){
        String insert = "INSERT INTO ESTILISTAS(NOME,EMAIL, TELEFONE, ESPECIALIDADE) VALUES (?,?,?,?)";
        save(insert, e.getNomeEstilista(), e.getEmail(), e.getTelefone(), e.getEspecialidade());
    }
    
    public void alterar(Estilista e){
        String update = "UPDATE ESTILISTAS SET NOME=?,EMAIL=?, TELEFONE=?, ESPECIALIDADE=? WHERE ID=?";
        save(update,e.getNomeEstilista(), e.getEmail(), e.getTelefone(), e.getEspecialidade(), e.getCodigoEstilista());
    }
    
    public void excluir(Estilista e){
        String delete="DELETE FROM ESTILISTAS WHERE ID=?";
        save(delete, e.getCodigoEstilista());
    }
    
    public Estilista buscarPorId(int id){
        String select = "SELECT * FROM ESTILISTAS WHERE ID=?";
        return buscarPorId(select, new EstilistaRowMapper(), id);
    }
    
    public List<Estilista> buscarTodas(){
         String select = "SELECT * FROM ESTILISTAS";
        return buscarTodos(select, new EstilistaRowMapper());
    }
    
    public static class EstilistaRowMapper implements RowMapper<Estilista>{

        @Override
        public Estilista mapRow(ResultSet rs) throws SQLException {
            Estilista estilista = new Estilista();
            estilista.setCodigoEstilista(rs.getInt("ID"));
            estilista.setNomeEstilista(rs.getString("NOME"));
            estilista.setEmail(rs.getString("EMAIL"));
            estilista.setTelefone(rs.getString("TELEFONE"));
            estilista.setEspecialidade(rs.getString("ESPECIALIDADE"));
            return estilista;
        }
        
    }
    
}
