Kotlin-Spark sample application based on [Learning Spark, 2nd Edition](https://github.com/databricks/LearningSparkV2.git).

```
 docker run -p 8888:8888 -p 4040:4040 -v sparkhome:/home/jovyan psychothan/scaling-data-science
```

Some useful commands:

```
spark.sql("show table extended in my_database like 'my_table' partition ( dt = '20210928')").show()
spark.sql("show partitions my_table").show()
spark.sql("alter table my_table drop partition (dt='20220430')")
spark.sql("drop table my_table")
```
