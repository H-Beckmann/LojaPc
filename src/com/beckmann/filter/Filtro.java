package com.beckmann.filter;

import java.io.IOException;

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

import com.beckmann.model.Usuario;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/faces/*"})
public class Filtro implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

	
//		chain.doFilter(request, response);
//		return;
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;

		String endereco = servletRequest.getRequestURI();
		System.out.println(endereco);
		if (endereco.equals("/LojaPc/faces/login.xhtml") || endereco.equals("/LojaPc/faces/principal.xhtml") ||endereco.equals("/LojaPc/faces/vendapeca.xhtml")) {
			chain.doFilter(request, response);
			return;
		}
		

		HttpSession session = servletRequest.getSession(false);
		
		Usuario usu = null;
		if (session != null)
			usu = (Usuario) session.getAttribute("usuarioLogado");
		
		if (usu == null) {
			((HttpServletResponse) response).sendRedirect("/LojaPc/faces/login.xhtml");
		}  else {

			chain.doFilter(request, response);
			return;
		}
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SecurityFilter Iniciado.");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	

}