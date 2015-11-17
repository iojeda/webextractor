package main;

import java.util.StringTokenizer;

/**
 * Created by Ismael Ojeda Perez on 15/11/2015.
 */
public class Article {

    private final String date;
    private final String img;
    private final String name;
    private final String opportunity;
    private final String newArticle;
    private final String unknown;
    private final String price;
    private final String group;

    public Article(String date, String img, String name, String opportunity, String newArticle, String unknown, String price, String group) {
        this.date = date;
        this.img = img;
        this.name = name;
        this.opportunity = opportunity;
        this.newArticle = newArticle;
        this.unknown = unknown;
        this.price = price;
        this.group = group;
    }

    public String getDate() {
        return date;
    }

    public String getImg() {
        return img;
    }

    public String getImgUrl()
    {
        StringTokenizer sc = new StringTokenizer(img,"'");
        sc.nextToken();
        return sc.nextToken();
    }

    public String getName() {
        return name;
    }

    public String getOpportunity() {
        return opportunity;
    }

    public String getNewArticle() {
        return newArticle;
    }

    public String getUnknown() {
        return unknown;
    }

    public String getPrice() {
        return price;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Article{" +
                "date='" + date + '\'' +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", opportunity='" + opportunity + '\'' +
                ", newArticle='" + newArticle + '\'' +
                ", unknown='" + unknown + '\'' +
                ", price='" + price + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}

