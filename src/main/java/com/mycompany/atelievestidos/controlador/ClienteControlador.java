/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atelievestidos.controlador;

import com.mycompany.atelievestidos.modelo.dao.ClienteDao;
import com.mycompany.atelievestidos.modelo.entidade.Cliente;
import com.mycompany.atelievestidos.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(WebConstantes.BASE_PATH + "/ClienteControlador")
public class ClienteControlador extends HttpServlet {

    private ClienteDao clienteDao;
    private Cliente cliente;
    String codigoCliente = "";
    String nomeCliente = "";
    String email = "";
    String telefone = "";
    String endereco = "";
    String opcao = "";

    @Override
    public void init() throws ServletException {
        clienteDao = new ClienteDao();
        cliente = new Cliente();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            codigoCliente = request.getParameter("codigoCliente");
            nomeCliente = request.getParameter("nomeCliente");
            email = request.getParameter("email");
            telefone = request.getParameter("telefone");
            endereco = request.getParameter("endereco");
            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }
            switch (opcao) {
                case "cadastrar":  cadastrar(request, response); break;
                case "editar":  editar(request, response); break;
                case "confirmarEditar":  confirmarEditar(request, response); break;
                case "excluir":  excluir(request, response); break;
                case "confirmarExcluir":  confirmarExcluir(request, response); break;
                case "cancelar":  cancelar(request, response); break;
                default:
                    throw new IllegalArgumentException("Opção inválida"+opcao);
            }
          

        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são numeros válidos");
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //validaCampos();
        cliente.setNomeCliente(nomeCliente);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        clienteDao.salvar(cliente);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoCliente", codigoCliente);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nomeCliente", nomeCliente);
        request.setAttribute("email", email);
        request.setAttribute("telefone", telefone);
        request.setAttribute("endereco", endereco);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoCliente", codigoCliente);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nomeCliente", nomeCliente);
        request.setAttribute("email", email);
        request.setAttribute("telefone", telefone);
        request.setAttribute("endereco", endereco);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //validaCampos();
        cliente.setCodigoCliente(Integer.valueOf(codigoCliente));
        cliente.setNomeCliente(nomeCliente);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        clienteDao.alterar(cliente);
        cancelar(request, response);
    }
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cliente.setCodigoCliente(Integer.valueOf(codigoCliente));
        cliente.setNomeCliente(nomeCliente);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);
        clienteDao.excluir(cliente);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoCliente", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nomeCliente", "");
        request.setAttribute("email", "");
        request.setAttribute("telefone", "");
        request.setAttribute("endereco", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = clienteDao.buscarTodas();
        request.setAttribute("clientes", clientes);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroCliente.jsp");
        dispatcher.forward(request, response);

    }
    
    /*public void validaCampos(){
        if(nomeCliente==null || nomeCliente.isEmpty()|| email==null || email.isEmpty() || telefone==null || telefone.isEmpty() || endereco==null || endereco.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }*/

}
