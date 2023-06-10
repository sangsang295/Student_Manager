package subject.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Subject;
import impl.SubjectImpl;
import net.sf.json.JSONSerializer;

/**
 * 
 * 处理成绩统计的请求之查询所有课程（以便生成下拉框）。 statistics_score.html
 * 页面载入后会先试用Ajax调用该请求以获得所有课程，并初始化下拉框。
 *
 */
public class StatisticGetAllSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StatisticGetAllSubjectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获取所有课程并以JSON格式返回
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SubjectImpl subjectImpl = new SubjectImpl();
		List<Subject> subjects = subjectImpl.query("all", "");
		response.getWriter().write(JSONSerializer.toJSON(subjects).toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
