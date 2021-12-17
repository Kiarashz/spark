package kiarash

import org.jetbrains.kotlinx.spark.api.*
import org.apache.spark.sql.functions.*

fun main(args: Array<String>) {
    if (args.size < 1) {
        println("""
            Usage: ch02 <csv file>
        """.trimIndent())
    }

    val inputFile = args[0]

    withSpark {
        val mnmDF = spark.read().option("header", "true").option("inferSchema", "true").csv(inputFile)
        val countMnMDf = mnmDF
                .select("State", "Color", "Count")
                .groupBy("State", "Color")
                .sum("Count")
                .orderBy(desc("sum(Count)"))
        countMnMDf.show(12)
        println("Total rows = ${countMnMDf.count()}")
    }
}