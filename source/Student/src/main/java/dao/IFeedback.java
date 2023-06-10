package dao;

import java.util.List;

import entity.Feedback;

/**
 * 
 * 定义Feedback数据库的操作接口
 *
 */
public interface IFeedback {
	
	boolean insert(Feedback fb);
	
	Feedback queryByScoId(String scoId);
	
	List<Feedback> queryBySubId(String subId);

}
