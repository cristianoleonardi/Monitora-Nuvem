package br.com.monitoranuvem.view;

import br.com.monitoranuvem.controller.ProviderControl;
import br.com.monitoranuvem.model.Provider;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cristiano Leonardi, Márcio Bolzan
 */
public class ProviderView extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        //Instancia do controller do provider
        ProviderControl pc = new ProviderControl();

        //Instancia a sessão para manipular as variáveis de sessao
        HttpSession session = request.getSession(true);

        //Ação que determina o que será executado pelo Controller.
        String action = request.getParameter("action");

        if (action == null || action.equalsIgnoreCase("") || action.equalsIgnoreCase("listarProvider")) {
            //###LISTAR PROVIDER###
            ArrayList<Provider> listaProvedores = pc.listaProvider();

            session.setAttribute("listaProvedores", listaProvedores);

            RequestDispatcher rd = request
                    .getRequestDispatcher("/provider.jsp");
            rd.forward(request, response);

        } else if (action.equalsIgnoreCase("criarProvider")) {
            //###CRIAR PROVIDER###
            String provider = request.getParameter("provider");

            if (pc.criarProvider(provider)) {
                session.setAttribute("responseAction", "Ok");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-checkmark-circle\"></i> Parabéns!</strong> Seu provedor foi cadastrado com sucesso.");

                ArrayList<Provider> listaProvedores = pc.listaProvider();
                session.setAttribute("listaProvedores", listaProvedores);

                RequestDispatcher rd = request
                        .getRequestDispatcher("/provider.jsp");
                rd.forward(request, response);
            } else {
                session.setAttribute("responseAction", "Erro");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-close-4\"></i> Erro!</strong> Não foi possível cadastrar este provedor.");

                RequestDispatcher rd = request
                        .getRequestDispatcher("/provider.jsp");
                rd.forward(request, response);
            }

        } else if (action.equalsIgnoreCase("buscarProvider")) {
            //###BUSCAR PROVIDER PARA ATUALIZAR O MESMO###
            String id = request.getParameter("id");

            Provider provedor = new Provider();
            provedor = pc.buscaProvider(Integer.parseInt(id));

            session.setAttribute("provedor", provedor);
            session.setAttribute("action", "atualizarProvider");

            ArrayList<Provider> listaProvedores = pc.listaProvider();
            session.setAttribute("listaProvedores", listaProvedores);

            RequestDispatcher rd = request
                    .getRequestDispatcher("/provider.jsp");
            rd.forward(request, response);

        } else if (action.equalsIgnoreCase("atualizarProvider")) {
            //###ALTERAR PROVIDER###
            String id = request.getParameter("id");
            String provider = request.getParameter("provider");

            if (pc.atualizaProvider(Integer.parseInt(id), provider)) {
                session.setAttribute("responseAction", "Ok");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-checkmark-circle\"></i> Parabéns!</strong> Seu provedor foi atualizado com sucesso.");

                ArrayList<Provider> listaProvedores = pc.listaProvider();
                session.setAttribute("listaProvedores", listaProvedores);

                RequestDispatcher rd = request
                        .getRequestDispatcher("/provider.jsp");
                rd.forward(request, response);
            } else {
                session.setAttribute("responseAction", "Erro");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-close-4\"></i> Erro!</strong> Não foi possível atualizar este provedor.");

                RequestDispatcher rd = request
                        .getRequestDispatcher("/provider.jsp");
                rd.forward(request, response);
            }

            RequestDispatcher rd = request
                    .getRequestDispatcher("/provider.jsp");
            rd.forward(request, response);

        } else if (action.equalsIgnoreCase("deletarProvider")) {
            //###DELETAR PROVIDER###
            String id = request.getParameter("id");
            if (pc.deletaProvider(Integer.parseInt(id))) {
                session.setAttribute("responseAction", "Ok");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-checkmark-circle\"></i> Parabéns!</strong> Seu provedor removido com sucesso.");

                ArrayList<Provider> listaProvedores = pc.listaProvider();
                session.setAttribute("listaProvedores", listaProvedores);

                RequestDispatcher rd = request
                        .getRequestDispatcher("/provider.jsp");
                rd.forward(request, response);
            } else {
                
                ArrayList<Provider> listaProvedores = pc.listaProvider();
                session.setAttribute("listaProvedores", listaProvedores);
                
                session.setAttribute("responseAction", "Erro");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-close-4\"></i> Erro!</strong> Não foi possível remover este provedor.");

                RequestDispatcher rd = request
                        .getRequestDispatcher("/provider.jsp");
                rd.forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProviderView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProviderView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProviderView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProviderView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
