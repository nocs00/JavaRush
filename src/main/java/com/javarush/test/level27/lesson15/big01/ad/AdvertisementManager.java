package com.javarush.test.level27.lesson15.big01.ad;

import com.javarush.test.level27.lesson15.big01.ConsoleHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by pdudenkov on 23.07.2016.
 */
public class AdvertisementManager
{
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    private int timeSeconds;

    public AdvertisementManager(int timeSeconds)
    {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        List<Advertisement> preparedVideos = findOptimalVideoList(timeSeconds);
        if (!preparedVideos.isEmpty()) {
            Collections.sort(preparedVideos, comparator());
            showVideos(preparedVideos);
        } else {
            throw new NoVideoAvailableException();
        }
    }

    private Comparator<Advertisement> comparator() {
        return new Comparator<Advertisement>()
        {
            @Override
            public int compare(Advertisement o1, Advertisement o2)
            {
                long profit1 = o1.getAmountPerOneDisplaying();
                long profit2 = o2.getAmountPerOneDisplaying();
                if (profit1 != profit2)
                {
                    return Long.compare(profit2, profit1);
                }
                long profitPerSecond1 = profitPerSecond(profit1, o1.getDuration());
                long profitPerSecond2 = profitPerSecond(profit2, o2.getDuration());
                return Long.compare(profitPerSecond1, profitPerSecond2);
            }
        };
    }

    private void showVideos(List<Advertisement> preparedVideos) {
        for (Advertisement advertisement : preparedVideos)
        {
            String template = "%s is displaying... %d, %d";
            String name = advertisement.getName();
            long profit = advertisement.getAmountPerOneDisplaying();
            long profitOneSecond = profitPerSecond(profit, advertisement.getDuration());

            ConsoleHelper.writeMessage(String.format(template, name, profit, profitOneSecond));
            advertisement.revalidate();
        }
    }

    private long profitPerSecond(long profit, int time) {
        return (profit*1000) / time;
    }

    private List<Advertisement> findOptimalVideoList(int time) {
        return storage.list();
    }
}
