实现功能:
-----
1. 为教务员（角色：管理员）开发统计各门课程程序平均分、各分段人数统计、绘制成绩分布图的功能。

* 功能位置：
a. 使用账户：（admin，admin）登录系统。
b. 在左侧菜单栏中选择“成绩管理 -> 成绩统计”功能。
c. 在右侧页面的下拉框中选择课程。
d. 此时，页面中将以图表的形式显示该门课程的详细统计信息，内容包括：分数段的人数分布图、及格率饼图、课程人数（已登分）、平均分、最高分、最低分等信息。

* 功能实现：
a. 前端
html : webapp/pages/statistics_score.html
css : webapp/css/statistic.css
js : webapp/js/statistic.js
使用框架 Chart.js (https://github.com/chartjs/Chart.js) 生成图表。
使用纯Ajax技术与后端实现交互。
b. 后端
POJO : entity.SubjectStatisticInfo
Servlet : subject.servlet.StatisticSubjectServlet, subject.servlet.StatisticGetAllSubjectServlet
------
2. 为学生开发查看成绩排名。

* 功能位置：
a. 使用账户：（stu00~stu19，000000）登录系统。
b. 在左侧菜单栏中选择“成绩管理 -> 查询成绩”功能。
c. 点击上方的“查询”按钮。
d. 在成绩列表中选择一列成绩，并点击后方编辑栏的“排名”按钮。
e. 在排名页面上可以看到有关自己本成绩的排名信息，内容包括：该课程的学生人数、平均分、最高分、平均分、自己的排名、排名占比等信息。
（设计说明：为何不将排名信息直接显示在成绩列表中？这是因为排名信息属于较为敏感的信息，学生可能不希望他人碰巧看到，因此必须由学生确认查看后方可在专属页面中显示。）

* 功能实现：
a. 前端
html : webapp/pages/score_ranking.html
js : webapp/js/socre_ranking.js
使用纯Ajax技术与后端实现交互。
b. 后端
POJO : entity.ScoreRankingInfo
Servlet : score.servlet.ScoreRankingServlet
------
3. 为学生开发给教师评分的功能。

* 功能位置：
a. 使用账户：（stu00~stu19，000000）登录系统。
b. 在左侧菜单栏中选择“成绩管理 -> 查询成绩”功能。
c. 点击上方的“查询”按钮。
d. 在成绩列表中选择一列成绩，并点击后方编辑栏的“反馈”按钮。
e. 在反馈页面上可以对该门课程的授课质量进行反馈，评价指标包括语言表达、课程设计、课堂气氛、作业评改和课堂管理，选择完毕后点击“提交”即可提交该反馈。
f. 每个课程仅能提交一次反馈，一旦提交则不可修改。如果已经提交了反馈，再进入该页面会显示之前反馈的内容。

* 功能实现：
a.前端
html : webapp/pages/subject_feedback.html
js : webapp/js/subject_feedback.js
b. 后端
Servlet : subject.servlet.SubjectFeedbackServlet, subject.servlet.GetSubjectFeedbackServlet
Interface : dao.IFeedback
Class : impl.FeedbackImpl
POJO : entity.Feedback
c. 数据库
表 : feedback
------
4. 为教务员（角色：管理员）开发统计课程反馈结果的功能。

* 功能位置：
a. 使用账户：（admin，admin）登录系统。
b. 在左侧菜单栏中选择“课程管理 -> 反馈结果”功能。
c. 在右侧页面的下拉框中选择课程。
d. 在反馈结果页面上可以看到该门课程的5种授课质量评价指标雷达图以及反馈率的环装图，在下方的表格中还可查看该课程的课程名称、任课教师、学生人数等信息。

* 功能实现：
a.前端
html : webapp/pages/subject_feedback.html
js : webapp/js/statistic_feedback.js
b. 后端
Servlet : subject.servlet.StatisticFeedback
POJO : entity.FeedbackInfo
------
