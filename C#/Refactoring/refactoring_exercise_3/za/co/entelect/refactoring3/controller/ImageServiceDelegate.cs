using System;
using refactoring_exercise_3.za.co.entelect.refactoring3.domain;
using refactoring_exercise_3.za.co.entelect.refactoring3.service;

namespace refactoring_exercise_3.za.co.entelect.refactoring3.controller
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