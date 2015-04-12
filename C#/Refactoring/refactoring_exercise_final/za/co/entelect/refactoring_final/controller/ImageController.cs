using System;
using refactoring_exercise_final.za.co.entelect.refactoring_final.domain;
using refactoring_exercise_final.za.co.entelect.refactoring_final.service;

namespace refactoring_exercise_final.za.co.entelect.refactoring_final.controller
{
    public class ImageController
    {
        private ImageService imageService = new ImageService();

        public Image FetchImage(String id)
        {
            return imageService.Fetch(id);
        }

        public Image UploadImage(String id, byte[] data)
        {
            Image image = new Image(id, data);
            imageService.Add(image);
            return image;
        }
    }
}