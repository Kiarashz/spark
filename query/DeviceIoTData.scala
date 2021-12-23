case class DeviceIoTData (battery_level: Long, c02_level: Long, 
cca2: String, cca3: String, cn: String, device_id: Long, 
device_name: String, humidity: Long, ip: String, latitude: Double,
lcd: String, longitude: Double, scale:String, temp: Long, 
timestamp: Long)

// /home/kiarash/lworkspace/spark/LearningSparkV2/databricks-datasets/learning-spark-v2/iot-devices/iot_devices.json

case class DeviceTempByCountry(temp: Long, device_name: String, device_id: Long, 
  cca3: String)
val dsTemp = ds
  .filter(d => {d.temp > 25})
  .map(d => (d.temp, d.device_name, d.device_id, d.cca3))
  .toDF("temp", "device_name", "device_id", "cca3")
  .as[DeviceTempByCountry]

ds.select('humidity, 'temp, 'c02_level, 'battery_level)
.agg(
    min('battery_level), max('battery_level), 
    min('temp), max('temp), 
    min('c02_level), max('c02_level),
    min('humidity), max('humidity)
).show()

ds.select(
    min('battery_level), max('battery_level), 
    min('temp), max('temp), 
    min('c02_level), max('c02_level),
    min('humidity), max('humidity)
).show()