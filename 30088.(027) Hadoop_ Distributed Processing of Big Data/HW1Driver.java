package edu.ucsc.hadoop30088.hw1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class HW1Driver extends Configured implements Tool {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        /*
         * set configuration values for the job programmatically.
         */

        int exitCode = ToolRunner.run(conf, new HW1Driver(), args);
        System.exit(exitCode);

    }

    @Override
    public int run(String[] args) throws Exception {
        /*
         * Validate that two arguments were passed from the command line.
         */
        if (args.length != 2) {
            System.out.printf("Usage: HW1Driver <input dir> <output dir>\n");
            System.exit(-1);
        }

        /*
         * Instantiate a Job object for your job's configuration.
         */
        Job job = Job.getInstance(getConf());

        /*
         * Specify the paths to the input and output data based on the
         * command-line arguments.
         */
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        /*
         * Specify the job: Program that outputs the number of words that begin
         * with each alphanumeric letter. Ignore case: Convert all the letters
         * to either upper or lower-case.
         */
        /*
         * Specify the jar file that contains your driver, mapper, and reducer.
         * Hadoop will transfer this jar file to nodes in your cluster.
         */
        job.setJarByClass(HW1Driver.class);

        /*
         * Set MapReduce job name
         */
        job.setJobName("Homework1");

        /*
         * Specify the mapper and reducer classes
         */
        job.setMapperClass(HW1Mapper.class);
        job.setReducerClass(HW1Reducer.class);

        /*
         * Call setInputFormatClass and setOutputFormatClass methods here, can
         * be ignored if input and output stream formats are text data.
         */
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        /*
         * The mapper's output keys and values have different data types than
         * the reducer's output keys and values. Therefore, you must call the
         * setMapOutputKeyClass and setMapOutputValueClass methods.
         */
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        /*
         * Specify the job's output key and value classes.
         * Use LongWritable as the number of words can be greater than MAX_INT
         */
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        /*
         * Start the MapReduce job and wait for it to finish. If it finishes
         * successfully, return 0. If not, return 1.
         */
        boolean success = job.waitForCompletion(true);
        return (success ? 0 : 1);
    }
}
