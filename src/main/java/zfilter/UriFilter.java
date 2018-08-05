package zfilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = { "/*" }, initParams = {
		@WebInitParam(name = "prefix", value = " URI: ") })
public class UriFilter implements Filter
{
	private String prefix;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		prefix = filterConfig.getInitParameter("prefix");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException
	{
		HttpServletRequest request0 = (HttpServletRequest) request;
		System.out.println(prefix + request0.getRequestURI());

		// 传递至下一个 filter
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy()
	{

	}

}
