package com.mobin.putDataToHBase;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Random;

/**
 * Created by hadoop on 3/9/16.
 */
public class PutDataToHBaseSortHotel extends Mapper<LongWritable, Text, ImmutableBytesWritable, Put>{
    protected void map(LongWritable key, Text value,
                       Mapper.Context context)
            throws IOException, InterruptedException {
        String[] values = value.toString().split("\t");
        String rowkey = values[1]+values[2]+values[13]+new Random().nextInt();
        Put put = new Put(Bytes.toBytes(rowkey));
        put.addColumn(Bytes.toBytes("INFO"),Bytes.toBytes("URL"), Bytes.toBytes(values[0]));
        put.addColumn(Bytes.toBytes("INFO"),Bytes.toBytes("SP"), Bytes.toBytes(values[1]));
        put.addColumn(Bytes.toBytes("INFO"),Bytes.toBytes("EP"), Bytes.toBytes(values[2]));
        put.addColumn(Bytes.toBytes("INFO"),Bytes.toBytes("ST"), Bytes.toBytes(values[4]));
        put.addColumn(Bytes.toBytes("INFO"),Bytes.toBytes("ET"), Bytes.toBytes(values[5]));
        put.addColumn(Bytes.toBytes("INFO"),Bytes.toBytes("SIGHTS"), Bytes.toBytes(values[3]));
        put.addColumn(Bytes.toBytes("INFO"),Bytes.toBytes("ALLDATE"), Bytes.toBytes(values[7]));
        put.addColumn(Bytes.toBytes("INFO"),Bytes.toBytes("HOTEL"), Bytes.toBytes(values[6]));
        put.addColumn(Bytes.toBytes("INFO"),Bytes.toBytes("TOTALPRICE"), Bytes.toBytes(Integer.valueOf(values[8])));
        put.addColumn(Bytes.toBytes("INFO"),Bytes.toBytes("TRAFFIC"), Bytes.toBytes(values[9]));
        put.addColumn(Bytes.toBytes("INFO"),Bytes.toBytes("TRAVELTYPE"), Bytes.toBytes(values[10]));
        put.addColumn(Bytes.toBytes("INFO"),Bytes.toBytes("IMAGE"), Bytes.toBytes(values[11]));
        put.addColumn(Bytes.toBytes("INFO"),Bytes.toBytes("SUPPLIER"), Bytes.toBytes(values[12]));
        put.addColumn(Bytes.toBytes("INFO"),Bytes.toBytes("HOTELLEVEL"), Bytes.toBytes(Integer.valueOf(values[13])));
        context.write(new ImmutableBytesWritable(Bytes.toBytes(rowkey)),put);
    }
}