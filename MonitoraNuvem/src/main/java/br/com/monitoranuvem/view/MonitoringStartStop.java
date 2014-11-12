package br.com.monitoranuvem.view;

import br.com.monitoranuvem.controller.DashboardControl;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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

    private ServletContext context;
    DashboardControl dc;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

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

        //Montar string XML retorno
        StringBuilder sb = new StringBuilder();

        //Instancia a sessÃ£o para manipular as variÃ¡veis de sessao
        HttpSession session = request.getSession(true);

        //Recuperar parametro enviados da view
        String btnMonitoring = request.getParameter("btnmonitoring");
        String action = request.getParameter("action");

        if (action != null && action.equalsIgnoreCase("startstopthread")) {
            boolean statusThread = false;
            if (btnMonitoring.trim().equalsIgnoreCase("false")) {
                //Parar Monitoramento
                session.setAttribute("monitoringstatus", "stoped");
                sb.append("<statusThread>");
                sb.append("<thread>");
                sb.append("<status>stoped</status>");
                sb.append("</thread>");
                sb.append("</statusThread>");

                dc.stopThread();
            } else if (btnMonitoring.trim().equalsIgnoreCase("true")) {
                //Iniciar Monitoramento
                session.setAttribute("monitoringstatus", "started");
                statusThread = true;
                sb.append("<statusThread>");
                sb.append("<thread>");
                sb.append("<status>started</status>");
                sb.append("</thread>");
                sb.append("</statusThread>");

                dc.startThread();
            }

            if (statusThread) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write(sb.toString());
            } else {
                //Nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }

        if (action != null && action.equalsIgnoreCase("updatestatus")) {
            boolean statusAdded = false;
            if (dc != null) {
                sb.append("<statusThread>");
                sb.append("<thread>");
                sb.append("<name>Amazon</name>");
                sb.append("<status>").append(dc.statusThreadAmazon()).append("</status>");
                sb.append("</thread>");
                sb.append("<thread>");
                sb.append("<name>OpenStack</name>");
                sb.append("<status>").append(dc.statusThreadOpen()).append("</status>");
                sb.append("</thread>");
                sb.append("<thread>");
                sb.append("<name>Alertas</name>");
                sb.append("<status>").append(dc.statusThreadAlerts()).append("</status>");
                sb.append("</thread>");
                sb.append("<thread>");
                sb.append("<name>Custos</name>");
                sb.append("<status>").append(dc.statusThreadCost()).append("</status>");
                sb.append("</thread>");
                sb.append("</statusThread>");

                statusAdded = true;
            }

            if (statusAdded) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write(sb.toString());
            } else {
                //Nothing to show
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
        }
    }

//}
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
