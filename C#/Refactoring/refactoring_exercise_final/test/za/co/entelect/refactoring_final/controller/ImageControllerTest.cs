using NUnit.Framework;
using refactoring_exercise_final.za.co.entelect.refactoring_final.controller;
using refactoring_exercise_final.za.co.entelect.refactoring_final.service;

namespace refactoring_exercise_final.test.za.co.entelect.refactoring_final.controller
{
    public class ImageControllerTest
    {
        private readonly ImageController _imageController = new ImageController();

        [Test]
        public void TestFetchImage(){
            Assert.NotNull(_imageController.FetchImage(ImageService.DefaultImage));
        }

        [Test]
        public void TestUploadImage(){
            Assert.NotNull((_imageController.UploadImage("1", new byte[1])));
        }
    }
}