package br.com.monitoranuvem.view;

import br.com.monitoranuvem.controller.ProviderControl;
import br.com.monitoranuvem.controller.ProviderInstanceControl;
import br.com.monitoranuvem.model.InstanceProvider;
import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.QtdStatusProvider;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
 * @author Cristiano Leonardi, MÃ¡rcio Bolzan
 */
public class DashboardDetailView extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {

        //Instancia a sessao para manipular as variaveis de sessao
        HttpSession session = request.getSession(true);

        //Instancia os controladores
        ProviderInstanceControl pic = new ProviderInstanceControl();
        ProviderControl pc = new ProviderControl();

        //Ação que determina o que será executado pelo Controller.
        String action = request.getParameter("action");

        //Recupera dados enviados na view
        String providerName = request.getParameter("provider");

        //Recupera o id do provider
        Provider p = pc.buscaProvider(providerName);

        //Recupera lista de status por provedor
        ArrayList<QtdStatusProvider> listaQtdStatusProvider = pic.listaQDTStatusProvider(p.getId());

        Map<String, Integer> mapQtdStatusProvider = new HashMap<String, Integer>();
        for (QtdStatusProvider qtdSP : listaQtdStatusProvider) {
            mapQtdStatusProvider.put(qtdSP.getStatus(), qtdSP.getQuantidade());
        }

        session.setAttribute("provider", p);
        session.setAttribute("mapQtdStatusProvider", mapQtdStatusProvider);

        if (action != null && action.equalsIgnoreCase("listarInstanceByStatus")) {
            //Recupera dados enviados na view
            String status = request.getParameter("status");

            ArrayList<InstanceProvider> listaInstanceProvider = pic.listaInstanceProvider(providerName, status);

            session.setAttribute("listaInstanceProvider", listaInstanceProvider);
        }

        //Redireciona para a view especifica
        RequestDispatcher rd = request
                .getRequestDispatcher("/dashboarddetail.jsp");
        rd.forward(request, response);

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
            Logger.getLogger(DashboardDetailView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDetailView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DashboardDetailView.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DashboardDetailView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardDetailView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(DashboardDetailView.class.getName()).log(Level.SEVERE, null, ex);
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
