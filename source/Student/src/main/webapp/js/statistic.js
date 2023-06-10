/**
 * 
 */
var initPage = false;
var numChart;
var passChart;

$(function() {
	// 初始化页面：获取全部课程列表，并生成最上方的下拉菜单（每个元素一个课程）
	$.ajax({
		url: '/Student/StatisticGetAllSubjectServlet',
		type: 'post',
		data: {},
		success: function(result) {
			var subjects = eval("(" + result + ")");
			$('#select_subject').html('');
			for (let s of subjects) {
				$('#select_subject').append('<option value="' + s.id + '">' + s.name + '</option>');
				if (!initPage) {
					showPage(s.id);
					initPage = true;
				}
			}
			$('#select_subject').change(function(e) {
				var selectedOption = $("#select_subject option:selected");
				var subId = selectedOption.val();
				showPage(subId);
			});
		},
		error: function() {
			sendAlter();
		}
	});
});

function showPage(subId) {
	// 切换为加载状态
	$('body').addClass('loading');
	// 根据选取的课程ID号，在页面上显示相应的统计信息。
	$.ajax({
		url: '/Student/StatisticSubjectServlet',
		type: 'post',
		data: {
			subId: subId
		},
		success: function(result) {
			// 结束加载状态
			$('body').removeClass('loading');
			// 解析返回数据
			var info = eval("(" + result + ")");
			// 生成成绩统计表
			$('#subject_name').text(info.subject.name);
			$('#avg_score').text(info.avgScore.toFixed(1));
			$('#max_score').text(info.maxScore.toFixed(1));
			$('#min_score').text(info.minScore.toFixed(1));
			$('#student_num').text(info.studentNum);
			$('#subject_teacher').text(info.teacher.name);
			// 生成成绩分布图
			var scoreLabels = info.scoreLabels;
			var scoreDistribution = info.scoreDistribution;
			if (numChart == null) {
				const nc = document.getElementById('numChart');
				numChart = new Chart(nc,
					{
						type: 'bar',
						data: {
							labels: scoreLabels,
							datasets: [{
								label: '人数',
								data: scoreDistribution,
								borderWidth: 1
							}]
						},
						options: {
							scales: {
								y: {
									beginAtZero: true
								}
							}
						}
					});
			} else {
				numChart.data.labels = scoreLabels;
				numChart.data.datasets[0].data = scoreDistribution;
				numChart.update();
			}
			// 生成及格率饼图
			var passData = info.passDistribution;
			if (passChart == null) {
				const pc = document.getElementById('passChart');
				passChart = new Chart(pc,
					{
						type: 'pie',
						data: {
							labels: ['及格', '不及格'],
							datasets: [{
								label: '比率',
								data: passData,
								backgroundColor: [
									'rgb(54, 162, 235)',
									'rgb(255, 99, 132)'
								],
								hoverOffset: 4
							}]
						}
					});
			} else {
				passChart.data.datasets[0].data = passData;
				passChart.update();
			}
		},
		error: function() {
			sendAlter();
		}
	});
}

function sendAlter() {
	$('body').removeClass('loading');
	alert('错误：无法连接到服务器，请检查网络并刷新页面重试。');
}