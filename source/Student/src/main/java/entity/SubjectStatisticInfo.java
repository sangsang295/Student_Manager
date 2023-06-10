package entity;

import java.util.List;

/**
 * 
 * 包装课程统计信息
 *
 */
public class SubjectStatisticInfo {

	private Subject subject;
	private Teacher teacher;
	private long studentNum;
	private double maxScore;
	private double minScore;
	private double avgScore;
	private List<Long> scoreDistribution;
	private List<String> scoreLabels;
	private List<Long> passDistribution;

	public List<String> getScoreLabels() {
		return scoreLabels;
	}

	public void setScoreLabels(List<String> scoreLabels) {
		this.scoreLabels = scoreLabels;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public long getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(long studentNum) {
		this.studentNum = studentNum;
	}

	public double getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}

	public double getMinScore() {
		return minScore;
	}

	public void setMinScore(double minScore) {
		this.minScore = minScore;
	}

	public double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	public List<Long> getScoreDistribution() {
		return scoreDistribution;
	}

	public void setScoreDistribution(List<Long> scoreDistribution) {
		this.scoreDistribution = scoreDistribution;
	}

	public List<Long> getPassDistribution() {
		return passDistribution;
	}

	public void setPassDistribution(List<Long> passDistribution) {
		this.passDistribution = passDistribution;
	}

}
