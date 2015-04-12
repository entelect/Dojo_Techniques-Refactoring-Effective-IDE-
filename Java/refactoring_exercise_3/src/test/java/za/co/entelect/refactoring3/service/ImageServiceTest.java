package za.co.entelect.refactoring3.service;

import org.junit.Before;
import org.junit.Test;
import za.co.entelect.refactoring3.domain.Image;

import static junit.framework.Assert.*;

public class ImageServiceTest {

    public static final String IMAGE_1 = "image_1";
    public static final String IMAGE_2 = "image_2";

    public static final byte[] DATA = new byte[1];
    private ImageService imageService = new ImageService();

    @Before
    public void init(){
        imageService = new ImageService();
    }

    @Test
    public void testAddImage(){
        assertEquals(1, imageService.count());
        imageService.add(new Image(IMAGE_1, DATA));
        imageService.add(new Image(IMAGE_2, DATA));
        assertEquals(3, imageService.count());
    }

    @Test
    public void testFetchImage(){
        assertNull(imageService.fetch(IMAGE_1));
        imageService.add(new Image(IMAGE_1, new byte[1]));
        Image image1 = imageService.fetch(IMAGE_1);
        assertNotNull(image1);
        assertEquals(IMAGE_1, image1.getImageId());
        assertNotNull(image1.getData());
        assertNotNull(imageService.fetch(ImageService.DEFAULT_IMAGE));
    }
}