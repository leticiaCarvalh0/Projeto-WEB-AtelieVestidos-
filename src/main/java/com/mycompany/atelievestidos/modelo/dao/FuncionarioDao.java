/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atelievestidos.modelo.dao;

import com.mycompany.atelievestidos.modelo.entidade.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioDao extends GenericoDAO<Funcionario>{
    
    public void salvar(Funcionario f){
        String insert = "INSERT INTO FUNCIONARIO(NOME, EMAIL, TELEFONE, DATACONTRATACAO, SALARIO) VALUES (?,?,?,?,?)";
        save(insert, f.getNomeFuncionario(), f.getEmail(), f.getTelefone(), f.getDataContratacao(), f.getSalario());
    }
    
    public void alterar(Funcionario f){
        String update = "UPDATE FUNCIONARIO SET NOME=?,EMAIL=?, TELEFONE=?, DATACONTRATACAO=?, SALARIO=? WHERE ID=?";
        save(update,f.getNomeFuncionario(), f.getEmail(), f.getTelefone(), f.getDataContratacao(), f.getSalario());
    }
    
    public void excluir(Funcionario f){
        String delete="DELETE FROM FUNCIONARIO WHERE ID=?";
        save(delete, f.getCodigoFuncionario());
    }
    
    public Funcionario buscarPorId(int id){
        String select = "SELECT * FROM FUNCIONARIO WHERE ID=?";
        return buscarPorId(select, new FuncionarioRowMapper(), id);
    }
    
    public List<Funcionario> buscarTodas(){
         String select = "SELECT * FROM FUNCIONARIO";
        return buscarTodos(select, new FuncionarioRowMapper());
    }
    
    public static class FuncionarioRowMapper implements RowMapper<Funcionario>{

        @Override
        public Funcionario mapRow(ResultSet rs) throws SQLException {
            Funcionario funcionario = new Funcionario();
            funcionario.setCodigoFuncionario(rs.getInt("ID"));
            funcionario.setNomeFuncionario(rs.getString("NOME"));
            funcionario.setEmail(rs.getString("EMAIL"));
            funcionario.setTelefone(rs.getString("TELEFONE"));
            funcionario.setDataContratacao("DATACONTRATACAO");
            funcionario.setSalario(rs.getDouble("SALARIO"));
            return funcionario;
        }
        
    }
    
}
