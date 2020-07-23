package com.example.news.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.news.Models.NewsModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyAsyncTask extends AsyncTask<String, NewsModel.ArticlesEntity, List<NewsModel.ArticlesEntity>> {
    com.example.news.utils.ops ops;
    String url;

    public MyAsyncTask(ops ops, String url) {
        this.url = url;
        this.ops = ops;

    }


    /**
     * Runs on Background Thread
     */
    @Override
    protected List<NewsModel.ArticlesEntity> doInBackground(String... strings) {
        List<NewsModel.ArticlesEntity> List = new ArrayList<>();

        try {


            Document doc = Jsoup.connect(url).get();

            //Log.e(TAG, "onCreate: " + doc.body());

            Elements paragraphs = doc.getElementsByClass("col-xs-12 bigOneSec");
            String s1;
            String s2;
            String s3;
            String s4;
            String s5;

            for (Element paragraph : paragraphs) {
                if (isCancelled()) {
                    break;
                }
                /* Date  */
                s1 = paragraph.getElementsByTag("span").text();
                /*Desc*/
                s3 = paragraph.getElementsByTag("p").text();
                /* Title*/
                s2 = paragraph.getElementsByTag("h3").text();
                /* Image*/
                s4 = paragraph.getElementsByTag("a").get(0).getElementsByTag("img").attr("src");

                String s = paragraph.getElementsByTag("a").get(0).attr("href");
                s5 = "https://www.youm7.com/" + URLEncoder.encode(s, "UTF-8");
                Document doc2 = Jsoup.connect(s5).get();
                Elements elements = doc2.getElementsByClass("articleCont");
                //   String ss2 = elements.get(0).getElementsByAttribute("id").text();
                //        String ss1 = elements.get(0).getElementsByClass("writeBy").text();


                List.add(new NewsModel.ArticlesEntity(s2, s3, s1, s4, s5));

                Log.e("XX" + "2", "\n" + s4 + "\n" + s1 + "\n" + s2 + "\n" + s3 + "\n" + s5);
                publishProgress(new NewsModel.ArticlesEntity(s2, s3, s1, s4, s5));

            }

        } catch (IOException e) {
            Log.e("XX", "onCreate: " + e.getLocalizedMessage());
        }
        return List;
    }

    /**
     * Runs on Main Thread
     */
    @Override
    protected void onProgressUpdate(NewsModel.ArticlesEntity... values) {
        super.onProgressUpdate(values);
        ops.get2(Arrays.asList(values));
    }

    /**
     * Runs on Main Thread
     */
    @Override
    protected void onPostExecute(List<NewsModel.ArticlesEntity> aLong) {
        super.onPostExecute(aLong);
        ops.get(aLong);
    }

    public void cancel() {
        this.cancel(true);
    }
}
