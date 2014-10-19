/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.view;

import br.com.monitoranuvem.controller.DashboardControl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
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
                .getRequestDispatcher("/");
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
            Logger.getLogger(MonitoringStartStop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MonitoringStartStop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MonitoringStartStop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MonitoringStartStop.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MonitoringStartStop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MonitoringStartStop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MonitoringStartStop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MonitoringStartStop.class.getName()).log(Level.SEVERE, null, ex);
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
