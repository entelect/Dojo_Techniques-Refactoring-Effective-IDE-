package za.co.entelect.refactoring_final.controller;

import za.co.entelect.refactoring_final.domain.Image;
import za.co.entelect.refactoring_final.service.ImageService;

public class ImageController {

    private static final long ACCOUNT_REOPEN_FEE_CENTS = 2000;

    private ImageService imageService = new ImageService();

    public Image fetchImage(String id) {
        return imageService.fetch(id);
    }

    public Image uploadImage(String id, byte[] data){
        Image image = new Image(id, data);
        imageService.add(image);
        return image;
    }
}
