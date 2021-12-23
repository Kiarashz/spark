df = spark.read.option('header', True).option('infer_schema', True).csv('/home/kiarash/lworkspace/spark/LearningSparkV2/databricks-datasets/learning-spark-v2/flights/departuredelays.csv')

###################################################################
df.createOrReplaceTempView("flights")
spark.sql("""
select delay, origin, destination, case
when delay > 360 then "Very Long Delay"
when delay > 120 and delay < 360 then "Long Delay"
when delay > 60 and delay <= 120 then "Short Delay"
when delay > 0 and delay <= 60 then "Tolerable Delays"
when delay = 0 then "No Delays"
else "Early"
end as flight_delays
from flights
order by delay desc
""").show(10,False)

###################################################################
def convertCase(str):
    resStr=""
    arr = str.split(" ")
    for x in arr:
       resStr= resStr + x[0:1].upper() + x[1:len(x)] + " "
    return resStr 

convertUDF = udf(lambda z: convertCase(z),StringType())

###################################################################
import datetime
from pyspark.sql.types import TimestampType
from pyspark.sql.functions import udf, col, to_timestamp, lit
def str2timestamp(input, year):
    result = datetime.datetime.strptime(f"{year}{input}","%Y%m%d%H%S")
    return result

print(str2timestamp("02010925", 2018))
convert2Timestamp = udf(str2timestamp, TimestampType())
df.withColumn("dtime", convert2Timestamp(df.date, lit(2018))).show()

###################################################################
import datetime
from pyspark.sql.types import TimestampType
from pyspark.sql.functions import udf, col, to_timestamp, lit
def str2timestamp(input, year):
    result = datetime.datetime.strptime(f"{year}{input}","%Y%m%d%H%S")
    return result

print(str2timestamp("02010925", 2018))
convert2Timestamp = udf(lambda idate: str2timestamp(idate, 2018), TimestampType())
df.withColumn("dtime", convert2Timestamp(df.date)).show()


###################################################################
from pyspark.sql.types import TimestampType
from pyspark.sql.functions import udf, col, to_timestamp, lit
df.withColumn("dtime", to_timestamp(col("date"), "MMddHHss")).show()

###################################################################
from pyspark.sql.functions import to_timestamp

(sc
    .parallelize([Row(dt='2016_08_21 11_31_08')])
    .toDF()
    .withColumn("parsed", to_timestamp("dt", "yyyy_MM_dd HH_mm_ss"))
    .show(1, False))
###################################################################

df.select(to_timestamp(lit('06-24-2019 12:01:19.000'),'MM-dd-yyyy HH:mm:ss.SSSS')) \
  .show()