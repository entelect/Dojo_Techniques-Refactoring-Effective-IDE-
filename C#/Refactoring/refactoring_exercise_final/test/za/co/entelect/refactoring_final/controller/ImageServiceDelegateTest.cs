using System;
using NUnit.Framework;
using NUnit.Framework.Constraints;
using refactoring_exercise_final.za.co.entelect.refactoring_final.controller;
using refactoring_exercise_final.za.co.entelect.refactoring_final.domain;
using refactoring_exercise_final.za.co.entelect.refactoring_final.service;

namespace refactoring_exercise_final.test.za.co.entelect.refactoring_final.controller
{
    [TestFixture]
    public class ImageServiceDelegateTest {

        private readonly ImageServiceDelegate _imageServiceDelegate = new ImageServiceDelegate();
        public const String Image1 = "image_101";

        [Test]
        public void TestAddImage(){
            Assert.That(_imageServiceDelegate.Count(), new EqualConstraint(1));
            _imageServiceDelegate.Add(new Image(Image1, new byte[]{}));
            Assert.That(_imageServiceDelegate.Count(), new EqualConstraint(2));
        }

        [Test]
        public void TestFetchImage(){
            var actual = _imageServiceDelegate.Fetch(ImageService.DefaultImage);
            Assert.NotNull(actual);
        }
    }
}