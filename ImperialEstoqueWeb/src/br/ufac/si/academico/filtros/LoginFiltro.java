package br.ufac.si.academico.filtros;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class LoginFiltro implements Filter {

	@Override
	public void destroy() {
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession httpSession = httpRequest.getSession(false);
		
		String loginURI = httpRequest.getContextPath() + "/login.xhtml";
		
		boolean estaLogado = (httpSession != null &&
				httpSession.getAttribute("usuarioLogado") != null);
		boolean acessandoLogin = httpRequest.getRequestURI().equals(loginURI);
		
		if(estaLogado || acessandoLogin) {
			chain.doFilter(request, response);
		}else {
			httpResponse.sendRedirect(loginURI);
		}
		
	}
	
	public void init(FilterConfig arg0) throws ServletException{
		
	}

}
