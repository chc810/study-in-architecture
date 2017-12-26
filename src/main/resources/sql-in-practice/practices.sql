##1、查询"01"课程比"02"课程成绩高的学生的信息及课程分数

SELECT
  a.*,
  b.score AS "01课程",
  c.score AS "02课程"
FROM student a INNER JOIN sc b ON a.s = b.s AND b.c = '01'
  INNER JOIN sc c ON a.s = c.s AND c.c = '02' AND c.score < b.score;

##3、查询平均成绩大于等于60分的同学的学生编号和学生姓名和平均成绩
##注意，select中出现的字段，要么是group by后面的，要么是聚合函数

SELECT
  a.s,
  a.sname,
  avg(b.score) AS "平均分"
FROM student a LEFT JOIN sc b ON a.s = b.s
GROUP BY a.s, a.sname
HAVING avg(b.score) > 60;

##5、查询所有同学的学生编号、学生姓名、选课总数、所有课程的总成绩
SELECT
  a.s,
  a.sname,
  count(b.c)   AS "选课总数",
  sum(b.score) AS "总成绩"
FROM student a LEFT JOIN sc b ON a.s = b.s
GROUP BY a.s, a.sname;

## 6、查询"李"姓老师的数量
SELECT count(t) from teacher where tname LIKE "李%";

##7、查询学过"张三"老师授课的同学的信息
SELECT * from student a inner join sc b on a.s = b.s inner JOIN course c on b.c = c.c INNER join teacher d on c.t = d.t and d.tname = "张三";
