package entity;

/**
 * 
 * 包装成绩排名信息
 *
 */
public class ScoreRankingInfo {

	private String subjectName;
	private String teacherName;
	private long studentsNum;
	private double maxScore;
	private double avgScore;
	private double minScore;

	private long rank;
	private double rankRate;

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

	public double getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}

	public double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	public double getMinScore() {
		return minScore;
	}

	public void setMinScore(double minScore) {
		this.minScore = minScore;
	}

	public long getRank() {
		return rank;
	}

	public void setRank(long rank) {
		this.rank = rank;
	}

	public double getRankRate() {
		return rankRate;
	}

	public void setRankRate(double rankRate) {
		this.rankRate = rankRate;
	}
}
