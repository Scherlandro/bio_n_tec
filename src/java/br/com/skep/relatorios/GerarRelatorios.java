package br.com.skep.relatorios;

import br.com.skep.callBD.AcessoDB;
import br.com.skep.entity.Produto;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import net.sf.jasperreports.engine.JasperFillManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.IntStream.builder;
import static java.util.stream.LongStream.builder;
import static java.util.stream.Stream.builder;
import javax.faces.FacesException;
import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.primefaces.component.api.DynamicColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.util.ComponentUtils;
import org.primefaces.util.Constants;

public class GerarRelatorios {

    AcessoDB conectBD = new AcessoDB();
    private HttpServletResponse response;
    private FacesContext context;
    private ByteArrayOutputStream baos;
    private InputStream stream;
    private Connection con;

    public GerarRelatorios() {
        this.context = FacesContext.getCurrentInstance();
        this.response = (HttpServletResponse) context.getExternalContext().getResponse();
    }

     public void getRelatorio() {
        /*
        HashMap parametro = new HashMap();
        String arquivoJasper = "C:\\SkepAutorizada\\dist\\GerarRelatorios\\fatVendas.jasper";
        parametro.put("vendas.id_venda", cod);
        JasperPrint print;
        */
        stream = this.getClass().getResourceAsStream("C:/Bionaturatec/relatorios/fatVendas.jasper");
        Map<String, Object> params = new HashMap<String, Object>();
        baos = new ByteArrayOutputStream();
        try {
            JasperReport report = (JasperReport) JRLoader.loadObject(stream);
            /*Para usar JavaBeanDataSource defina: new JRBeanCollectionDataSource(lista)
            mude a string do getResourceAsStream("/report/reportPessoaJavaBeanDS.jasper")
             */
            JasperPrint print = JasperFillManager.fillReport(report, params, getConexao());
            JasperExportManager.exportReportToPdfStream(print, baos);
            response.reset();
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            response.setHeader("Content-disposition", "inline; filename=relatorio.pdf");
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();

            context.responseComplete();
            fecharConexao();

            /*
            print = JasperFillManager.fillReport(arquivoJasper, parametro, conectBD.getConection());
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setVisible(true);
             */
        } catch (JRException ex) {
            Logger.getLogger(GerarRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerarRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printFatDeVenda(int cod) {
       /*
        Map parameters = new HashMap();
        String arquivoJasper = "C:\\SkepAutorizada\\dist\\GerarRelatorioss\\fatVendas.jasper";
        parameters.put("vendas.id_venda", cod);
        */
        stream = this.getClass().getResourceAsStream("/br.com.skep.relatorios/fatVendas.jasper");
        Map<String, Object> params = new HashMap<String, Object>();
        baos = new ByteArrayOutputStream();
        try {
           // JasperPrintManager.printPage(JasperFillManager.fillReportToFile(arquivoJasper, parameters, conectBD.getConection()), 0, true);
             JasperReport report = (JasperReport) JRLoader.loadObject(stream);
            JasperPrint print = JasperFillManager.fillReport(report, params, getConexao());
            JasperExportManager.exportReportToPdfStream(print, baos);
            response.reset();
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            response.setHeader("Content-disposition", "inline; filename=relatorio.pdf");
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();

            context.responseComplete();
            fecharConexao(); 
             
         } catch (JRException ex) {
            Logger.getLogger(GerarRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerarRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    public void printVendasDiretoParaImpressora() {

        PrintService[] printService = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.AUTOSENSE, null);
        System.out.println("Quantas impressoras.: " + printService.length);
        PrintService impressoraPadrao = PrintServiceLookup.lookupDefaultPrintService();
        System.out.println("A impressora Padrão é a " + impressoraPadrao.getName());
        DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        HashDocAttributeSet hashDocAttributeSet = new HashDocAttributeSet();
        //   String arquivoJasper ="C:\\SkepAutorizada\\dist\\GerarRelatorioss\\fatVendas.jasper";
        String arquivoJasper = "C:\\SkepAutorizada\\dist\\GerarRelatorioss\\fatVendas.txt";
        //    JasperPrint print;
        try {
            FileInputStream fileInputStream = new FileInputStream(arquivoJasper);
            Doc doc = new SimpleDoc(fileInputStream, docFlavor, hashDocAttributeSet);
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
            PrintService printSv = ServiceUI.printDialog(null, 300, 200, printService, impressoraPadrao, docFlavor, printRequestAttributeSet);
            if (printSv != null) {
                DocPrintJob docPrintJob = printSv.createPrintJob();
                try {
                    docPrintJob.print(doc, printRequestAttributeSet);
                } catch (PrintException e) {
                    Logger.getLogger(GerarRelatorios.class.getName()).log(Level.SEVERE, null, e);
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            /*
            print = JasperFillManager.fillReport(arquivoJasper, null, mysql.conectar());
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setVisible(true);
            viewer.toFront();
             */
        } catch (FileNotFoundException ex) {//JRException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    //-----------------------------------------------------------------
    /*
    defina um parametro: List<Objeto> lista, se usar JavaBean DataSource
     */
    public void getGerarRelatorios() {
        stream = this.getClass().getResourceAsStream("/report/reportPessoaBD.jasper");
        Map<String, Object> params = new HashMap<String, Object>();
        baos = new ByteArrayOutputStream();

        try {

            JasperReport report = (JasperReport) JRLoader.loadObject(stream);

            /*Para usar JavaBeanDataSource defina: new JRBeanCollectionDataSource(lista)
            mude a string do getResourceAsStream("/report/reportPessoaJavaBeanDS.jasper")
             */
            JasperPrint print = JasperFillManager.fillReport(report, params, getConexao());
            JasperExportManager.exportReportToPdfStream(print, baos);

            response.reset();
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            response.setHeader("Content-disposition", "inline; filename=GerarRelatorios.pdf");
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            response.getOutputStream().close();

            context.responseComplete();
            fecharConexao();

        } catch (JRException ex) {
            Logger.getLogger(GerarRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerarRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pessoa", "root", "12345");
            return con;

        } catch (SQLException ex) {
            Logger.getLogger(GerarRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GerarRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }

    public void fecharConexao() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(GerarRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
}
