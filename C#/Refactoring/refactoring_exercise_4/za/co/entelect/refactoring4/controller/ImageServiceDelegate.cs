using System;
using refactoring_exercise_4.za.co.entelect.refactoring4.domain;
using refactoring_exercise_4.za.co.entelect.refactoring4.service;

namespace refactoring_exercise_4.za.co.entelect.refactoring4.controller
{

    public class ImageServiceDelegate
    {

        private ImageService imageService = new ImageService();

        public Image Fetch(String id)
        {
            return imageService.Fetch(id);
        }

        public int Count()
        {
            return imageService.Count();
        }

        public void Add(Image image)
        {
            imageService.Add(image);
        }
    }
}