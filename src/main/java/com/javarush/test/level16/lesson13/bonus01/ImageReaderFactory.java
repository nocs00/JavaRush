package com.javarush.test.level16.lesson13.bonus01;

import com.javarush.test.level16.lesson13.bonus01.common.*;

/**
 * Created by pdudenkov on 23.11.15.
 */
public class ImageReaderFactory
{
    public static ImageReader getReader(ImageTypes imageType) {
        ImageReader imageReader = null;

        if (imageType == ImageTypes.BMP)
            imageReader = new BmpReader();
        else if (imageType == ImageTypes.JPG)
            imageReader = new JpgReader();
        else if (imageType == ImageTypes.PNG)
            imageReader = new PngReader();
        else
            throw new IllegalArgumentException("Неизвестный тип картинки");

        return imageReader;
    }
}
