package subject.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Score;
import entity.Subject;
import entity.SubjectStatisticInfo;
import entity.Teacher;
import impl.ScoreImpl;
import impl.SubjectImpl;
import impl.TeacherImpl;
import net.sf.json.JSONSerializer;

/**
 * 
 * 该类用于获取某一课程的全部统计数据
 * 
 */
public class StatisticSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 设定要划分的分数段，程序将以该分数段为依据统计人数并生成成绩分布柱状图。如果该数组为空，则以及格分数对人数进行分段
	 */
	private static final double[] GRADE = { 60, 70, 80, 90 };

	/**
	 * 设定及格成绩，程序将以该分数为依据统计及格人数并生成饼状图
	 */
	private static final double PASS_SCORE = 60;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StatisticSubjectServlet() {
		super();
	}

	/**
	 * 
	 * 
	 * <h2>根据课程ID返回成绩统计结果</h2>
	 * <p>
	 * 该方法会将课程ID所指定课程的课程名称、任课教师、学生总数、成绩分布等信息以JSON格式返回。
	 * </p>
	 * 
	 * @version 1.0
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String subId = request.getParameter("subId");
		SubjectStatisticInfo info = new SubjectStatisticInfo();
		// 由ID获取课程类
		SubjectImpl si = new SubjectImpl();
		Subject subject = si.query("sub_id", subId).parallelStream().findFirst().get();
		info.setSubject(subject);
		// 查询该课程的所有成绩
		ScoreImpl sci = new ScoreImpl();
		List<Score> scores = sci.query("sub_id", subId);
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
		info.setStudentNum(studentNum);
		// 由课程名称获取任课教师
		TeacherImpl ti = new TeacherImpl();
		Teacher teacher = ti.query("tec_major", subject.getName()).parallelStream().findFirst().get();
		info.setTeacher(teacher);
		// 统计及格和不及格人数
		long passNum = scores.parallelStream().mapToDouble((e) -> e.getCount()).filter(s -> s >= PASS_SCORE).count();
		long dontPassNum = studentNum - passNum;
		List<Long> passDistribution = new ArrayList<>();
		passDistribution.add(passNum);
		passDistribution.add(dontPassNum);
		info.setPassDistribution(passDistribution);
		// 生成成绩分布标签并统计成绩分布
		List<String> scoreLabels = new ArrayList<>();
		List<Long> scoreDistribution = new ArrayList<>();
		if (GRADE.length > 0) {
			// 统计小于最小分数段的人数
			scoreLabels.add("<" + GRADE[0]);
			scoreDistribution
					.add(scores.parallelStream().mapToDouble((e) -> e.getCount()).filter(s -> s < GRADE[0]).count());
			// 统计中间分数段的人数
			for (int i = 1; i < GRADE.length; i++) {
				int c = i;// 当前分数段的上限元素
				int p = i - 1;// 当前分数段的下限元素
				scoreDistribution.add(scores.parallelStream().mapToDouble((e) -> e.getCount())
						.filter(s -> s >= GRADE[p] && s < GRADE[c]).count());
				scoreLabels.add(GRADE[p] + "~" + GRADE[c]);
			}
			// 统计大于最大分数段的人数
			scoreLabels.add("≥" + GRADE[GRADE.length - 1]);
			scoreDistribution.add(scores.parallelStream().mapToDouble((e) -> e.getCount())
					.filter(s -> s >= GRADE[GRADE.length - 1]).count());
		} else {
			// 分段数组为空，默认以及格分数分成两段
			scoreLabels.add("<" + PASS_SCORE);
			scoreLabels.add("≥" + PASS_SCORE);
			scoreDistribution.add(dontPassNum);
			scoreDistribution.add(passNum);
		}
		info.setScoreLabels(scoreLabels);
		info.setScoreDistribution(scoreDistribution);
		response.getWriter().write(JSONSerializer.toJSON(info).toString());
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
