/**
 * 
 */
var initPage = false;
var feedbackChart;
var feedbackRateChart;

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
		url: '/Student/StatisticFeedback',
		type: 'post',
		data: {
			subId: subId
		},
		success: function(result) {
			// 结束加载状态
			$('body').removeClass('loading');
			// 解析返回数据
			console.log(result);
			var info = eval("(" + result + ")");
			// 生成反馈统计表
			$('#subject_name').text(info.subjectName);
			$('#teacher_name').text(info.teacherName);
			$('#student_num').text(info.studentsNum);
			$('#feedback_num').text(info.feedbackNum);
			// 生成反馈雷达图
			var feedbackDistribution = [info.leAvg, info.cdAvg, info.caAvg, info.heAvg, info.cmAvg];
			if (feedbackChart == null) {
				const nc = document.getElementById('feedbackChart');
				feedbackChart = new Chart(nc,
					{
						type: 'radar',
						data: {
							labels: [
								'语言表达',
								'课程设计',
								'课堂气氛',
								'作业评改',
								'课堂管理'
							],
							datasets: [{
								label: '平均打分',
								data: feedbackDistribution,
								fill: true,
								backgroundColor: 'rgba(54, 162, 235, 0.2)',
								borderColor: 'rgb(54, 162, 235)',
								pointBackgroundColor: 'rgb(54, 162, 235)',
								pointBorderColor: '#fff',
								pointHoverBackgroundColor: '#fff',
								pointHoverBorderColor: 'rgb(54, 162, 235)'
							}]
						},
						options: {
							elements: {
								line: {
									borderWidth: 3
								}
							},
							plugins: {
								title: {
									display: true,
									text: '反馈结果雷达图',
								}
							}
						}
					});
			} else {
				feedbackChart.data.datasets[0].data = feedbackDistribution;
				feedbackChart.update();
			}
			var feedbackRate = [info.studentsNum - info.feedbackNum, info.feedbackNum];
			if (feedbackRateChart == null) {
				const nc = document.getElementById('feedbackRateChart');
				feedbackRateChart = new Chart(nc,
					{
						type: 'doughnut',
						data: {
							labels: [
								'未反馈',
								'已反馈'
							],
							datasets: [{
								label: '人数',
								data: feedbackRate,
								backgroundColor: [
									'rgb(255, 99, 132)',
									'rgb(54, 162, 235)'
								],
								hoverOffset: 4
							}]
						},
						options: {
							plugins: {
								title: {
									display: true,
									text: '反馈率统计',
								}
							}
						}
					});
			} else {
				feedbackRateChart.data.datasets[0].data = feedbackRate;
				feedbackRateChart.update();
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