/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atelievestidos.controlador;

import com.mycompany.atelievestidos.modelo.dao.ModeloDao;
import com.mycompany.atelievestidos.modelo.entidade.Modelo;
import com.mycompany.atelievestidos.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(WebConstantes.BASE_PATH + "/ModeloControlador")
public class ModeloControlador extends HttpServlet {

    private ModeloDao modeloDao;
    private Modelo modelo;
    String codigoModelo = "";
    String nomeModelo = "";
    String opcao = "";

    @Override
    public void init() throws ServletException {
        modeloDao = new ModeloDao();
        modelo = new Modelo();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            codigoModelo = request.getParameter("codigoModelo");
            nomeModelo= request.getParameter("nomeModelo");
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
        modelo.setNomeModelo(nomeModelo);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoModelo", codigoModelo);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nomeModelo", nomeModelo);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoModelo", codigoModelo);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nomeModelo", nomeModelo);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modelo.setCodigoModelo(Integer.valueOf(codigoModelo));
        modelo.setNomeModelo(nomeModelo);
        cancelar(request, response);
    }
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        modelo.setCodigoModelo(Integer.valueOf(codigoModelo));
        modelo.setNomeModelo(nomeModelo);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoModelo", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nomeModelo", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Modelo> modelos = modeloDao.buscarTodas();
        request.setAttribute("modelos", modelos);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroModelo.jsp");
        dispatcher.forward(request, response);

    }

}
