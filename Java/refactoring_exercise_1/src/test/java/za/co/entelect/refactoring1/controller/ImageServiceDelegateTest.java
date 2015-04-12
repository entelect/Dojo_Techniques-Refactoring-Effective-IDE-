package za.co.entelect.refactoring1.controller;

import org.junit.Test;
import za.co.entelect.refactoring1.domain.Image;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ImageServiceDelegateTest {

    private ImageServiceDelegate imageServiceDelegate = new ImageServiceDelegate();
    public static final String IMAGE_1 = "image_1";

    @Test
    public void testAddImage(){
        assertEquals(1, imageServiceDelegate.count());
        imageServiceDelegate.add(new Image(IMAGE_1, new byte[]{}));
        assertEquals(2, imageServiceDelegate.count());
    }

    @Test
    public void testFetchImage(){
        assertNull(imageServiceDelegate.fetch(IMAGE_1));
    }
}