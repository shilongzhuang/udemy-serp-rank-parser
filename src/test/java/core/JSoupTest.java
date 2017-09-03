package core;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class JSoupTest {

    private String url = "https://www.udemy.com/bootstrap-to-wordpress/";

    Document doc;

    @Before
    public void init() {
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void title() {
        System.out.println(doc.title());
    }

    @Test
    public void printUpdateDate() {
        Elements elements = doc.select("div[data-purpose=last-update-date]");
        String text =  elements.text();
        System.out.println(text);

        if(text.startsWith("Last updated")) {
            String[] texts = text.split(" ");
            System.out.println(texts[2]);
        }

     //   System.out.println(elements.text());
    }

    @Test
    public void parseLocalFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("examples/course.html").getFile());
        System.out.println(file.exists());

        try {
            Document document  = Jsoup.parse(file,"UTF-8");
        //    System.out.println(document.body());
            Elements elements = document.select("div[data-purpose=last-update-date]");
            String text =  elements.text();
            System.out.println(text);

            if(text.startsWith("Last updated")) {
                String[] texts = text.split(" ");
                System.out.println(texts[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
