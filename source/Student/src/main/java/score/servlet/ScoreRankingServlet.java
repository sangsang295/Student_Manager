package score.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Score;
import entity.ScoreRankingInfo;
import entity.Subject;
import entity.Teacher;
import impl.ScoreImpl;
import impl.TeacherImpl;
import net.sf.json.JSONSerializer;

/**
 * 
 * 该类用于获取学生的成绩排名数据
 * 
 */
public class ScoreRankingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ScoreRankingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String scoId = request.getParameter("scoId");
		ScoreRankingInfo info = new ScoreRankingInfo();
		// 根据成绩ID查询成绩
		ScoreImpl si = new ScoreImpl();
		Score score = si.query("sco_id", scoId).parallelStream().findFirst().get();
		// 由该成绩获得其对应的课程
		Subject subject = score.getSubject();
		// 获得课程名称
		info.setSubjectName(subject.getName());
		// 由课程名称获取任课教师
		TeacherImpl ti = new TeacherImpl();
		Teacher teacher = ti.query("tec_major", subject.getName()).parallelStream().findFirst().get();
		info.setTeacherName(teacher.getName());
		// 查询同课程的所有成绩
		ScoreImpl sci = new ScoreImpl();
		List<Score> scores = sci.query("sub_id", "" + subject.getId());
		// 计算该课总分最大成绩
		double maxScore = scores.parallelStream().mapToDouble((e) -> e.getCount()).max().getAsDouble();
		info.setMaxScore(maxScore);
		// 计算该课总分平均成绩
		double avgScore = scores.parallelStream().mapToDouble((e) -> e.getCount()).average().getAsDouble();
		info.setAvgScore(avgScore);
		// 计算该课总分最低成绩
		double minScore = scores.parallelStream().mapToDouble((e) -> e.getCount()).min().getAsDouble();
		info.setMinScore(minScore);
		// 计算学生总人数（已登入成绩）
		long studentNum = scores.parallelStream().count();
		info.setStudentsNum(studentNum);
		// 计算该学生的排名
		double scoreCount = score.getCount();
		long rank = scores.parallelStream().mapToDouble((e) -> e.getCount()).filter(s -> s > scoreCount).count() + 1;
		info.setRank(rank);
		// 计算该学生的排名占比
		double rankRate = (double) rank / (double) studentNum;
		info.setRankRate(rankRate);
		// 回传
		response.getWriter().write(JSONSerializer.toJSON(info).toString());
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
