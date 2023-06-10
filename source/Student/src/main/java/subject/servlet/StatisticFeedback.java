package subject.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IFeedback;
import entity.Feedback;
import entity.FeedbackInfo;
import entity.Operator;
import entity.Score;
import entity.Subject;
import entity.Teacher;
import impl.FeedbackImpl;
import impl.ScoreImpl;
import impl.SubjectImpl;
import impl.TeacherImpl;
import net.sf.json.JSONSerializer;

/**
 * 
 * 
 * 该类用于获得课程反馈的统计结果
 * 
 */
public class StatisticFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StatisticFeedback() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Operator operator = (Operator) request.getSession().getAttribute("log_operator");
		if (operator.getRole().getId() == 1) {
			String subId = request.getParameter("subId");
			// 根据课程ID查询所有与之相关的反馈结果
			IFeedback fi = new FeedbackImpl();
			List<Feedback> fbs = fi.queryBySubId(subId);
			FeedbackInfo fbi = new FeedbackInfo();
			if (fbs.size() > 0) {
				// 如果有反馈结果，计算各类平均分和反馈数目
				fbi.setFeedbackNum(fbs.size());
				double leAvg = fbs.parallelStream().mapToDouble((e) -> e.getLe()).average().getAsDouble();
				fbi.setLeAvg(leAvg);
				double cdAvg = fbs.parallelStream().mapToDouble((e) -> e.getCd()).average().getAsDouble();
				fbi.setCdAvg(cdAvg);
				double caAvg = fbs.parallelStream().mapToDouble((e) -> e.getCa()).average().getAsDouble();
				fbi.setCaAvg(caAvg);
				double heAvg = fbs.parallelStream().mapToDouble((e) -> e.getHe()).average().getAsDouble();
				fbi.setHeAvg(heAvg);
				double cmAvg = fbs.parallelStream().mapToDouble((e) -> e.getCm()).average().getAsDouble();
				fbi.setCmAvg(cmAvg);
			}
			// 查询课程名称、任课教师、学生人数
			SubjectImpl si = new SubjectImpl();
			Subject subject = si.query("sub_id", subId).parallelStream().findFirst().get();
			fbi.setSubjectName(subject.getName());
			TeacherImpl ti = new TeacherImpl();
			Teacher teacher = ti.query("tec_major", subject.getName()).parallelStream().findFirst().get();
			fbi.setTeacherName(teacher.getName());
			ScoreImpl sci = new ScoreImpl();
			List<Score> scores = sci.query("sub_id", subId);
			fbi.setStudentsNum(scores.size());
			// 回传结果
			response.getWriter().write(JSONSerializer.toJSON(fbi).toString());
			return;
		}
		response.getWriter().write(JSONSerializer.toJSON(null).toString());
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
