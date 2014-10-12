package br.com.monitoranuvem.view;

import br.com.monitoranuvem.controller.DashboardControl;
import br.com.monitoranuvem.controller.ProviderHistoryControl;
import br.com.monitoranuvem.controller.ProviderInstanceControl;
import br.com.monitoranuvem.model.QtdStatusProvider;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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
            throws ServletException, IOException, ClassNotFoundException, SQLException, ParseException {

        //Instancia a sessão para manipular as variáveis de sessao
        HttpSession session = request.getSession(true);

        //Lista de cores para utilização nos gráficos
        String[] chartColours = new String[]{"#62aeef", "#d8605f", "#72c380", "#6f7a8a", "#f7cb38", "#5a8022", "#2c7282"};

        //Acessa o controlador de instâncias
        ProviderInstanceControl pic = new ProviderInstanceControl();

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
            dadosGrafico += "{label: \"" + sp.getProvider().getNome() + "\", data: " + percentual + ", color : \"" + chartColours[i] + "\"}";
            if (i < listaActiveInstanceProvider.size() - 1) {
                dadosGrafico += ";";
            }
        }
        //Envia dados para grágico (Instâncias Ativas por Provedor)
        session.setAttribute("listaActiveInstanceProvider", dadosGrafico);

        //Solicita a quantidade de instancias por provedor
        ArrayList<QtdStatusProvider> listaStatusProvider = pic.listaQDTStatusProvider();

        //Zera os dados da variavel dadosGrafico
        dadosGrafico = "";

        //Variavel que armazena nomes do gráfico de barras
        Set label = new HashSet();
        String labels = "";
        labels += "[";

        //Monta dados para grágico (Total de Instâncias por Provedor por Status)
        Set<String> status = new HashSet<>();
        for (QtdStatusProvider sp : listaStatusProvider) {
            if (status.add(sp.getStatus())) {
                //Solicita a quantidade de instancias ativas por provedor status
                ArrayList<QtdStatusProvider> listaStatusInstanceProvider = pic.listaQDTStatusProvider(sp.getStatus());

                dadosGrafico += "{label: \"" + sp.getStatus() + "\", data: [";

                for (int i = 0; i < listaStatusInstanceProvider.size(); i++) {
                    QtdStatusProvider spStatus = listaStatusInstanceProvider.get(i);

                    //Define os labels
                    if (label.add(spStatus.getProvider().getNome())) {
                        if (label.size() > 1) labels += ",";
                        labels += "[" + (label.size() - 1) + ", \"" + spStatus.getProvider().getNome() + "\"]";
                    }

                    //dadosGrafico += "\"" + spStatus.getProvider().getNome() + "\": " + spStatus.getQuantidade();
                    dadosGrafico += "[" + i + ", " + spStatus.getQuantidade() + "]";

                    if (i < listaStatusInstanceProvider.size() - 1) {
                        dadosGrafico += ",";
                    } else if (i == listaStatusInstanceProvider.size() - 1) {
                        dadosGrafico += "]};";
                    }
                }
            }
        }
        labels += "]";
        //Envia dados para grágico (Total de Instâncias por Provedor por Status)
        session.setAttribute("labels", labels);
        session.setAttribute("listaStatusInstanceProvider", dadosGrafico);

        //Monta dados para grágico (Total de Instâncias por Provedor por Status)
        ProviderHistoryControl phc = new ProviderHistoryControl();
        String historyLastThirtyDays = phc.historyLastThirtyDays();
        long[] days = phc.getFirstLastDay();
        session.setAttribute("historyLastThirtyDays", historyLastThirtyDays);
        session.setAttribute("firstDay", days[0]);
        session.setAttribute("lastDay", days[1]);
        
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
        } catch (ParseException ex) {
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
        } catch (ParseException ex) {
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
