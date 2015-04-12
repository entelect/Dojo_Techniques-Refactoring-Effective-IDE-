package za.co.entelect.refactoring_final.controller;

import org.junit.Test;
import za.co.entelect.refactoring_final.service.ImageService;

import static junit.framework.Assert.assertNotNull;

public class ImageControllerTest {

    private ImageController imageController = new ImageController();

    @Test
    public void testFetchImage(){
        assertNotNull(imageController.fetchImage(ImageService.DEFAULT_IMAGE));
    }

    @Test
    public void testUploadImage(){
        assertNotNull(imageController.uploadImage("1", new byte[1]));
    }
}
