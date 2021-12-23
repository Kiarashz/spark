from pyspark.sql import SparkSession
from pyspark.sql.functions import avg

spark = (SparkSession
    .builder
    .appName("ages")
    .getOrCreate())

dataDf = spark.createDataFrame(
    [
        ("Brooke", 32),
        ("Denny", 22),
        ("Jules", 30),
        ("TD", 25),
        ("Brooke", 35)
    ],
    ["name", "age"])

avgDf = dataDf.groupBy("name").agg(avg("age"))
avgDf.show()