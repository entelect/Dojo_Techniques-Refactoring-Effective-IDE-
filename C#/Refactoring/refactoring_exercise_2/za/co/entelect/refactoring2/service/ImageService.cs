using System;
using System.Collections.Generic;
using refactoring_exercise_2.za.co.entelect.refactoring2.domain;

namespace refactoring_exercise_2.za.co.entelect.refactoring2.service
{


    /**
     * Basic in memory implementation to store images
    */
    public class ImageService
    {

        public const string DefaultImage = "defaultImage";
        private readonly Dictionary<string, Image> _images = new Dictionary<string, Image>();

        public ImageService()
        {
            _images.Add(DefaultImage, new Image(DefaultImage, new byte[1]));
        }

        public Image Fetch(String id)
        {
            if (!_images.ContainsKey(id))
            {
                return null;
            }
            return _images[id];
        }

        public void Add(Image image)
        {
            _images.Add(image.ImageId, image);
        }

        public int Count()
        {
            return _images.Count;
        }
    }
}