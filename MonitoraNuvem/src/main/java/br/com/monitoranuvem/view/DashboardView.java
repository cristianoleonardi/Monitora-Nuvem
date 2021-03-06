package br.com.monitoranuvem.view;

import br.com.monitoranuvem.controller.ProviderHistoryControl;
import br.com.monitoranuvem.controller.ProviderInstanceControl;
import br.com.monitoranuvem.model.QtdStatusProvider;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
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
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {

        //Instancia a sessão para manipular as variáveis de sessao
        HttpSession session = request.getSession(true);

        //Montar string XML retorno
        StringBuilder sb = new StringBuilder();

        //Ação que determina o que será executado pelo Controller.
        String graph = request.getParameter("graph");

        //Acessa o controlador de instâncias
        ProviderInstanceControl pic = new ProviderInstanceControl();

        boolean dataGraphAdded = false;

        if (graph.equalsIgnoreCase("activeinstancebyprovider")) {
            dataGraphAdded = false;
            //Lista de cores para utilização nos gráficos
            String[] chartColoursActive = new String[]{"#32CD32", "#228B22", "#008000", "#006400", "#2E8B57", "#3CB371", "#8FBC8F", "#90EE90", "#98FB98"};

            //Solicita a quantidade de instancias ativas (RUNNING) por provedor
            ArrayList<QtdStatusProvider> listaActiveInstanceProvider = pic.listaQDTStatusProvider("RUNNING");

            //Gera a quantidade total
            int qtdTotalInstancias = 0;
            for (QtdStatusProvider sp : listaActiveInstanceProvider) {
                qtdTotalInstancias += sp.getQuantidade();
            }

            String dadosGrafico = "";
            for (int i = 0; i < listaActiveInstanceProvider.size(); i++) {
                QtdStatusProvider sp = listaActiveInstanceProvider.get(i);

                //Calculo de percentual
                int qtdProvider = sp.getQuantidade();
                double percentual = (qtdProvider * 100.00 / qtdTotalInstancias * 100.00) / 100.00;
                percentual = Math.round(percentual * 100.0) / 100.0;

                //Monta dados para grágico (Instâncias Ativas por Provedor)
                dadosGrafico += "{label: \"" + sp.getProvider().getNome() + "\", data: " + percentual + ", color : \"" + chartColoursActive[i] + "\"}";
                if (i < listaActiveInstanceProvider.size() - 1) {
                    dadosGrafico += ";";
                }
            }
            dataGraphAdded = true;
            sb.append("<dataGraph>").append(dadosGrafico).append("</dataGraph>");
        }

        if (graph.equalsIgnoreCase("instancebystatus")) {
            dataGraphAdded = false;
            ArrayList<String> grafico2 = pic.listaQDTStatusProviderDay();

            dataGraphAdded = true;
            sb.append("<dataGraph>");
            sb.append("<dataInfo>").append(grafico2.get(0)).append("</dataInfo>");
            sb.append("<dataLabel>").append(grafico2.get(1)).append("</dataLabel>");
            sb.append("</dataGraph>");
        }

        if (graph.equalsIgnoreCase("historyinstancesbystatus")) {
            dataGraphAdded = false;
            ProviderHistoryControl phc = new ProviderHistoryControl();
            ArrayList<String> grafico3 = phc.montaHistorico(30);

            dataGraphAdded = true;
            sb.append("<dataGraph>");
            sb.append("<firstDay>").append(grafico3.get(0)).append("</firstDay>");
            sb.append("<lastDay>").append(grafico3.get(1)).append("</lastDay>");
            sb.append("<history>").append(grafico3.get(2)).append("</history>");
            sb.append("</dataGraph>");
        }

        if (dataGraphAdded) {
            response.setContentType("text/xml");
            response.setHeader("Cache-Control", "no-cache");
            response.getWriter().write("<dataGraphs>" + sb.toString() + "</dataGraphs>");
        } else {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
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
            throw new ServletException("ClassNotFoundException", ex);
        } catch (SQLException ex) {
            throw new ServletException("SQLException", ex);
        } catch (ParseException ex) {
            throw new ServletException("ParseException", ex);
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
