package za.co.entelect.refactoring1.controller;

import za.co.entelect.refactoring1.domain.Image;
import za.co.entelect.refactoring1.service.ImageService;


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
