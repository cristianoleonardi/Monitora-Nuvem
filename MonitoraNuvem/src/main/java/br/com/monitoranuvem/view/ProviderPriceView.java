package br.com.monitoranuvem.view;

import br.com.monitoranuvem.controller.ProviderControl;
import br.com.monitoranuvem.controller.ProviderPriceControl;
import br.com.monitoranuvem.model.InstanceType;
import br.com.monitoranuvem.model.Provider;
import br.com.monitoranuvem.model.ProviderPrice;
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
public class ProviderPriceView extends HttpServlet {

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
        ProviderPriceControl ppc = new ProviderPriceControl();

        //Instancia do controller do provider
        ProviderControl pc = new ProviderControl();

        //Instancia a sessão para manipular as variáveis de sessao
        HttpSession session = request.getSession(true);

        //Ação que determina o que será executado pelo Controller.
        String action = request.getParameter("action");

        if (action == null || action.equalsIgnoreCase("") || action.equalsIgnoreCase("listarProviderPrice")) {
            //###LISTAR PROVIDERPRICE###

            ArrayList<ProviderPrice> listaProviderPrice = ppc.listaProviderPrice();
            session.setAttribute("listaProviderPrice", listaProviderPrice);

            ArrayList<Provider> listaProvider = pc.listaProvider();
            session.setAttribute("listaProvider", listaProvider);

            ArrayList<String> listaInstanceType = new InstanceType().listaTypeInstance();
            session.setAttribute("listaInstanceType", listaInstanceType);

        } else if (action.equalsIgnoreCase("criarProviderPrice")) {
            //###CRIAR PROVIDERPRICE###
            String instanceType = request.getParameter("instancetype");
            Double price = Double.parseDouble(request.getParameter("price"));
            int provider = Integer.parseInt(request.getParameter("provider"));

            if (ppc.criarProviderPrice(instanceType, price, provider)) {
                session.setAttribute("responseAction", "Ok");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-checkmark-circle\"></i> Parabéns!</strong> O preço foi cadastrado com sucesso.");

                ArrayList<ProviderPrice> listaProviderPrice = ppc.listaProviderPrice();
                session.setAttribute("listaProviderPrice", listaProviderPrice);

                ArrayList<Provider> listaProvider = pc.listaProvider();
                session.setAttribute("listaProvider", listaProvider);

                ArrayList<String> listaInstanceType = new InstanceType().listaTypeInstance();
                session.setAttribute("listaInstanceType", listaInstanceType);
            } else {
                session.setAttribute("responseAction", "Erro");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-close-4\"></i> Erro!</strong> Não foi possível cadastrar este preço.");
            }

        } else if (action.equalsIgnoreCase("buscarProviderPrice")) {
            //###BUSCAR PROVIDERPRICE PARA ATUALIZAR O MESMO###
            int id = Integer.parseInt(request.getParameter("id"));

            ProviderPrice providerPrice = ppc.buscaProviderPrice(id);

            session.setAttribute("providerPrice", providerPrice);
            session.setAttribute("action", "atualizarProviderPrice");

            ArrayList<ProviderPrice> listaProviderPrice = ppc.listaProviderPrice();
            session.setAttribute("listaProviderPrice", listaProviderPrice);

            ArrayList<Provider> listaProvider = pc.listaProvider();
            session.setAttribute("listaProvider", listaProvider);

            ArrayList<String> listaInstanceType = new InstanceType().listaTypeInstance();
            session.setAttribute("listaInstanceType", listaInstanceType);

        } else if (action.equalsIgnoreCase("atualizarProviderPrice")) {
            //###ALTERAR PROVIDERPRICE###
            int id = Integer.parseInt(request.getParameter("id"));
            String instanceType = request.getParameter("instancetype");
            Double price = Double.parseDouble(request.getParameter("price"));
            int provider = Integer.parseInt(request.getParameter("provider"));

            if (ppc.atualizaProviderPrice(id, provider, instanceType, price)) {
                session.setAttribute("responseAction", "Ok");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-checkmark-circle\"></i> Parabéns!</strong> O preço foi atualizado com sucesso.");

                ArrayList<ProviderPrice> listaProviderPrice = ppc.listaProviderPrice();
                session.setAttribute("listaProviderPrice", listaProviderPrice);

                ArrayList<Provider> listaProvider = pc.listaProvider();
                session.setAttribute("listaProvider", listaProvider);

                ArrayList<String> listaInstanceType = new InstanceType().listaTypeInstance();
                session.setAttribute("listaInstanceType", listaInstanceType);
            } else {
                session.setAttribute("responseAction", "Erro");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-close-4\"></i> Erro!</strong> Não foi possível atualizar este preço.");
            }

        } else if (action.equalsIgnoreCase("deletarProviderPrice")) {
            //###DELETAR PROVIDERPRICE###
            int id = Integer.parseInt(request.getParameter("id"));

            if (ppc.deletaProviderPrice(id)) {
                session.setAttribute("responseAction", "Ok");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-checkmark-circle\"></i> Parabéns!</strong> O preço foi removido com sucesso.");

                ArrayList<ProviderPrice> listaProviderPrice = ppc.listaProviderPrice();
                session.setAttribute("listaProviderPrice", listaProviderPrice);

                ArrayList<Provider> listaProvider = pc.listaProvider();
                session.setAttribute("listaProvider", listaProvider);

                ArrayList<String> listaInstanceType = new InstanceType().listaTypeInstance();
                session.setAttribute("listaInstanceType", listaInstanceType);
            } else {

                ArrayList<ProviderPrice> listaProviderPrice = ppc.listaProviderPrice();
                session.setAttribute("listaProviderPrice", listaProviderPrice);

                ArrayList<Provider> listaProvider = pc.listaProvider();
                session.setAttribute("listaProvider", listaProvider);

                ArrayList<String> listaInstanceType = new InstanceType().listaTypeInstance();
                session.setAttribute("listaInstanceType", listaInstanceType);

                session.setAttribute("responseAction", "Erro");
                session.setAttribute("responseMsg", "<strong><i class=\"icon24 i-close-4\"></i> Erro!</strong> Não foi possível remover este preço.");
            }
        }

        RequestDispatcher rd = request
                .getRequestDispatcher("/providerprice.jsp");
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
            Logger.getLogger(ProviderPriceView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProviderPriceView.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ProviderPriceView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProviderPriceView.class.getName()).log(Level.SEVERE, null, ex);
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
