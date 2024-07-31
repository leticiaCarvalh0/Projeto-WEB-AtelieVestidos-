/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atelievestidos.controlador;

import com.mycompany.atelievestidos.modelo.dao.FuncionarioDao;
import com.mycompany.atelievestidos.modelo.entidade.Funcionario;
import com.mycompany.atelievestidos.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(WebConstantes.BASE_PATH + "/FuncionarioControlador")
public class FuncionarioControlador extends HttpServlet {

    private FuncionarioDao funcionarioDao;
    private Funcionario funcionario;
    String codigoFuncionario = "";
    String nomeFuncionario = "";
    String email = "";
    String telefone = "";
    String dataContratacao = "";
    String salario = "";
    String opcao = "";

    @Override
    public void init() throws ServletException {
        funcionarioDao = new FuncionarioDao();
        funcionario = new Funcionario();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            codigoFuncionario = request.getParameter("codigoEstilista");
            nomeFuncionario = request.getParameter("nomeEstilista");
            email = request.getParameter("email");
            telefone = request.getParameter("telefone");
            dataContratacao = request.getParameter("dataContratacao");
            salario = request.getParameter("salario");
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
        validaCampos();
        funcionario.setNomeFuncionario(nomeFuncionario);
        funcionario.setEmail(email);
        funcionario.setTelefone(telefone);
        funcionario.setDataContratacao(dataContratacao);
        funcionario.setSalario(Double.valueOf(salario));
        funcionarioDao.salvar(funcionario);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoFuncionario", codigoFuncionario);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nomeFuncionario", nomeFuncionario);
        request.setAttribute("email", email);
        request.setAttribute("telefone", telefone);
        request.setAttribute("dataContratacao", dataContratacao);
        request.setAttribute("salario", salario);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoFuncionario", codigoFuncionario);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nomeFuncionario", nomeFuncionario);
        request.setAttribute("email", email);
        request.setAttribute("telefone", telefone);
        request.setAttribute("dataContratacao", dataContratacao);
        request.setAttribute("salario", salario);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        funcionario.setCodigoFuncionario(Integer.valueOf(codigoFuncionario));
        funcionario.setNomeFuncionario(nomeFuncionario);
        funcionario.setEmail(email);
        funcionario.setTelefone(telefone);
        funcionario.setDataContratacao(dataContratacao);
        funcionario.setSalario(Double.valueOf(salario));
        funcionarioDao.alterar(funcionario);
        cancelar(request, response);
    }
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        funcionario.setCodigoFuncionario(Integer.valueOf(codigoFuncionario));
        funcionario.setNomeFuncionario(nomeFuncionario);
        funcionario.setEmail(email);
        funcionario.setTelefone(telefone);
        funcionario.setDataContratacao(dataContratacao);
        funcionario.setSalario(Double.valueOf(salario));
        funcionarioDao.excluir(funcionario);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoEstilista", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nomeEstilista", "");
        request.setAttribute("email", "");
        request.setAttribute("telefone", "");
        request.setAttribute("dataContratacao", "");
        request.setAttribute("salario", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Funcionario> funcionarios = funcionarioDao.buscarTodas();
        request.setAttribute("funcionarios", funcionarios);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroFuncionario.jsp");
        dispatcher.forward(request, response);

    }
    
    public void validaCampos(){
        if(nomeFuncionario==null || nomeFuncionario.isEmpty()|| email==null || email.isEmpty() || telefone==null || telefone.isEmpty() || dataContratacao==null || dataContratacao.isEmpty() || salario==null || salario.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }

}
