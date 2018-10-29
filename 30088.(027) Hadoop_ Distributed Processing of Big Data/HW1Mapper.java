package edu.ucsc.hadoop30088.hw1;

/**
 * Add import statements as needed
 */
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * To define a map function for your MapReduce job, subclass the Mapper class
 * and override the map method. The class definition requires four parameters:
 * 
 * @param inkey
 *            data type of the input key - LongWritable
 * @param invalue
 *            data type of the input value - Text
 * @param outkey
 *            data type of the output key - Text
 * @param outvalue
 *            data type of the output value - IntWritable
 */
public class HW1Mapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    /**
     * The map method runs once for each line of text in the input file. The
     * method receives:
     * 
     * @param inkey 
     *          key of type LongWritable
     * @param invalue
     *            value of type Text
     * @param context
     *            Context object.
     */
    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        /*
         * Convert the input Text object to String object
         */
        String line = value.toString();

        /*
         * Split the line into words
         */
        for (String word : line.split("\\W+")) {
            if (word.length() > 0) {
                String letter = word.substring(0, 1).toLowerCase();
                /*
                 * Do not include punctuation marks or white characters - spaces, line-breaks, etc..
                 * In other words, ignore all non-alphanumeric letters
                 * Ignore case: Convert all the letters to either upper or lower-case
                 */
                if (Character.isLetterOrDigit(letter.charAt(0))) {
                    /*
                     * Call the write method on the Context object to emit a key
                     * and a value from the map method. The key is the letter
                     * (in lower-case) that the word starts with; the value is
                     * '1'
                     */
                    context.write(new Text(letter), new IntWritable(1));
                }
            }
        }
    }
}