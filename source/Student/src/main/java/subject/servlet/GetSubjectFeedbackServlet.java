package subject.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IFeedback;
import entity.Feedback;
import entity.Operator;
import impl.FeedbackImpl;
import net.sf.json.JSONSerializer;

/**
 * 
 * 该类用于查询指定课程成绩是否进行了反馈，如果有，返回其JSON数据
 * 
 */
public class GetSubjectFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetSubjectFeedbackServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Operator operator = (Operator) request.getSession().getAttribute("log_operator");
		Feedback fb = null;
		if (operator.getRole().getId() == 3) {
			String scoId = request.getParameter("scoId");
			IFeedback fi = new FeedbackImpl();
			fb = fi.queryByScoId(scoId);
		}
		response.getWriter().write(JSONSerializer.toJSON(fb).toString());
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
