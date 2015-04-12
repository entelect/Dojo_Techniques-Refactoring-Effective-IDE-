using System;
using refactoring_exercise_final.za.co.entelect.refactoring_final.domain;
using refactoring_exercise_final.za.co.entelect.refactoring_final.service;

namespace refactoring_exercise_final.za.co.entelect.refactoring_final.controller
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