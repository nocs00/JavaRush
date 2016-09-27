package com.javarush.test.level28.lesson15.big01.model;

import com.javarush.test.level28.lesson15.big01.view.View;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pdudenkov on 29.06.2016.
 */
public class Model
{
    private View view;
    private Provider[] providers;

    public Model(View view, Provider ... providers)
    {
        if (view == null || providers.length == 0) {
            throw new IllegalArgumentException();
        }

        this.view = view;
        this.providers = providers;
    }

    public void selectCity(String city) {
        List<Vacancy> vacancies = new ArrayList<>();

        for (Provider provider : providers)
        {
            vacancies.addAll(provider.getJavaVacancies(null));
        }

        view.update(vacancies);
    }
}
