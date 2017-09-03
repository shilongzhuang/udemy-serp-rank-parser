### Udemy Search Result Page Rank Factor


### The goal is to grab the search result data from Udemy website and see what factor is impacting their ranking.

1. This git repository is Java code (first version) to fetch the data from design topic search result page. e.g. https://www.udemy.com/courses/design/all-courses/

2. The available data right now are
- ID
- TITLE
- URL
- IS_PAID
- PRICE
- HEADLINE
- SUBSCRIBERS
- RATING
- REVIEWS
- LECTURES
- SCORE
- HOURS
- LEVEL
- CAPTIONS
- IS_BESTSELLER
- INSTRUCTORS
- PUBLISHED_DATE

Will add more.

2. All of data are stored at local Mysql, we can pipe in the data to ElasticSearch and Kibana, then we can do an exploration analysis.

3. Try to use machine learning model to predict which factor is high correlated to the rank and its search score for certain topic. Stay tuned. I will have anther git repository regarding that result and analysis. 

**Disclaimer:** All of data are totally public and collected by the Java code, this project is just a fun project for technical exercise as search is my favorite. I don't use those for any commercial purpose.   
