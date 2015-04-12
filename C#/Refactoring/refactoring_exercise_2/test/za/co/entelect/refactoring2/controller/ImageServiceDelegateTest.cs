using System;
using NUnit.Framework;
using NUnit.Framework.Constraints;
using refactoring_exercise_2.za.co.entelect.refactoring2.controller;
using refactoring_exercise_2.za.co.entelect.refactoring2.domain;
using refactoring_exercise_2.za.co.entelect.refactoring2.service;

namespace refactoring_exercise_2.test.za.co.entelect.refactoring2.controller
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