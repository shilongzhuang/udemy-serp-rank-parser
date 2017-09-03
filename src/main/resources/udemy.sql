SHOW DATABASES;

USE udemy;

SHOW TABLES;
DROP TABLE course;
CREATE TABLE course (
  ID             INT          NOT NULL,
  TITLE          VARCHAR(100) NOT NULL,
  URL            VARCHAR(100) NOT NULL,
  IS_PAID        TINYINT(1)   NOT NULL,
  PRICE          INT          NOT NULL,
  HEADLINE       VARCHAR(300) NOT NULL,
  SUBSCRIBERS    INT          NOT NULL,
  RATING         DOUBLE       NOT NULL,
  REVIEWS        INT          NOT NULL,
  LECTURES       INT          NOT NULL,
  SCORE          DOUBLE       NOT NULL,
  HOURS          DOUBLE       NOT NULL,
  LEVEL          VARCHAR(30)  NOT NULL,
  CAPTIONS       INT          NOT NULL,
  IS_BESTSELLER  TINYINT(1)   NOT NULL,
  INSTRUCTORS    INT          NOT NULL,
  PUBLISHED_DATE DATETIME     NOT NULL,
  CHANNEL_ID     INT          NOT NULL,
  RANK           INT          NOT NULL,
  PRIMARY KEY (ID, CHANNEL_ID)
);

--
DROP TABLE course_detail;
CREATE TABLE course_detail (
  ID                    INT PRIMARY KEY NOT NULL,
  TITLE                 VARCHAR(100)    NOT NULL,
  URL                   VARCHAR(100)    NOT NULL,
  IS_PAID               TINYINT(1)      NOT NULL,
  PRICE                 INT             NOT NULL,
  HEADLINE              VARCHAR(300)    NOT NULL,
  SUBSCRIBERS           INT             NOT NULL,
  RATING                DOUBLE          NOT NULL,
  REVIEWS               INT             NOT NULL,
  LECTURES              INT             NOT NULL,
  QUIZZES               INT             NOT NULL,
  HOURS                 DOUBLE          NOT NULL,
  LEVEL                 VARCHAR(30)     NOT NULL,
  CAPTIONS              INT             NOT NULL,
  IS_BESTSELLER         TINYINT(1)      NOT NULL,
  INSTRUCTORS           INT             NOT NULL,
  HAS_AUTO_CAPTION      TINYINT(1)      NOT NULL,
  HAS_CERTIFICATE       TINYINT(1)      NOT NULL,
  PUBLISHED_DATE        DATETIME        NOT NULL,
  LAST_UPDATE_DATE      DATETIME        NOT NULL,
  INSTRUCTOR_FULL_NAME  VARCHAR(100)    NOT NULL,
  INSTRUCTOR_FIRST_NAME VARCHAR(50)     NOT NULL,
  INSTRUCTOR_JOB_TITLE  VARCHAR(200)    NOT NULL,
  INSTRUCTOR_URL        VARCHAR(100)    NOT NULL,
  INSTRUCTOR_ID         INT             NOT NULL,
  CATEGORY              VARCHAR(100)    NOT NULL,
  CATEGORY_ID           INT             NOT NULL,
  SUB_CATEGORY          VARCHAR(100)    NOT NULL,
  SUB_CATEGORY_ID       INT             NOT NULL
);

--
DROP TABLE search_result;
CREATE TABLE search_result (
  COURSE_ID        INT          NOT NULL,
  KEYWORD          VARCHAR(100) NOT NULL,
  URL              VARCHAR(100) NOT NULL,
  SCORE            DOUBLE       NOT NULL,
  PREDICTIVE_SCORE DOUBLE       NOT NULL,
  RANK             INT          NOT NULL,
  PRIMARY KEY (COURSE_ID, KEYWORD)
);

SELECT *
FROM search_result
WHERE KEYWORD = 'SEO'
ORDER BY RANK;

SELECT
  COUNT(*),
  COUNT(DISTINCT COURSE_ID)
FROM search_result;

SELECT
  s.KEYWORD,
  s.RANK,
  s.SCORE,
  s.PREDICTIVE_SCORE,
  c.*
FROM search_result s LEFT OUTER JOIN course_detail c
ON s.COURSE_ID = c.ID;