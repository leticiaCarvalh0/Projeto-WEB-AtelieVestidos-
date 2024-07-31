/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atelievestidos.controlador;

import com.mycompany.atelievestidos.modelo.dao.EstilistaDao;
import com.mycompany.atelievestidos.modelo.entidade.Estilista;
import com.mycompany.atelievestidos.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(WebConstantes.BASE_PATH + "/EstilistaControlador")
public class EstilistaControlador extends HttpServlet {

    private EstilistaDao estilistaDao;
    private Estilista estilista;
    String codigoEstilista = "";
    String nomeEstilista = "";
    String email = "";
    String telefone = "";
    String especialidade = "";
    String opcao = "";

    @Override
    public void init() throws ServletException {
        estilistaDao = new EstilistaDao();
        estilista = new Estilista();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            codigoEstilista = request.getParameter("codigoEstilista");
            nomeEstilista = request.getParameter("nomeEstilista");
            email = request.getParameter("email");
            telefone = request.getParameter("telefone");
            especialidade = request.getParameter("especialidade");
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
        estilista.setNomeEstilista(nomeEstilista);
        estilista.setEmail(email);
        estilista.setTelefone(telefone);
        estilista.setEspecialidade(especialidade);
        estilistaDao.salvar(estilista);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoEstilista", codigoEstilista);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nomeEstilista", nomeEstilista);
        request.setAttribute("email", email);
        request.setAttribute("telefone", telefone);
        request.setAttribute("especialidade", especialidade);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoEstilista", codigoEstilista);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nomeEstilista", nomeEstilista);
        request.setAttribute("email", email);
        request.setAttribute("telefone", telefone);
        request.setAttribute("especialidade", especialidade);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        estilista.setCodigoEstilista(Integer.valueOf(codigoEstilista));
        estilista.setNomeEstilista(nomeEstilista);
        estilista.setEmail(email);
        estilista.setTelefone(telefone);
        estilista.setEspecialidade(especialidade);
        estilistaDao.alterar(estilista);
        cancelar(request, response);
    }
    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        estilista.setCodigoEstilista(Integer.valueOf(codigoEstilista));
        estilista.setNomeEstilista(nomeEstilista);
        estilista.setEmail(email);
        estilista.setTelefone(telefone);
        estilista.setEspecialidade(especialidade);
        estilistaDao.excluir(estilista);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("codigoEstilista", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nomeEstilista", "");
        request.setAttribute("email", "");
        request.setAttribute("telefone", "");
        request.setAttribute("especialidade", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Estilista> estilistas = estilistaDao.buscarTodas();
        request.setAttribute("estilistas", estilistas);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroEstilista.jsp");
        dispatcher.forward(request, response);

    }
    
    public void validaCampos(){
        if(nomeEstilista==null || nomeEstilista.isEmpty()|| email==null || email.isEmpty() || telefone==null || telefone.isEmpty() || especialidade==null || especialidade.isEmpty()){
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }

}
