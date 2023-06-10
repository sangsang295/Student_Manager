/**
 * 
 */

$(function() {
	// 检查是否已经进行过反馈，若有，则显示之前的反馈结果，否则允许提交反馈。
	$('input[type=radio]').prop('disabled', 'true');
	$('#a_submit').hide();
	var scoId = getUrlParams('sco_id');
	$.ajax({
		url: '/Student/GetSubjectFeedbackServlet',
		type: 'post',
		data: {
			scoId: scoId
		},
		success: function(result) {
			var feedback = eval("(" + result + ")");
			if (feedback == null) {
				$('input[type=radio]').removeAttr('disabled');
				$('#a_submit').show();
			} else {
				$('input[name=radio_le][value=' + parseInt(feedback.le) + ']').prop('checked', 'checked');
				$('input[name=radio_cd][value=' + feedback.cd + ']').prop('checked', 'checked');
				$('input[name=radio_ca][value=' + feedback.ca + ']').prop('checked', 'checked');
				$('input[name=radio_he][value=' + feedback.he + ']').prop('checked', 'checked');
				$('input[name=radio_cm][value=' + feedback.cm + ']').prop('checked', 'checked');
			}
		},
		error: function() {
			alert("错误：无法连接至服务器，请检查网络后刷新页面重试。");
		}
	});
});

function submit() {
	if (confirm("该反馈提交后将无法修改，确认提交么？")) {
		var scoId = getUrlParams('sco_id');
		var le = $('input[name=radio_le]:checked').val();
		var cd = $('input[name=radio_cd]:checked').val();
		var ca = $('input[name=radio_ca]:checked').val();
		var he = $('input[name=radio_he]:checked').val();
		var cm = $('input[name=radio_cm]:checked').val();
		$.ajax({
			url: '/Student/SubjectFeedbackServlet',
			type: 'post',
			data: {
				scoId: scoId,
				le: le,
				cd: cd,
				ca: ca,
				he: he,
				cm: cm
			},
			success: function(result) {
				if (result == 'SUCCESS') {
					alert("提交成功！");
					window.location.reload();
				} else {
					alert("提交失败，请重试或联系管理老师。");
				}
			},
			error: function() {
				alert("错误：无法连接至服务器，请检查网络后刷新页面重试。");
			}
		});
	}
}

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