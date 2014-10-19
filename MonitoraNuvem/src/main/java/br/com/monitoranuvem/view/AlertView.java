package br.com.monitoranuvem.view;

import br.com.monitoranuvem.controller.ProviderAlerts;
import br.com.monitoranuvem.controller.ProviderControl;
import br.com.monitoranuvem.model.Alerts;
import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ProviderService;
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
 * @author Cristiano
 */
public class AlertView extends HttpServlet {

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

        //Instancia do controller do alerts
        ProviderControl pc = new ProviderControl();

        //Instancia do controller do provider
        ProviderAlerts pa = new ProviderAlerts();

        //Instancia a sessão para manipular as variáveis de sessao
        HttpSession session = request.getSession(true);

        //Ação que determina o que será executado pelo Controller.
        String action = request.getParameter("action");

        if (action == null || action.equalsIgnoreCase("") || action.equalsIgnoreCase("listarAlerts")) {
            //###LISTAR ALERTS###
            ArrayList<Alerts> listaAlerts = pa.listaAlerts();

            ArrayList<Provider> listaProvedores = pc.listaProvider();

            session.setAttribute("listaAlerts", listaAlerts);
            session.setAttribute("listaProvedores", listaProvedores);

            RequestDispatcher rd = request
                    .getRequestDispatcher("/alert.jsp");
            rd.forward(request, response);

        } else if (action.equalsIgnoreCase("criarAlerts")) {
            //###CRIAR ALERTS###
            String alertName = request.getParameter("alertname");
            int idProvider = Integer.parseInt(request.getParameter("provider"));
            String status = request.getParameter("status");
            String metric = request.getParameter("metric");
            String operation = request.getParameter("operation");
            String metricValue = request.getParameter("metricvalue");

            if (pa.criarAlerts(alertName, idProvider, status, metric, operation, metricValue)) {
                session.setAttribute("responseAction", "Ok");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-checkmark-circle\"></i> Parabéns!</strong> Seu alerta foi cadastrado com sucesso.");

                ArrayList<Alerts> listaAlerts = pa.listaAlerts();
                session.setAttribute("listaAlerts", listaAlerts);
                
                ArrayList<Provider> listaProvedores = pc.listaProvider();
                session.setAttribute("listaProvedores", listaProvedores);
            } else {
                session.setAttribute("responseAction", "Erro");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-close-4\"></i> Erro!</strong> Não foi possível cadastrar este alerta.");
            }

            RequestDispatcher rd = request
                    .getRequestDispatcher("/alert.jsp");
            rd.forward(request, response);

        } else if (action.equalsIgnoreCase("buscarAlerts")) {
            //###BUSCAR ALERTS PARA ATUALIZAR O MESMO###
            int id = Integer.parseInt(request.getParameter("id"));

            Alerts alert = new Alerts();
            alert = pa.buscaAlerts(id);

            session.setAttribute("alert", alert);
            session.setAttribute("action", "atualizarAlerts");

            ArrayList<Provider> listaProvedores = pc.listaProvider();
            session.setAttribute("listaProvedores", listaProvedores);

            ArrayList<Alerts> listaAlerts = pa.listaAlerts();
            session.setAttribute("listaAlerts", listaAlerts);

            RequestDispatcher rd = request
                    .getRequestDispatcher("/alert.jsp");
            rd.forward(request, response);

        } else if (action.equalsIgnoreCase("atualizarAlerts")) {
            //###ALTERAR ALERTS###
            int id = Integer.parseInt(request.getParameter("id"));
            String alertName = request.getParameter("alertname");
            int idProvider = Integer.parseInt(request.getParameter("provider"));
            String status = request.getParameter("status");
            String metric = request.getParameter("metric");
            String operation = request.getParameter("operation");
            String metricValue = request.getParameter("metricvalue");

            if (pa.atualizaAlerts(alertName, idProvider, status, metric, operation, metricValue, id)) {
                session.setAttribute("responseAction", "Ok");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-checkmark-circle\"></i> Parabéns!</strong> Seu alerta foi atualizado com sucesso.");

                ArrayList<Alerts> listaAlerts = pa.listaAlerts();
                session.setAttribute("listaAlerts", listaAlerts);
                
                ArrayList<Provider> listaProvedores = pc.listaProvider();
                session.setAttribute("listaProvedores", listaProvedores);
            } else {
                session.setAttribute("responseAction", "Erro");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-close-4\"></i> Erro!</strong> Não foi possível atualizar este alerta.");
            }

            RequestDispatcher rd = request
                    .getRequestDispatcher("/alert.jsp");
            rd.forward(request, response);

        } else if (action.equalsIgnoreCase("deletarAlerts")) {
            //###DELETAR ALERTS###
            int id = Integer.parseInt(request.getParameter("id"));
            if (pa.deletaAlerts(id)) {
                session.setAttribute("responseAction", "Ok");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-checkmark-circle\"></i> Parabéns!</strong> Seu alerta foi removido com sucesso.");

                ArrayList<Alerts> listaAlerts = pa.listaAlerts();
                session.setAttribute("listaAlerts", listaAlerts);
                
                ArrayList<Provider> listaProvedores = pc.listaProvider();
                session.setAttribute("listaProvedores", listaProvedores);
            } else {
                session.setAttribute("responseAction", "Erro");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-close-4\"></i> Erro!</strong> Não foi possível remover este alerta.");
            }

            RequestDispatcher rd = request
                    .getRequestDispatcher("/alert.jsp");
            rd.forward(request, response);
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
            Logger.getLogger(AlertView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AlertView.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AlertView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AlertView.class.getName()).log(Level.SEVERE, null, ex);
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
