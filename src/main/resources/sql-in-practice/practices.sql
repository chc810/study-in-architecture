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
SELECT *
FROM student a INNER JOIN sc b ON a.s = b.s
  INNER JOIN course c ON b.c = c.c
  INNER JOIN teacher d ON c.t = d.t AND d.tname = "张三";

##8、查询没学过"张三"老师授课的同学的信息
##注意，此处使用not exists时，前面select的条件只能在后面where中，不能在on中
SELECT *
FROM student a
WHERE NOT exists(SELECT *
                 FROM teacher aa INNER
                   JOIN course bb ON aa.t = bb.t
                   INNER JOIN sc cc ON bb.c = cc.c
                 where aa.tname = "张三" AND a.s = cc.s);

##9、查询学过编号为"01"并且也学过编号为"02"的课程的同学的信息

SELECT *
FROM student a INNER JOIN sc b ON a.s = b.s AND b.c = "01"
  INNER JOIN sc c ON a.s = c.s AND c.c = "02";

##10、查询学过编号为"01"但是没有学过编号为"02"的课程的同学的信息

SELECT *
FROM student a INNER JOIN sc b ON a.s = b.s AND b.c = "01"
WHERE NOT exists(SELECT *
                 FROM sc c
                 WHERE a.s = c.s AND c.c = "02");

##下面的方法使用了left join的特性
select *
from student a
  left join sc b
    on a.s=b.s and b.c='01'
  left join sc c
    on a.s=c.s and c.c='02'
where b.c='01' and c.c is null;

##20、查询学生的总成绩并进行排名
select a.s, a.sname, sum(b.score) from student a LEFT JOIN sc b on a.s = b.s
GROUP BY a.s, a.sname ORDER BY sum(b.score) desc;

##下面注意 count(Null) 为0;  计算时全部忽略null项;对于avg(null),max(null),min(null),sum(null)为null
SELECT a.*
  ,COUNT(b.c)+1 asall
FROM sc a
  LEFT JOIN sc b
    ON a.c=b.c AND a.score<b.score
GROUP BY 1,2,3
ORDER BY a.c,asall;