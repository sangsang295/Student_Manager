/**
 * 
 */

$(function() {
	// 获取分数ID
	$('body').addClass('loading');
	var scoId = getUrlParams('sco_id');
	$.ajax({
		url: '/Student/ScoreRankingServlet',
		type: 'post',
		data: {
			scoId: scoId
		},
		success: function(result) {
			var rankInfo = eval("(" + result + ")");
			$('body').removeClass('loading');
			$('#subject_name').text(rankInfo.subjectName);
			$('#max_score').text(rankInfo.maxScore.toFixed(1));
			$('#subject_teacher').text(rankInfo.teacherName);
			$('#avg_score').text(rankInfo.avgScore.toFixed(1));
			$('#students_num').text(rankInfo.studentsNum);
			$('#min_score').text(rankInfo.minScore.toFixed(1));
			$('#rank').text(rankInfo.rank);
			$('#rank_rate').text((rankInfo.rankRate * 100).toFixed(1) + "%");
		},
		error: function() {
			$('body').removeClass('loading');
			alert("错误：无法连接至服务器，请检查网络后刷新页面重试。");
		}
	});
});

// 工具方法：从URL中获取指定参数
function getUrlParams(key) {
	var url = window.location.search.substr(1);
	if (url == '') {
		return false;
	}
	var paramsArr = url.split('&');
	for (var i = 0; i < paramsArr.length; i++) {
		var combina = paramsArr[i].split("=");
		if (combina[0] == key) {
			return combina[1];
		}
	}
	return false;
}