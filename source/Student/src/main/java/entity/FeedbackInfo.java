package entity;

/**
 * 
 * 封装反馈统计信息
 *
 */
public class FeedbackInfo {

	private String subjectName;
	private String teacherName;
	private long studentsNum;
	private long feedbackNum;

	private double leAvg;
	private double cdAvg;
	private double caAvg;
	private double heAvg;
	private double cmAvg;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public long getStudentsNum() {
		return studentsNum;
	}

	public void setStudentsNum(long studentsNum) {
		this.studentsNum = studentsNum;
	}

	public long getFeedbackNum() {
		return feedbackNum;
	}

	public void setFeedbackNum(long feedbackNum) {
		this.feedbackNum = feedbackNum;
	}

	public double getLeAvg() {
		return leAvg;
	}

	public void setLeAvg(double leAvg) {
		this.leAvg = leAvg;
	}

	public double getCdAvg() {
		return cdAvg;
	}

	public void setCdAvg(double cdAvg) {
		this.cdAvg = cdAvg;
	}

	public double getCaAvg() {
		return caAvg;
	}

	public void setCaAvg(double caAvg) {
		this.caAvg = caAvg;
	}

	public double getHeAvg() {
		return heAvg;
	}

	public void setHeAvg(double heAvg) {
		this.heAvg = heAvg;
	}

	public double getCmAvg() {
		return cmAvg;
	}

	public void setCmAvg(double cmAvg) {
		this.cmAvg = cmAvg;
	}

}
