package za.co.entelect.refactoring3.service;

import za.co.entelect.refactoring3.domain.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Basic in memory implementation to store images
 */
public class ImageService {

    public static final String DEFAULT_IMAGE = "defaultImage";
    private Map<String, Image> images = new HashMap<String, Image>();

    public ImageService(){
        images.put(DEFAULT_IMAGE, new Image(DEFAULT_IMAGE, new byte[1]));
    }

    public Image fetch(String id){
        return images.get(id);
    }

    public void add(Image image){
        images.put(image.getImageId(), image);
    }

    public int count(){
        return images.size();
    }
}