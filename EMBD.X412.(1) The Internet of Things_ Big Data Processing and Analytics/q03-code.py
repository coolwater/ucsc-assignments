from __future__ import print_function
import sys
from operator import add

textFile = spark.sparkContext.textFile("lab3-iot-gendata.txt")

# Output the filtered histogram
counts = textFile.flatMap(lambda x: x.split(' ')) \
	.filter(lambda x: x <= "94") \
	.map(lambda x: (x, 1)) \
	.reduceByKey(add)
output = counts.collect()
for (word, count) in output:
	print("%s: %i" % (word, count))