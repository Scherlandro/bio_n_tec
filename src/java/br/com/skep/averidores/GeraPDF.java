/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.skep.averidores;

/**
 *
 * @author Scherlandro
 */
public class GeraPDF {
    /*
    https://pt.stackoverflow.com/questions/70981/atualizar-p%C3%A1gina-apos-download
    
       <h:commandLink actionListener="#{repositorioCobrancaAddMB.clear}" 
                  title="${messages['label.print']}" 
                  rendered="#{repositorioCobrancaAddMB.podeGeraBoleto(bean)}"
                  class="btn btn-white">
             <span class="fa fa-file-pdf-o"/>
             <f:ajax event="click" execute="@this" render="@all" listener="#{repositorioCobrancaAddMB.geraBoleto(bean)}"/>
     </h:commandLink> 
    
    
    
     <h:commandLink action="#{repositorioCobrancaAddMB.geraBoleto(bean)}" 
                    actionListener="#{repositorioCobrancaAddMB.clear}" 
                    title="${messages['label.print']}" 
                    rendered="#{repositorioCobrancaAddMB.podeGeraBoleto(bean)}"
                    class="btn btn-white">
           <span class="fa fa-file-pdf-o"/>
      </h:commandLink> 
    */
     
    /*
    
   public boolean podeGeraBoleto1(Titulo bean) {
        if (bean.getSituacao().isLiquidado()) {
            return false;
        }
        return bean.getPortador().getBanco().isCobranca() && bean.getPortador().getBloquetoEmissao().isCliente();
    }
    
    
    public boolean podeGeraBoleto(Titulo bean) {
        return tituloBC.podeGeraBoleto(bean);
    }

    public String geraBoleto(Titulo bean) {
//        return "titulo_list.jsf";
        TituloTO tituloTO = tituloBC.geraTituloTO(bean);
        if (bean.getNossoNumero() == null || bean.getNossoNumero().isEmpty()) {
            tituloBC.geraNossoNumero(bean, tituloTO);
        } else {
            tituloTO.buildNossoNumeroTO();
        }
        if (tituloTO.getNossoNumeroTO() == null) {
            getMessageContext().add(getResourceBundle().getString("banco.msg.null"), SeverityType.WARN);
            return null;
        }

        byte[] pdf = GeraBoleto.gera(tituloTO);

        this.geraPDF(pdf, bean);

        return null;
    }

    public void geraPDF(byte[] pdf, Titulo bean) {
        if (pdf == null) {
            return;
        }

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + bean.getNumero() + " - " + bean.getParcela() + ".pdf");

        try {
            OutputStream out = response.getOutputStream();
            out.write(pdf);
            out.flush();
            out.close();
        } catch (IOException ex) {
            CriareLog.log(ex);
        }
    }

*/

}
