package br.com.monitoranuvem.view;

import br.com.monitoranuvem.controller.ProviderInstanceControl;
import br.com.monitoranuvem.model.QtdStatusProvider;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
public class DashboardView extends HttpServlet {

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

        //DashboardControl dc = new DashboardControl();
        ProviderInstanceControl pic = new ProviderInstanceControl();

        //ArrayList<MNComputeService> listaComputeService = dc.startComputeService();
        ArrayList<QtdStatusProvider> listaStatusProvider = pic.listaQDTStatusProvider("RUNNING");

        String[] chartColours = new String[]{"#62aeef", "#d8605f", "#72c380", "#6f7a8a", "#f7cb38", "#5a8022", "#2c7282"};

        //Gera a quantidade total
        int qtdTotalInstancias = 0;
        for (QtdStatusProvider sp : listaStatusProvider) {
            qtdTotalInstancias += sp.getQuantidade();
        }

        String dadosGrafico = "";
        for (int i = 0; i < listaStatusProvider.size(); i++) {
            QtdStatusProvider sp = listaStatusProvider.get(i);

            //Calculo de percentual
            int qtdProvider = sp.getQuantidade();

            double percentual = (qtdProvider * 100.00 / qtdTotalInstancias * 100.00) / 100.00;
            percentual = Math.round(percentual*100.0)/100.0;

            dadosGrafico += "{label: \"" + sp.getProvider().getNome() + "\", data: " + percentual + ", color : \"" + chartColours[i] + "\"}";
            if (i < listaStatusProvider.size() - 1) {
                dadosGrafico += ";";
            }
        }

        //Instancia a sessão para manipular as variáveis de sessao
        HttpSession session = request.getSession(true);

        //session.setAttribute("listaComputeService", listaComputeService);
        session.setAttribute("listaStatusProvider", dadosGrafico);

        RequestDispatcher rd = request
                .getRequestDispatcher("/dashboard.jsp");
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
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardView.class.getName()).log(Level.SEVERE, null, ex);
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
