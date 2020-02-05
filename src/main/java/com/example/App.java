package com.example;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Region region = Region.AP_NORTHEAST_1;
//        final S3Client s3 = S3Client.builder().region(region).build();
//
//        String bucket= "daiyamask" + System.currentTimeMillis();
//        // Create bucket
//        CreateBucketRequest request1 = CreateBucketRequest
//                .builder()
//                .bucket(bucket)
//                .createBucketConfiguration(CreateBucketConfiguration.builder()
//                        .locationConstraint(region.id())
//                        .build())
//                .build();
////        final CreateBucketResponse response1 = s3.createBucket(request1);
////        System.out.println(response1);

        String bucket2 = "daiyamask2" + System.currentTimeMillis();
        // Create bucket
        CreateBucketRequest request2 = CreateBucketRequest
                .builder()
                .bucket(bucket2)
                .createBucketConfiguration(CreateBucketConfiguration.builder()
                        .locationConstraint(region.id())
                        .build())
                .build();

        final S3AsyncClient s3AsyncClient = S3AsyncClient.builder().region(region).build();
        s3AsyncClient.createBucket(request2).thenAccept(System.out::println);
        final CompletableFuture<ListBucketsResponse> future = s3AsyncClient.listBuckets();
        future.whenComplete((res, err) -> {
            try {
                if(res != null) {
                    System.out.println(res);
                } else {
                    err.printStackTrace();
                }
            } finally {
                s3AsyncClient.close();
            }
        });
        future.join();
    }
}
