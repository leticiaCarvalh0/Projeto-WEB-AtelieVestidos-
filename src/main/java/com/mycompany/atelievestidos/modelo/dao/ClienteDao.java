/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atelievestidos.modelo.dao;

import com.mycompany.atelievestidos.modelo.entidade.Cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ClienteDao extends GenericoDAO<Cliente>{
    
    public void salvar(Cliente c){
        String insert = "INSERT INTO CLIENTES(NOME,EMAIL, TELEFONE, ENDERECO) VALUES (?,?,?,?)";
        save(insert, c.getNomeCliente(), c.getEmail(), c.getTelefone(), c.getEndereco());
    }
    
    public void alterar(Cliente c){
        String update = "UPDATE CLIENTES SET NOME=?,EMAIL=?, TELEFONE=?, ENDERECO=? WHERE ID=?";
        save(update,c.getNomeCliente(),  c.getEmail(), c.getTelefone(), c.getEndereco());
    }
    
    public void excluir(Cliente c){
        String delete="DELETE FROM CLIENTES WHERE ID=?";
        save(delete, c.getCodigoCliente());
    }
    
    public Cliente buscarPorId(int id){
        String select = "SELECT * FROM CLIENTES WHERE ID=?";
        return buscarPorId(select, new ClienteRowMapper(), id);
    }
    
    public List<Cliente> buscarTodas(){
         String select = "SELECT * FROM CLIENTES";
        return buscarTodos(select, new ClienteRowMapper());
    }
    
    public static class ClienteRowMapper implements RowMapper<Cliente>{

        @Override
        public Cliente mapRow(ResultSet rs) throws SQLException {
            Cliente cliente = new Cliente();
            cliente.setCodigoCliente(rs.getInt("ID"));
            cliente.setNomeCliente(rs.getString("NOME"));
            cliente.setEmail(rs.getString("EMAIL"));
            cliente.setTelefone(rs.getString("TELEFONE"));
            cliente.setEndereco(rs.getString("ENDERECO"));
            return cliente;
        }
        
    }
    
}
