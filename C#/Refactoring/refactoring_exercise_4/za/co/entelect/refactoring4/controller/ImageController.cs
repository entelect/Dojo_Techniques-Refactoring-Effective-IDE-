using System;
using refactoring_exercise_4.za.co.entelect.refactoring4.domain;
using refactoring_exercise_4.za.co.entelect.refactoring4.service;

namespace refactoring_exercise_4.za.co.entelect.refactoring4.controller
{
    public class ImageController
    {
        private readonly ImageService imageService = new ImageService();

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