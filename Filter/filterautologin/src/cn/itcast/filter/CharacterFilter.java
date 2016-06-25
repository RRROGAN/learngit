package cn.itcast.filter;
/*
 * CharacterFilter�����������û�����ʵ��ͳһȫս����Ĺ��ܡ����ǣ���������ʽpost��get�������ķ�ʽ��ͬ
 * Ϊ�ˣ�����ͨ��HttpServletRequestWrapper���HttpServletRequest���а�װ����ͨ����дgetParameter()
 * �ķ�ʽ������get��ʽ�ύ�����ı��롣
 * */
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharacterFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		//�����������󣬽��ȫս��������
		//ָ��request��response�ı���
		request.setCharacterEncoding("utf-8"); //ֻ����Ϣ����Ч
		response.setContentType("text/html;charset=utf-8");
		//�ڷ���ʱ��Ӧ�ø�Ŀ����Դһ��request������Ŀ����Դ����
		CharacterRequest characterRequest = new CharacterRequest(request);
		chain.doFilter(characterRequest, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}

//���request������а�װ
//�̳�Ĭ�ϰ�װ��HttpServletRequestWrapper
class CharacterRequest extends HttpServletRequestWrapper{

	public CharacterRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	//����̳и���һ���ḴдһЩ�������˴�������дgetParameter()����
	public String getParameter(String name){
		//���ñ���װ����getParameter()����������������
		String value = super.getParameter(name);
		if(value==null){
			return null;
		}
		//�ж�����ʽ
		String method = super.getMethod();
		if("get".equalsIgnoreCase(method)){
			try {
				value = new String(value.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//�������󷵻ؽ��
		return value;
	}
	
}
