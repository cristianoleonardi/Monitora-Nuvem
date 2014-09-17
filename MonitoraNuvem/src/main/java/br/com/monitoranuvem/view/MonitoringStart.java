/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.monitoranuvem.view;

import br.com.monitoranuvem.connection.ConectionCloud;
import br.com.monitoranuvem.controller.ProviderDialogControl;
import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ViewProperties;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jclouds.compute.ComputeService;
import org.jclouds.compute.domain.ComputeMetadata;
import org.jclouds.compute.domain.Hardware;
import org.jclouds.compute.domain.NodeMetadata;

/**
 *
 * @author Cristiano
 */
public class MonitoringStart extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Credenciais de acesso
        ViewProperties prop = new ViewProperties();

        //Efetua a conexao com o provedor
        ConectionCloud con = new ConectionCloud();

        //Instancia de Compute
        ComputeService compute = null;

        //Instancia de Hardware
        Hardware hardware = null;

        if (con.conection(Provider.AMAZON, prop.getAcessKey(), prop.getSecretKey())) {
            ProviderDialogControl pdc = new ProviderDialogControl();
            compute = pdc.getListCServ();
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet MonitoringStart</title>");
        out.println("</head>");
        out.println("<body>");
        for (ComputeMetadata node : compute.listNodes()) {
            NodeMetadata metadata = compute.getNodeMetadata(node.getId());
            out.println("<h1>ID da Instancia: " + metadata.getId() + "</h1>");
            out.println("<ul>");
            out.println("<li>Tipo de Instância: " + metadata.getType().name() + "</li>");
            out.println("<li>Nome da Instancia: " + metadata.getName() + "</li>");
            out.println("<li>Localização: " + metadata.getLocation().getDescription() + "</li>");
            out.println("<li>Status da Instância: " + metadata.getBackendStatus() + "</li>");
            out.println("<li>Grupo: " + metadata.getGroup() + "</li>");
            out.println("<li>HOST: " + metadata.getHostname() + "</li>");
            out.println("<li>IP Privado: " + metadata.getPrivateAddresses() + "</li>");
            out.println("<li>IP Público: " + metadata.getPublicAddresses() + "</li>");
            out.println("<li>Provedor: " + metadata.getProviderId() + "</li>");
            out.println("<li>Sistema Operacional: " + metadata.getOperatingSystem().getDescription() + "</li>");
            out.println("<li>Arquitetura S.O.: " + metadata.getOperatingSystem().getArch() + "</li>");
            out.println("<li>Status do que?: " + metadata.getStatus() + "</li>");

            out.println("</ul>");
            

            out.println("<h2>Hardware</h2>");
            //hardware = metadata.getHardware();
            //out.println("<p>Teste</p>" + hardware.getRam());
            
            out.println("</ul>");
            //out.println("<li>Hardware: " + metadata.getHardware().getRam() + "</li>");
            out.println("</ul>");
        }
        out.println("<a href=\"index.html\">Menu Principal</a>");
        out.println("</body>");
        out.println("</html>");
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
