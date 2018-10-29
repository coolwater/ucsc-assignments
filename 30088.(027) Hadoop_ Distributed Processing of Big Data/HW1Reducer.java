package edu.ucsc.hadoop30088.hw1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * To define a reduce function for your MapReduce job, subclass the Reducer
 * class and override the reduce method. The class definition requires four
 * parameters:
 * 
 * @param inkey
 *            data type of the input key - Text
 * @param invalue
 *            data type of the input value - IntWritable
 * @param outkey
 *            data type of the output key - Text
 * @param outvalue
 *            data type of the output value - LongWritable
 */
public class HW1Reducer extends Reducer<Text, IntWritable, Text, LongWritable> {

    /**
     * The reduce method runs once for each key received from the shuffle and
     * sort phase of the MapReduce framework. The method receives:
     * 
     * @param inkey
     *            key of type Text
     * @param invalues
     *            set of values of type IntWritable
     * @param context
     *            Context object
     */
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {
        long lcount = 0L;

        for (IntWritable value : values) {
            lcount += value.get();
        }
        /*
         * write the total count of words starting with the 'key' letter (alphanumeric, ignorecase)
         */
        if (lcount > 0L) {
            context.write(key, new LongWritable(lcount));
        }
    }
}