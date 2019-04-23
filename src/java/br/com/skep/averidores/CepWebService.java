
package br.com.skep.averidores;


import java.net.URL;
import org.dom4j.Document;
import java.util.Iterator;
import org.dom4j.Element;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;



public class CepWebService {
 
    
    private String tipoLogradouroWebServ = "";
    private String logradouroWebServ = "";
    private String bairroWebServ = "";
    private String cidadeWebServ = "";
    private String estadoWebServ = "";
    private int resultadoWebServ = 0;
 
    @SuppressWarnings("rawtypes")
    public CepWebService(String cep) {
 
        try {
            URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
                             
            Document document = getDocumento(url);
 
             Element root = document.getRootElement();
 
            for (Iterator i = root.elementIterator(); i.hasNext();) {
                Element element = (Element) i.next();
 
                if (element.getQualifiedName().equals("tipo_logradouro"))setTipoLogradouroWebServ(element.getText());
                if (element.getQualifiedName().equals("logradouro"))setLogradouroWebServ(element.getText());
                if (element.getQualifiedName().equals("bairro"))setBairroWebServ(element.getText());
                if (element.getQualifiedName().equals("cidade"))setCidadeWebServ(element.getText());
                if (element.getQualifiedName().equals("uf"))setEstadoWebServ(element.getText());
                if (element.getQualifiedName().equals("resultado"))setResultadoWebServ(Integer.parseInt(element.getText()));
 
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 
    public Document getDocumento(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);
         return document;
    }

    public String getTipoLogradouroWebServ() {
        return tipoLogradouroWebServ;
    }

    public void setTipoLogradouroWebServ(String tipoLogradouroWebServ) {
        this.tipoLogradouroWebServ = tipoLogradouroWebServ;
    }

    public String getLogradouroWebServ() {
        return logradouroWebServ;
    }

    public void setLogradouroWebServ(String logradouroWebServ) {
        this.logradouroWebServ = logradouroWebServ;
    }

    public String getBairroWebServ() {
        return bairroWebServ;
    }

    public void setBairroWebServ(String bairroWebServ) {
        this.bairroWebServ = bairroWebServ;
    }

    public String getCidadeWebServ() {
        return cidadeWebServ;
    }

    public void setCidadeWebServ(String cidadeWebServ) {
        this.cidadeWebServ = cidadeWebServ;
    }

    public String getEstadoWebServ() {
        return estadoWebServ;
    }

    public void setEstadoWebServ(String estadoWebServ) {
        this.estadoWebServ = estadoWebServ;
    }

    public int getResultadoWebServ() {
        return resultadoWebServ;
    }

    public void setResultadoWebServ(int resultadoWebServ) {
        this.resultadoWebServ = resultadoWebServ;
    }
 



}