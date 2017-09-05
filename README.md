### Udemy Search Result Parser


### The goal is to grab the search result data from Udemy website and see what factor is impacting their ranking.

1.This git repository is the Java code to fetch the data from design topic search result page. e.g. https://www.udemy.com/courses/search/?q=design&src=ukw

2.The available data right now are
 - KEYWORD
 - RANK
 - COURSE_ID
 - URL
 - SCORE
 - PREDICTIVE_SCORE
 - IS_BESTSELLER
 - TITLE
 - IS_PAID
 - PRICE
 - HEADLINE
 - SUBSCRIBERS
 - RATING
 - REVIEWS
 - LECTURES
 - QUIZZES
 - HOURS
 - LEVEL
 - CAPTIONS
 - INSTRUCTORS
 - HAS_AUTO_CAPTION
 - HAS_CERTIFICATE
 - PUBLISHED_DATE
 - LAST_UPDATE_DATE
 - DAYS_SINCE_PUBLISHED
 - DAYS_SINCE_LAST_UPDATE
 - HAS_UPDATED
 - INSTRUCTOR_FIRST_NAME
 - INSTRUCTOR_FULL_NAME
 - INSTRUCTOR_JOB_TITLE
 - CATEGORY
 - SUB_CATEGORY


3.All of data are stored at local Mysql, we can pipe in the data to ElasticSearch and Kibana, then we can do an exploration analysis.

4.Try to use machine learning model to predict which factor is high correlated to the rank and its search score for certain topic. Stay tuned. I will have anther git repository regarding that result and analysis. 

**Disclaimer:** All of data are totally public and collected by the Java code, this project is just a fun project for technical exercise as search is my favorite. I don't use those for any commercial purpose.   
