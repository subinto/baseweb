package crawling;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CommonCrolling {
    private final static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
   
    public static String getCurrentData(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return sdf.format(new Date());
    }
   
    public static void readOkjspJob(int num) {
        try {
//            System.out.println("############################################## Start Time : " + getCurrentData());
            // 1. URL 선언
            String connUrl = "https://okky.kr/articles/jobs?offset=" + 20 * num + "&max=20&sort=id&order=desc";
            
//            System.out.println("Read Url : " + connUrl);
            // 2. HTML 가져오기
            Connection conn = Jsoup
                    .connect(connUrl)
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .userAgent(USER_AGENT)
                    .method(Connection.Method.GET)
                    .ignoreContentType(true);
           
            Document doc = conn.get();
           
            // 3. 가져온 HTML Document 를 확인하기
            System.out.println(doc.toString());
            //System.out.println(doc.select(".list-group-item-heading").toString());
            
            // Ex.1 : 글 목록
//            Elements listItem = doc.getElementsByClass("list-group-item-heading");
//            for(Element item : listItem) {
//            	Elements aTag = item.getElementsByTag("a");
//            	System.out.println(num + " > " + aTag.attr("href") + " : " + aTag.text());
//            }
           
        } catch (IOException e) {
            // Exp : Connection Fail
            e.printStackTrace();
        }
//        System.out.println("############################################## End Time : " + getCurrentData());
    }
    public static void main(String[] args) {
    	for(int i = 0 ; i < 1 ; i++) {
    		readOkjspJob(i);
    	}
    }
}
