package br.com.monitoranuvem.view;

import br.com.monitoranuvem.controller.ProviderControl;
import br.com.monitoranuvem.controller.ProviderDialogControl;
import br.com.monitoranuvem.controller.ProviderServiceControl;
import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ProviderService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jclouds.compute.ComputeService;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.compute.domain.NodeMetadata;

/**
 *
 * @author Cristiano
 */
public class MonitoringStart extends HttpServlet {

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
            throws ServletException, IOException {

        Provider pn = new Provider();
        ProviderControl pnc = new ProviderControl();
        try {
            pn = pnc.buscaProvider("Amazon");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MonitoringStart.class.getName()).log(Level.SEVERE, null, ex);
        }

        ProviderService ps = new ProviderService();
        ProviderServiceControl psc = new ProviderServiceControl();
        try {
            ps = psc.buscaProviderService(1);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MonitoringStart.class.getName()).log(Level.SEVERE, null, ex);
        }

        ComputeService compute = null;

        ProviderDialogControl pdc = new ProviderDialogControl();
        compute = pdc.getListCServ(ps);

        //Futuro será utilizado para redirecionar para o JSP e não ficar fazendo includes
        //RequestDispatcher rd = request
        //        .getRequestDispatcher("/dashboard.jsp");
        //rd.forward(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        RequestDispatcher rd1 = request.getRequestDispatcher("header.jsp");
        rd1.include(request, response);

        RequestDispatcher rd2 = request.getRequestDispatcher("topbar.jsp");
        rd2.include(request, response);

        out.println("<div class=\"main\">");

        RequestDispatcher rd3 = request.getRequestDispatcher("sidebar.jsp");
        rd3.include(request, response);

        out.println("<section id=\"content\">");
        out.println("<div class=\"wrapper\">");
        out.println("<div class=\"crumb\">");
        out.println("<ul class=\"breadcrumb\">");
        out.println("<li><a href=\"#\"><i class=\"icon16 i-home-4\"></i>Home</a></li>");
        out.println("<li class=\"active\">Dashboard</li>");
        out.println("</ul>");
        out.println("</div>");
        out.println("<div class=\"container-fluid\">");
        out.println("<div class=\"row-fluid\">");

        for (ComputeMetadata node : compute.listNodes()) {
            NodeMetadata metadata = compute.getNodeMetadata(node.getId());

            out.println("<div class=\"col-lg-6\">");
            out.println("<div class=\"page-header\">");
            out.println("<h3>Instância - " + node.getProviderId() + "</h3>");
            out.println("</div>");
            out.println("<table class=\"table table-bordered\">");
            out.println("<tbody>");

            out.println("<tr>");
            out.println("<th>ID:</th>");
            out.println("<td class=\"center\">" + metadata.getId() + "</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<th>Nome:</th>");
            out.println("<td class=\"center\">" + metadata.getName() + "</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<th>Grupo:</th>");
            out.println("<td class=\"center\">" + metadata.getGroup() + "</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<th>Status:</th>");
            out.println("<td class=\"center\">" + metadata.getStatus().name() + "</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<th>Alertas:</th>");
            out.println("<td class=\"center\"></td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<th>Detalhes:</th>");
            out.println("<td class=\"center\">"
                    + "<div class=\"btn-group\">"
                    + "<a class=\"btn btn-xs\"><i class=\"icon20 i-storage\"></i> Storage</a>"
                    + "<a class=\"btn btn-xs\"><i class=\"icon20 i-cogs\"></i> Hardware</a>"
                    + "<a class=\"btn btn-xs\"><i class=\"icon20 i-health\"></i> Desempenho</a>"
                    + "<a class=\"btn btn-xs\"><i class=\"icon20 i-bell-2\"></i> Alertas</a>"
                    + "</div>"
                    + "</td>");
            out.println("</tr>");

            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");

        }

        out.println("</div>");
        out.println("</section>");
        out.println("</div>");

        RequestDispatcher rd4 = request.getRequestDispatcher("footer.jsp");
        rd4.include(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
