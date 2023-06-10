package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IFeedback;
import entity.Feedback;
import util.DB;

/**
 * 
 * 实现Feedback数据库的操作
 *
 */
public class FeedbackImpl implements IFeedback {

	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;

	@Override
	public boolean insert(Feedback fb) {
		// 插入一条新的反馈数据
		try {
			conn = DB.getConn();
			pst = conn.prepareStatement(
					"INSERT INTO `feedback` (`fb_id`, `sco_id`, `fb_le`, `fb_cd`, `fb_ca`, `fb_he`, `fb_cm`) VALUES (?, ?, ?, ?, ?, ?, ?);");
			pst.setString(1, fb.getId());
			pst.setInt(2, fb.getScoId());
			pst.setInt(3, fb.getLe());
			pst.setInt(4, fb.getCd());
			pst.setInt(5, fb.getCa());
			pst.setInt(6, fb.getHe());
			pst.setInt(7, fb.getCm());
			if (pst.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pst, rs);
		}
		return false;
	}

	@Override
	public Feedback queryByScoId(String scoId) {
		// 查询某个课程成绩是否已经进行了反馈，如果有则返回该条目对象，否则返回null
		conn = DB.getConn();
		try {
			pst = conn.prepareStatement("SELECT * FROM `feedback` where sco_id = ?");
			pst.setInt(1, Integer.parseInt(scoId));
			rs = pst.executeQuery();
			// 理论上只有一条结果
			List<Feedback> fbs = saveToPOJO(rs);
			if (fbs.size() > 0) {
				return fbs.parallelStream().findFirst().get();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pst, rs);
		}
		return null;
	}

	private List<Feedback> saveToPOJO(ResultSet rs) {
		List<Feedback> fbs = new ArrayList<>();
		// 将查询结果封装为对象
		try {
			while (rs.next()) {
				Feedback fb = new Feedback();
				fb.setId(rs.getString(1));
				fb.setScoId(rs.getInt(2));
				fb.setLe(rs.getInt(3));
				fb.setCd(rs.getInt(4));
				fb.setCa(rs.getInt(5));
				fb.setHe(rs.getInt(6));
				fb.setCm(rs.getInt(7));
				fbs.add(fb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fbs;
	}

	@Override
	public List<Feedback> queryBySubId(String subId) {
		// 查询某个课程成绩是否已经进行了反馈，如果有则返回该条目对象，否则返回null
		conn = DB.getConn();
		try {
			pst = conn.prepareStatement(
					"SELECT * FROM `feedback` where sco_id in (select sco_id from `score` where sub_id = ?)");
			pst.setInt(1, Integer.parseInt(subId));
			rs = pst.executeQuery();
			// 理论上只有一条结果
			return saveToPOJO(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pst, rs);
		}
		return new ArrayList<>();
	}
}
