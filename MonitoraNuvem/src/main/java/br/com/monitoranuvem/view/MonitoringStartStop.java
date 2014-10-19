package br.com.monitoranuvem.view;

import br.com.monitoranuvem.controller.DashboardControl;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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
public class MonitoringStartStop extends HttpServlet {

    DashboardControl dc;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     * @throws java.lang.InterruptedException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException, InterruptedException {

        //Controlador de Threads
        dc = DashboardControl.getInstance();

        //Instancia a sessão para manipular as variáveis de sessao
        HttpSession session = request.getSession(true);

        //Recuperar parametro enviados da view
        String monitoring = request.getParameter("monitoring");
        String action = request.getParameter("action");

        if (action != null && action.equalsIgnoreCase("stopStartThread")) {
            if (monitoring == null) {
                //Para Monitoramento
                session.setAttribute("monitoringstatus", "stoped");
                dc.stopThread();
            } else if (monitoring.trim().equalsIgnoreCase("on")) {
                //Inicia Monitoramento
                session.setAttribute("monitoringstatus", "started");
                dc.startThread();
            }
        }
        
        if (action != null && action.equalsIgnoreCase("stopStartThread") || action != null && action.equalsIgnoreCase("updateStatus")) {
            //Aguarda 5 segundos
            Thread.sleep(5000);

            //Status Monitoramento
            String statusAmazon = dc.statusThreadAmazon();
            String statusOpen = dc.statusThreadOpen();
            String statusAlerts = dc.statusThreadAlerts();

            //Insere status na sessão
            session.setAttribute("statusamazon", statusAmazon);
            session.setAttribute("statusopen", statusOpen);
            session.setAttribute("statusalerts", statusAlerts);
        }
        
        //Atualiza a página atual
        RequestDispatcher rd = request
                .getRequestDispatcher("#");
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
            throw new ServletException("ClassNotFoundException", ex);
        } catch (SQLException ex) {
           throw new ServletException("SQLException", ex);
        } catch (ParseException ex) {
            throw new ServletException("ParseException", ex);
        } catch (InterruptedException ex) {
            throw new ServletException("InterruptedException", ex);
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
            throw new ServletException("ClassNotFoundException", ex);
        } catch (SQLException ex) {
            throw new ServletException("SQLException", ex);
        } catch (ParseException ex) {
            throw new ServletException("ParseException", ex);
        } catch (InterruptedException ex) {
            throw new ServletException("InterruptedException", ex);
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
