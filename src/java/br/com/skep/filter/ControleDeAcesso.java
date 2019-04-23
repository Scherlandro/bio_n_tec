package br.com.skep.filter;

import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(servletNames = {"Faces Servlet"})
public class ControleDeAcesso implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        System.out.println("filtrando.....");
        if ((session.getAttribute("USUARIOLogado") != null)
                || (req.getRequestURI().endsWith("radix.jsf"))
                || (req.getRequestURI().endsWith("login.jsf"))
                || (req.getRequestURI().contains("javax.faces.resource/"))) {

            chain.doFilter(request, response);
            
            
            String url = req.getRequestURL().toString();
            System.out.print(url);
          //  if (!req.getRequestURI().startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) { // Skip JSF resources (CSS/JS/Images/etc)
            if (url.equals("radix.jsf")) { // Skip JSF resources (CSS/JS/Images/etc)
                System.out.print(url);
                res.reset();
                res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                res.addHeader("Pragma", "no-cache"); // HTTP 1.0.
                res.setDateHeader("Expires", 0); // Proxies.
            }
        } else {
            System.out.println("erro no filtro.");
            redireciona("radix.jsf", response);
        }

    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }

    private void redireciona(String url, ServletResponse response)
            throws IOException {
        HttpServletResponse res = (HttpServletResponse) response;
        res.sendRedirect(url);
    }
}
