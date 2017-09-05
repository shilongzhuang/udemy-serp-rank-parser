### Udemy Search Result Parser


### The goal is to grab the search result data from Udemy website and see what factor is impacting their ranking.

1. This git repository is the Java code to fetch the data from design topic search result page. e.g. https://www.udemy.com/courses/search/?q=design&src=ukw

2. The available data right now are
| Feature                | Value                                                                                                       |
|------------------------|-------------------------------------------------------------------------------------------------------------|
| KEYWORD                | design                                                                                                      |
| RANK                   | 1386                                                                                                        |
| COURSE_ID              | 8324                                                                                                        |
| URL                    | /beginning-javascript/                                                                                      |
| SCORE                  | 2143.9092                                                                                                   |
| PREDICTIVE_SCORE       | 4073.02852                                                                                                  |
| IS_BESTSELLER          | 0                                                                                                           |
| TITLE                  | Javascript for Beginners                                                                                    |
| IS_PAID                | 1                                                                                                           |
| PRICE                  | 20                                                                                                          |
| HEADLINE               | Learn javascript online and supercharge your web design with this Javascript for beginners training course. |
| SUBSCRIBERS            | 3706                                                                                                        |
| RATING                 | 4.18966                                                                                                     |
| REVIEWS                | 203                                                                                                         |
| LECTURES               | 48                                                                                                          |
| QUIZZES                | 6                                                                                                           |
| HOURS                  | 3                                                                                                           |
| LEVEL                  | All Levels                                                                                                  |
| CAPTIONS               | 0                                                                                                           |
| INSTRUCTORS            | 1                                                                                                           |
| HAS_AUTO_CAPTION       | 0                                                                                                           |
| HAS_CERTIFICATE        | 1                                                                                                           |
| PUBLISHED_DATE         | 40733.23855                                                                                                 |
| LAST_UPDATE_DATE       | 42663                                                                                                       |
| DAYS_SINCE_PUBLISHED   | 2249                                                                                                        |
| DAYS_SINCE_LAST_UPDATE | 319                                                                                                         |
| HAS_UPDATED            | 1                                                                                                           |
| INSTRUCTOR_FIRST_NAME  | LearnToProgram,                                                                                             |
| INSTRUCTOR_FULL_NAME   | LearnToProgram, Inc.                                                                                        |
| INSTRUCTOR_JOB_TITLE   | Learn Web, Mobile and Game Development                                                                      |
| CATEGORY               | Development                                                                                                 |
| SUB_CATEGORY           | Web Development                                                                                             |


2. All of data are stored at local Mysql, we can pipe in the data to ElasticSearch and Kibana, then we can do an exploration analysis.

3. Try to use machine learning model to predict which factor is high correlated to the rank and its search score for certain topic. Stay tuned. I will have anther git repository regarding that result and analysis. 

**Disclaimer:** All of data are totally public and collected by the Java code, this project is just a fun project for technical exercise as search is my favorite. I don't use those for any commercial purpose.   
