package subject.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IFeedback;
import entity.Feedback;
import entity.Operator;
import impl.FeedbackImpl;

/**
 * 
 * 该类用于提交课程反馈
 * 
 */
public class SubjectFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubjectFeedbackServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 检查权限
		Operator operator = (Operator) request.getSession().getAttribute("log_operator");
		if (operator.getRole().getId() == 3) {
			// 获得该反馈对应的课程分数ID（必须有分数的学生才能对该课程进行反馈）
			String scoId = request.getParameter("scoId");
			// 获得各项评分，每项均为0-4的整数值，越大越好
			// 语言表达
			int le = Integer.parseInt(request.getParameter("le"));
			// 课程设计
			int cd = Integer.parseInt(request.getParameter("cd"));
			// 课堂气氛
			int ca = Integer.parseInt(request.getParameter("ca"));
			// 作业评改
			int he = Integer.parseInt(request.getParameter("he"));
			// 课堂管理
			int cm = Integer.parseInt(request.getParameter("cm"));
			// 生成封装对象并插入数据库
			Feedback fb = new Feedback();
			fb.setId(UUID.randomUUID().toString()); // 主键使用UUID，便于未来拆表分布式部署
			fb.setScoId(Integer.parseInt(scoId));
			fb.setLe(le);
			fb.setCd(cd);
			fb.setCa(ca);
			fb.setHe(he);
			fb.setCm(cm);
			IFeedback fi = new FeedbackImpl();
			if (fi.insert(fb)) {
				// 插入成功，回传结果
				response.getWriter().write("SUCCESS");
				return;
			}
		}
		response.getWriter().write("ERROR");
		return;
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
