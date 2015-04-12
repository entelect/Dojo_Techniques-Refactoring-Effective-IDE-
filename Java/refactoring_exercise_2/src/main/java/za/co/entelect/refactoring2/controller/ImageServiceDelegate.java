package za.co.entelect.refactoring2.controller;

import za.co.entelect.refactoring2.domain.Image;
import za.co.entelect.refactoring2.service.ImageService;


/**
 * Exercise 4:
 * This code ImageServiceDelegate suffers from the following code smell
 *
 * Middle Man
 */
public class ImageServiceDelegate {

   private ImageService imageService = new ImageService();

    public Image fetch(String id) {
        return imageService.fetch(id);
    }

    public int count() {
        return imageService.count();
    }

    public void add(Image image) {
        imageService.add(image);
    }
}
