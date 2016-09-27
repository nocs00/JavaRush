package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by pdudenkov on 27.06.2016.
 */
public class HHStrategy implements Strategy
{
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString)
    {
        List<Vacancy> vacancies = new ArrayList<>();
        Document document = null;
        try {
            document = getDocument("http://javarush.ru/testdata/big28data.html?page=%d", 0);
            Elements elementsByAttribute = document.select("tr[data-qa=vacancy-serp__vacancy]");
            for (Element element : elementsByAttribute)
            {
                String title = element.select("[data-qa=vacancy-serp__vacancy-title]").text();
                String salary = element.select("[data-qa=vacancy-serp__vacancy-compensation]").text();
                String city = element.select("[data-qa=vacancy-serp__vacancy-address]").text();
                String companyName = element.select("[data-qa=vacancy-serp__vacancy-employer]").text();
                String siteName = document.title();
                String url = element.select("[data-qa=vacancy-serp__vacancy-title]").first().attr("href");

                Vacancy vacancy = new Vacancy();
                vacancy.setTitle(title);
                vacancy.setSalary(salary);
                vacancy.setCity(city);
                vacancy.setCompanyName(companyName);
                vacancy.setSiteName(siteName);
                vacancy.setUrl(url);
                vacancies.add(vacancy);
            }

            return vacancies;
        } catch (IOException e) {

        }

        return Collections.EMPTY_LIST;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Document document = Jsoup
                .connect(String.format(searchString, page))
                .userAgent("javarush")
                .referrer("javarush.ru")
                .get();

        return document;
    }
}