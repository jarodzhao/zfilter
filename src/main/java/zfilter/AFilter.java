package zfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = {"/*"})
public class AFilter implements Filter
{

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException
	{
		System.out.print("hello - ");
		filterChain.doFilter(request, response);
		
	}

	@Override
	public void destroy()
	{

	}

}
