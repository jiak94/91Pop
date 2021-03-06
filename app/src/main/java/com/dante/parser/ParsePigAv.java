package com.dante.parser;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.dante.data.model.BaseResult;
import com.dante.data.model.PigAv;
import com.dante.data.model.PigAvVideo;
import com.dante.utils.StringUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author flymegoc
 * @date 2018/1/22
 */

public class ParsePigAv {
    private static final String TAG = ParsePigAv.class.getSimpleName();

    /**
     * @param html 原网页
     * @return json===
     */
    public static BaseResult<PigAvVideo> parserVideoUrl(String html) {
        BaseResult<PigAvVideo> baseResult = new BaseResult<>();
        Document document = Jsoup.parse(html);
        Element videoWrapper = document.getElementsByClass("td-post-content td-pb-padding-side").first();
        String videoHtml = videoWrapper.html();
        Logger.t(TAG).d(videoHtml);
        int index = videoHtml.indexOf("setup") + 6;
        int endIndexV = videoHtml.indexOf(");");
        String videoUrl = videoHtml.substring(index, endIndexV);
        Logger.t(TAG).d(videoUrl);

        PigAvVideo pigAvVideo = new Gson().fromJson(videoUrl, PigAvVideo.class);

        Elements items = document.getElementsByClass("td-block-span12");
        List<PigAv> pigAvList = new ArrayList<>();
        for (Element element : items) {
            PigAv pigAv = new PigAv();
            Element a = element.selectFirst("a");
            String title = a.attr("title");
            pigAv.setTitle(title);
            String contentUrl = a.attr("href");
            pigAv.setContentUrl(contentUrl);
            Element img = element.selectFirst("img");
            String imgUrl = img.attr("src");
            int beginIndex = imgUrl.lastIndexOf("/");
            int endIndex = imgUrl.indexOf("-");
            String bigImg = StringUtils.subString(imgUrl, 0, endIndex);
            if (TextUtils.isEmpty(bigImg)) {
                pigAv.setImgUrl(imgUrl);
            } else {
                pigAv.setImgUrl(bigImg + ".jpg");
            }
            String pId = StringUtils.subString(imgUrl, beginIndex + 1, endIndex);
            Logger.t(TAG).d(pId);
            pigAv.setpId(pId);

            int imgWidth = Integer.parseInt(img.attr("width"));
            pigAv.setImgWidth(imgWidth);
            int imgHeight = Integer.parseInt(img.attr("height"));
            pigAv.setImgHeight(imgHeight);
            pigAvList.add(pigAv);
        }
        pigAvVideo.setPigAvList(pigAvList);
        baseResult.setData(pigAvVideo);
        return baseResult;
    }

    public static BaseResult<List<PigAv>> videoList(String html) {
        BaseResult<List<PigAv>> baseResult = new BaseResult<>();
        baseResult.setTotalPage(1);

        Document doc = Jsoup.parse(html);
        Elements items = doc.getElementsByClass("td-block-span4");
        List<PigAv> pigAvList = new ArrayList<>();
        for (Element element : items) {
            PigAv pigAv = new PigAv();
            Element a = element.selectFirst("a");
            String title = a.attr("title");
            pigAv.setTitle(title);
            String contentUrl = a.attr("href");
            pigAv.setContentUrl(contentUrl);
            Element img = element.selectFirst("img");
            String imgUrl = img.attr("src");
            int beginIndex = imgUrl.lastIndexOf("/");
            int endIndex = imgUrl.lastIndexOf("-");
            String bigImg = StringUtils.subString(imgUrl, 0, endIndex);
            if (TextUtils.isEmpty(bigImg)) {
                pigAv.setImgUrl(imgUrl);
            } else {
                pigAv.setImgUrl(bigImg + ".jpg");
            }
            String pId = StringUtils.subString(imgUrl, beginIndex + 1, endIndex);
            Logger.t(TAG).d(pId);
            pigAv.setpId(pId);

            int imgWidth = Integer.parseInt(img.attr("width"));
            pigAv.setImgWidth(imgWidth);
            int imgHeight = Integer.parseInt(img.attr("height"));
            pigAv.setImgHeight(imgHeight);
            pigAvList.add(pigAv);
        }
        baseResult.setData(pigAvList);
        return baseResult;
    }

    public static BaseResult<List<PigAv>> moreVideoList(String html) {
        BaseResult<List<PigAv>> baseResult = new BaseResult<>();
        baseResult.setTotalPage(1);

        Document doc = Jsoup.parse(html);
        Elements items = doc.getElementsByClass("td-block-span4");
        List<PigAv> pigAvList = new ArrayList<>();
        for (Element element : items) {
            PigAv pigAv = new PigAv();
            Element a = element.selectFirst("a");
            String title = a.attr("title");
            pigAv.setTitle(title);
            String contentUrl = a.attr("href");
            pigAv.setContentUrl(contentUrl);
            Element img = element.selectFirst("img");
            String imgUrl = img.attr("src");
            int beginIndex = imgUrl.lastIndexOf("/");
            int endIndex = imgUrl.lastIndexOf("-");
            String bigImg = StringUtils.subString(imgUrl, 0, endIndex);
            if (TextUtils.isEmpty(bigImg)) {
                pigAv.setImgUrl(imgUrl);
            } else {
                pigAv.setImgUrl(bigImg + ".jpg");
            }
            String pId = StringUtils.subString(imgUrl, beginIndex + 1, endIndex);
            Logger.t(TAG).d(pId);
            pigAv.setpId(pId);

            int imgWidth = Integer.parseInt(img.attr("width"));
            pigAv.setImgWidth(imgWidth);
            int imgHeight = Integer.parseInt(img.attr("height"));
            pigAv.setImgHeight(imgHeight);
            pigAvList.add(pigAv);
        }
        baseResult.setData(pigAvList);
        return baseResult;
    }
}
