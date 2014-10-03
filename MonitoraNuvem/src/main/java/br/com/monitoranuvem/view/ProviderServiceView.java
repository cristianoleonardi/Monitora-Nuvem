package br.com.monitoranuvem.view;

import br.com.monitoranuvem.controller.ProviderControl;
import br.com.monitoranuvem.controller.ProviderServiceControl;
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
 * @author Cristiano Leonardi, Márcio Bolzan
 */
public class ProviderServiceView extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
        
        //Instancia do controller do provider
        ProviderServiceControl psc = new ProviderServiceControl();

        //Instancia a sessão para manipular as variáveis de sessao
        HttpSession session = request.getSession(true);

        //Ação que determina o que será executado pelo Controller.
        String action = request.getParameter("action");

        if (action == null || action.equalsIgnoreCase("") || action.equalsIgnoreCase("listarProviderService")) {
            //###LISTAR PROVIDER SERVICE###
            ArrayList<ProviderService> listaPrvServices = psc.listaProviderService();
            
            ArrayList<Provider> listaProvedores = pc.listaProvider();

            session.setAttribute("listaPrvServices", listaPrvServices);
            session.setAttribute("listaProvedores", listaProvedores);

            RequestDispatcher rd = request
                    .getRequestDispatcher("/providerservice.jsp");
            rd.forward(request, response);

        } else if (action.equalsIgnoreCase("criarProviderService")) {
            //###CRIAR PROVIDER SERVICE###
            int provider = Integer.parseInt(request.getParameter("provider"));
            String providerService = request.getParameter("providerservice");
            String endpoint = request.getParameter("endpoint");
            String accessKey = request.getParameter("accesskey");
            String secretAccessKey = request.getParameter("secretaccesskey");

            if (psc.criarProviderService(provider, providerService, endpoint, accessKey, secretAccessKey)) {
                session.setAttribute("responseAction", "Ok");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-checkmark-circle\"></i> Parabéns!</strong> Sua credencial foi cadastrada com sucesso.");

                ArrayList<ProviderService> listaPrvServices = psc.listaProviderService();
                session.setAttribute("listaPrvServices", listaPrvServices);

                RequestDispatcher rd = request
                        .getRequestDispatcher("/providerservice.jsp");
                rd.forward(request, response);
            } else {
                session.setAttribute("responseAction", "Erro");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-close-4\"></i> Erro!</strong> Não foi possível cadastrar esta credencial.");

                RequestDispatcher rd = request
                        .getRequestDispatcher("/providerservice.jsp");
                rd.forward(request, response);
            }

        } else if (action.equalsIgnoreCase("buscarProviderService")) {
            //###BUSCAR PROVIDER SERVICE PARA ATUALIZAR O MESMO###
            int id = Integer.parseInt(request.getParameter("id"));

            ProviderService prvService = new ProviderService();
            prvService = psc.buscaProviderService(id);

            session.setAttribute("prvService", prvService);
            session.setAttribute("action", "atualizarProviderService");

            RequestDispatcher rd = request
                    .getRequestDispatcher("/providerservice.jsp");
            rd.forward(request, response);

        } else if (action.equalsIgnoreCase("atualizarProviderService")) {
            //###ALTERAR PROVIDER SERVICE###
            int id = Integer.parseInt(request.getParameter("id"));
            int provider = Integer.parseInt(request.getParameter("provider"));
            String providerService = request.getParameter("providerservice");
            String endpoint = request.getParameter("endpoint");
            String accessKey = request.getParameter("accesskey");
            String secretAccessKey = request.getParameter("secretaccesskey");

            if (psc.atualizaProvider(id, provider, providerService, endpoint, accessKey, secretAccessKey)) {
                session.setAttribute("responseAction", "Ok");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-checkmark-circle\"></i> Parabéns!</strong> Sua credencial foi atualizada com sucesso.");

                ArrayList<ProviderService> listaPrvServices = psc.listaProviderService();
                session.setAttribute("listaPrvServices", listaPrvServices);
                
                RequestDispatcher rd = request
                        .getRequestDispatcher("/providerservice.jsp");
                rd.forward(request, response);
            } else {
                session.setAttribute("responseAction", "Erro");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-close-4\"></i> Erro!</strong> Não foi possível atualizar esta credencial.");

                RequestDispatcher rd = request
                        .getRequestDispatcher("/providerservice.jsp");
                rd.forward(request, response);
            }

            RequestDispatcher rd = request
                    .getRequestDispatcher("/provider.jsp");
            rd.forward(request, response);

        } else if (action.equalsIgnoreCase("deletarProviderService")) {
            //###DELETAR PROVIDER SERVICE###
            int id = Integer.parseInt(request.getParameter("id"));
            if (psc.deletaProviderServide(id)) {
                session.setAttribute("responseAction", "Ok");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-checkmark-circle\"></i> Parabéns!</strong> Sua credencial foi removida com sucesso.");

                ArrayList<ProviderService> listaPrvServices = psc.listaProviderService();
                session.setAttribute("listaPrvServices", listaPrvServices);
                
                RequestDispatcher rd = request
                        .getRequestDispatcher("/providerservice.jsp");
                rd.forward(request, response);
            } else {
                session.setAttribute("responseAction", "Erro");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-close-4\"></i> Erro!</strong> Não foi possível remover esta credencial.");

                RequestDispatcher rd = request
                        .getRequestDispatcher("/providerservice.jsp");
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
