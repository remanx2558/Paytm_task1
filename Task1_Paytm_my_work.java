//package Paytm_task;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.*;
//
//public class Task1_Paytm_my_work {
//
//    static class user{
//        int uid;
//        int age;
//        char gender;
//        String job;
//        String zip;
//        user(int u,int a,char g,String j,String t){
//            uid=u;
//            age=a;
//            gender=g;
//            job=j;
//            zip=t;
//        }
//
//    }
//    static class Rate{
//        int uid;
//        int mid;
//        int rat;
//        int time;
//
//        Rate(int u,int m,int r,int t){
//            uid=u;
//            mid=m;
//            rat=r;
//            time=t;
//        }
//
//    }
//    static class movie{
//
//        int mid;
//        String title;
//        String rdate;
//        String vdate;
//        String url;
//        ArrayList<Integer>gen;
//      movie(int m,String t,String r,String v,String u){
//          mid=m;
//          title=t;
//          rdate=r;
//          vdate=v;
//          url=u;
//          gen=new ArrayList<Integer>();
//      }
//
//    }
//    static  HashMap<Integer,String>genre;
//    static ArrayList<user>list_user;
//    static ArrayList<Rate>list_rates;
//    static ArrayList<movie>list_movie;
//
//
//
//
//    static float [][]movie_obj_helper;
//    public static void main(String args[]) throws FileNotFoundException {
//        getGenera();
//        getUser();
//        getRating();
//        getMovie();
//        Scanner s=new Scanner(System.in);
//        initialise_movie_objects_list();
//        already_seen_movies_by_users();
//        int t=s.nextInt();
//
//        //////////general sorting //////
//
//        //movie_obj_helper[list_movies.size][4]
//        //movie_obj_helper[mid][value][frequency][avg_value]
//
//
//
//
//
//
//
//
//
//
//
//        for(int p=0;p<t;p++){
//            int uid=s.nextInt();
//            fiveMovies(uid);
//        }
//
//
//
//
//
//
//
//    }
//    static HashMap<Integer,Integer>usr_movies[];
//    static void already_seen_movies_by_users(){
//        usr_movies=new HashMap[944];
//        for(int i=0;i<944;i++){
//            usr_movies[i]=new HashMap<Integer,Integer>();
//        }
//        for(int i=0;i<list_rates.size();i++){
//            Rate curr=list_rates.get(i);
//            int uid=curr.uid;
//            int mid=curr.mid;
//            usr_movies[uid].put(mid,1);
//        }
//
//
//
//
//    }
//    static Integer mov_ans[];
//    static void initialise_movie_objects_list(){
//        movie_obj_helper=new float[1683][3];
//        //movie_obj_helper[mid][value][frequency][avg_value]
//        for(int i=0;i<list_rates.size();i++){
//            Rate curr=list_rates.get(i);
//            int mid=curr.mid;
//            int rat=curr.rat;
//            movie_obj_helper[mid][0]=movie_obj_helper[mid][0]+rat;//value
//            movie_obj_helper[mid][1]=movie_obj_helper[mid][1]+1;//frequency
//            if(movie_obj_helper[mid][1]>0){
//                movie_obj_helper[mid][2]=movie_obj_helper[mid][0]/movie_obj_helper[mid][1];//avg_value
//            }
//        }
////        for(int i=0;i<1683;i++){
////            System.out.println(i+" "+movie_obj_helper[i][0]+" "+movie_obj_helper[i][1]+" "+movie_obj_helper[i][2]);
////        }
//        ///////make array of movies
//         mov_ans=new Integer[1683];
//        for(int i=0;i<1683;i++){
//            mov_ans[i]=i;
//        }
//        ////////////////////////////
//
//        Arrays.sort(mov_ans,new myOne());
////        for(int i=0;i<1683;i++){
////            System.out.println(mov_ans[i]);
////        }
//
//
//        /////////////////////
//
//
//
//
//
//
//    }
//    static class myOne implements Comparator<Integer>{
//
//
//        @Override
//        public int compare(Integer a, Integer b) {
//            int k=(movie_obj_helper[a][2]>movie_obj_helper[b][2])?1:-1;
//            if(movie_obj_helper[a][2]==movie_obj_helper[b][2]){
//                k=(movie_obj_helper[a][1]>movie_obj_helper[b][1])?1:-1;
//                if(movie_obj_helper[a][1]==movie_obj_helper[b][1]){
//                    k=0;
//                }
//            }
//
//
//            return k;
//        }
//    }
//
//
//
//
//
//
//
//
//    static float gen_helper[][];
//    static void fiveMovies(int uid){
//        Integer copy_mov_ans[]= mov_ans.clone();
//         gen_helper=new float[19][3];
//        //[gen_id][value][frequncy][average_value]
//        for(int i=0;i<list_rates.size();i++){
//            Rate curr=list_rates.get(i);
//            int val=curr.rat;
//            int mid=curr.mid;
//
//            if(mid<list_movie.size()){
//                ArrayList<Integer>movie_fall=list_movie.get(mid).gen;
//                for(int j=0;j<movie_fall.size();j++){
//                    int cat=movie_fall.get(j);
//                    gen_helper[cat][0]=gen_helper[cat][0]+val;//val
//                    gen_helper[cat][1]++;//freq
//                    if(gen_helper[cat][1]>0){
//                        gen_helper[cat][2]=gen_helper[cat][0]/gen_helper[cat][1];
//                    }
//                }
//            }
//
//
//        }
//        Arrays.sort(copy_mov_ans,new myTwo());
//        ///////////////////////////////////////////////////already seen movies by user
//        int counter=0;
//        for(int i=copy_mov_ans.length-1;i>=0&& counter<5;i--){
//            int val=copy_mov_ans[i];
//            if(!usr_movies[uid].containsKey(val)){
//                counter++;
//                System.out.println(list_movie.get(val).title);
//            }
//
//
//
//
//        }
//
//
//        /////////////////////////////////////////////////////
//
//
//
//
//
//    }
//    static class myTwo implements Comparator<Integer>{
//
//
//        @Override
//        public int compare(Integer mid1, Integer mid2) {
//            float val1=0;
//            if(mid1<list_movie.size()){
//                ArrayList<Integer>genrasss1=list_movie.get(mid1).gen;
//                for(int i=0;i<genrasss1.size();i++){
//                    int c1=genrasss1.get(i);
//                    val1=val1+gen_helper[c1][2];
//                }
//            }
//
//
//            float val2=0;
//            if(mid2<list_movie.size()){
//                ArrayList<Integer>genrasss2=list_movie.get(mid2).gen;
//                for(int i=0;i<genrasss2.size();i++){
//                    int c2=genrasss2.get(i);
//                    val2=val2+gen_helper[c2][2];
//                }
//            }
//
//            int k=(val1>val2)?1:-1;
//            if(val1==val2){
//                k=0;
//            }
//
//
//            return k;
//        }
//    }
//
//    static void getMovie() throws FileNotFoundException {
//        File file1=new File("C:\\Users\\gyash\\OneDrive\\Desktop\\Paytm_task1\\movie.data");//open file
//        Scanner scan1=new Scanner (file1);//scan file
//        list_movie=new ArrayList<movie>();
//
//        ///////////////
//        int counter=0;
//        while(scan1.hasNextLine()){
//            counter++;
//            String arr1[]=scan1.nextLine().split("\\|");//split at every |
//            String title="";
//            String rdate="";
//            String vdate="";
//            String url="";
//            int mid=0;
//
//            if(arr1.length>0){
//
//                mid=Integer.parseInt(arr1[0]);
//                title=arr1[1];
//                rdate=arr1[2];
//                 vdate=arr1[3];
//                 url=arr1[4];
//                movie curr_movie=new movie(mid,title,rdate,vdate,url);
//                for(int i=5;i<arr1.length;i++){
//                    if(arr1[i].length()>0 && arr1[i].charAt(0)=='1'){
//                        curr_movie.gen.add(i-5);
//
//                    }
//                }
//
//               // System.out.println("i goes till "+arr1.length);
//                list_movie.add(curr_movie);
//
//
//
//            }
//          //  System.out.println(mid+" "+title+" ");
//
//
//
//
//
//        }
//      //  System.out.println("length of movie_list is "+list_movie.size()+" but counter is "+counter);
//
//
//
//
//    }
//    static void getRating() throws FileNotFoundException {
//        File file1=new File("C:\\Users\\gyash\\OneDrive\\Desktop\\Paytm_task1\\ratings.data");//open file
//        Scanner scan1=new Scanner (file1);//scan file
//        list_rates=new ArrayList<Rate>();
//
//        ///////////////
//        while(scan1.hasNextLine()){
//            String arr1[]=scan1.nextLine().split("\\t");//split at every |
//
//            if(arr1.length>1){
//                int uid=Integer.parseInt(arr1[0]);
//                int mid=Integer.parseInt(arr1[1]);
//                int rat=Integer.parseInt(arr1[2]);
//                int time=Integer.parseInt(arr1[3]);
//                list_rates.add(new Rate(uid,mid,rat,time));
//
//            }
//
//        }
//     //   System.out.println("length of rate_list is "+list_rates.size());
////                for(int i=0;i<list_rates.size();i++){
////                    Rate curr=list_rates.get(i);
////            System.out.println(curr.uid+" "+curr.mid+" "+curr.rat+" "+curr.time);
////        }
//
//
//
//    }
//
//    static void getUser() throws FileNotFoundException {
//        File file1=new File("C:\\Users\\gyash\\OneDrive\\Desktop\\Paytm_task1\\user.data");//open file
//        Scanner scan1=new Scanner (file1);//scan file
//        list_user=new ArrayList<user>();
//
//        ///////////////
//        while(scan1.hasNextLine()){
//            String arr1[]=scan1.nextLine().split("\\|");//split at every |
//
//            if(arr1.length>1){
//                int uid=Integer.parseInt(arr1[0]);
//                int age=Integer.parseInt(arr1[1]);
//                char gender=arr1[2].charAt(0);
//                String job=arr1[3];
//                String time=arr1[4];
//                list_user.add(new user(uid,age,gender,job,time));
//
//            }
//
//        }
////        System.out.println("length of user_list is "+list_user.size());
////                for(int i=0;i<list_user.size();i++){
////                    user curr=list_user.get(i);
////            System.out.println(curr.uid+" "+curr.gender+" "+curr.job);
////        }
//
//
//
//    }
//    static void getGenera() throws FileNotFoundException {
//        File file1=new File("C:\\Users\\gyash\\OneDrive\\Desktop\\Paytm_task1\\genre.data");//open file
//        Scanner scan1=new Scanner (file1);//scan file
//        genre=new HashMap<Integer,String>();//hashMap
//
//
//        ///////////////
//        while(scan1.hasNextLine()){
//            String arr1[]=scan1.nextLine().split("\\|");//split at every |
//
//            if(arr1.length>1){
//                int val=Integer.parseInt(arr1[1]);
//                String str=arr1[0];
//                genre.put(val,str);
//            }
//
//        }
////        for(int i=0;i<=18;i++){
////            System.out.println(i+" and its genra "+genre.get(i));
////        }
//
//
//    }
//
//}
