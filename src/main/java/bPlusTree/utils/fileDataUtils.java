package bPlusTree.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.common.io.*;
import bPlusTree.bplus_Tree;


public class fileDataUtils {
    private static final String BASEPATH = "/Users/ruixi/Documents/B+Tree/test_data";
    List<Integer> keys = new ArrayList<Integer>();

    public void readFile(bplus_Tree bplus_tree,int page_size_in_kb, String d_size) throws IOException {
        int data_size = Integer.parseInt(d_size);
//        randomAccessFile RAF = new randomAccessFile();
        File f = new File(BASEPATH);
        File[] files = f.listFiles();
        int kb = 1024;
        int mb = 10;
        int pages = (mb*1000000)/(page_size_in_kb*kb);
        int result= 0;
        int byte_count = 0;
        int page_count = 0;
        long time  = 0;
        int base = 0;
        for (File file : files) {
            String fileName = file.getAbsolutePath() ;
            if (file.isFile() && fileName.contains("itcont")) {
//                System.out.println(fileName);
                String s = fileName.substring(47).getBytes().toString();
//                System.out.println(s.hashCode());

                InputStream inputStream = new FileInputStream(fileName);
                CountingInputStream cis = new CountingInputStream(inputStream);
                int page_size = page_size_in_kb*kb;

                while(cis.available()>0) {
//                    int page_size = random.nextInt(page_size_in_kb*kb-1024)+1024;
//                byte[] str = inputStream.readNBytes(4096);


                    byte[] bytes = cis.readNBytes(page_size);

                    result = (int) cis.getCount();
                    //System.out.println(result);
//                    pages--;
//                    int res = (int) cis.getCount()/(1024);


//                    int key = result;
//                    for (byte b : bytes) {
//                        bplus_tree.add(key, b);
//                        key++;
//                        byte_count++;
//                    }
                    int key = s.hashCode()+page_count;
                    keys.add(key);
//                    System.out.println(base+result);
                    page_count++;
                    int lim = base+result;
                    if(lim >= (data_size)){
                        // System.out.println("base+result: "+base+result+"data_size:"+(data_size));
                        break;
                    }


                    long current = System.currentTimeMillis();
                    bplus_tree.add(key,result);
                    //System.out.println(bplus_tree.search(result));

                    time += System.currentTimeMillis() - current;

//                    System.out.println(Integer.toBinaryString(0x1000 |res).substring(1) );
                }
//                System.out.println("base+result: "+base+result+"     data_size:"+data_size);

                // System.out.println("base+result: "+base+"   data_size:"+(data_size));
                base += result;
                if(base>= data_size){

                    break;
                }


//                    if (pages == 0){
//                        break;
//                    }
            }
            if(base>= data_size){

                break;
            }
        }
//        System.out.println("last pointer " +result);
        long duration = time;
//        System.out.println(String.format("Inserted %s bytes", (byte_count)));
        System.out.println("Number of pages(entries): " + page_count);
        System.out.println("Time used for insertion: [" + duration+"] ms");

    }


    public void searchTree(bplus_Tree bplus_tree,int page_size_in_kb, String data_size) throws IOException {

        List<Integer> lst_keys = keys;

        int kb = 1024;
        int mb = 10;
        int pages = (mb*1000000)/(page_size_in_kb*kb);

        Random rando = new Random();
        long total_time = 0;
        int page_count = lst_keys.size();
        //System.out.println("Number of pages :"+page_count);
        long time = System.currentTimeMillis();
        for(int i = 0; i <1000 ; i++){
            int rand_key = rando.nextInt(page_count);
//            System.out.println(rand_key);

            bplus_tree.search(lst_keys.get(rand_key));

        }
        System.out.println("The average time for 1000 searches is :["+(System.currentTimeMillis()-time)+"] ms");
    }

    public void deleteTree(bplus_Tree bplus_tree,int page_size_in_kb, String data_size) throws IOException {

        List<Integer> lst_keys = keys;
        File f = new File(BASEPATH+data_size);
        File[] files = f.listFiles();
        int kb = 1024;
        int mb = 10;
        int pages = (mb*1000000)/(page_size_in_kb*kb);


        int page_count = lst_keys.size();
        while (lst_keys.size()>0){

            Random rando = new Random();
            long total_time = 0;

            //System.out.println("Number of pages :"+page_count);
            for(int i = 0; i <page_count ; i++){
                int rand_key = rando.nextInt(lst_keys.size());
//            System.out.println(rand_key);
                long time = System.currentTimeMillis();
                bplus_tree.remove(lst_keys.get(rand_key));
                total_time += System.currentTimeMillis() - time;
                lst_keys.remove(rand_key);
//                for(int j = 0; j< bplus_tree.getRoot().getEntries().size();j++){
//                   if( bplus_tree.getRoot().getEntries().get(j).getKey().compareTo(0)!=0){
//
//                       bplus_tree.remove(bplus_tree.getRoot().getEntries().get(j).getKey());
//                   }
//                }

            }
            System.out.println("The time used for deletion is :["+total_time+"] ms");
        }
    }


}
