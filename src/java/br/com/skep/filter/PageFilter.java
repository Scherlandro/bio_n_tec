package br.com.skep.filter;

import br.com.skep.beans.LoginBean;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

public class PageFilter implements Filter {

    @Inject
    private LoginBean loginBean;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        /*
        recuperação da Sessão atual ou a criação de uma nova caso não exista
         */
        HttpServletRequest req = (HttpServletRequest) request;
        //HttpSession req = ((HttpServletRequest) request).getSession(true);
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURL().toString();
        System.out.print(url);
        /*Se o usuario estiver navegando em uma página restrita 
        e não tiver logado, será redirecionado para pagina radix.jsf        
        */
        if (url.contains("/restricted") && loginBean.getNome() == null) {
            res.sendRedirect(req.getServletContext().getContextPath() + "/login.jsf");
        } else {
       //permitirá que o usuário prossiga com o seu fluxo de navegação:
        chain.doFilter(request, response);
        }
        /*
       capturar a sessão como um HttpSession.
       Depois captura a página atual que está sendo acessada:
         */
        // String newCurrentPage = ((HttpServletRequest) request).getServletPath();

        /*
       verifica se não existe uma página atual gravada na sessão,
       caso seja verdade então é o primeiro acesso do usuário.
       Então é gravado a última página e a página atual como sendo as mesmas:
       
       if (sess.getAttribute("currentPage") == null) {
           sess.setAttribute("lastPage", newCurrentPage);
           sess.setAttribute("currentPage", newCurrentPage);
       } else {
  
           String oldCurrentPage = sess.getAttribute("currentPage").toString();
           if (!oldCurrentPage.equals(newCurrentPage)) {
             sess.setAttribute("lastPage", oldCurrentPage);
             sess.setAttribute("currentPage", newCurrentPage);
           }
       }
         */
 /*
       Método que permitirá que o usuário prossiga com o seu fluxo de navegação:
         */
      //  chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
}
