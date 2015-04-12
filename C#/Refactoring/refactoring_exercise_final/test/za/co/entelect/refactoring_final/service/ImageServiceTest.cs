using NUnit.Framework;
using NUnit.Framework.Constraints;
using refactoring_exercise_final.za.co.entelect.refactoring_final.domain;
using refactoring_exercise_final.za.co.entelect.refactoring_final.service;

namespace refactoring_exercise_final.test.za.co.entelect.refactoring_final.service
{
    [TestFixture]
    public class ImageServiceTest {

        public const string Image1 = "image_1";
        public const string Image2 = "image_2";

        public static readonly byte[] Data = new byte[1];
        private ImageService _imageService = new ImageService();

        [SetUp]
        public void Init(){
            _imageService = new ImageService();
        }

        [Test]
        public void TestAddImage(){
            Assert.That(_imageService.Count(), new EqualConstraint(1));
            _imageService.Add(new Image(Image1, Data));
            _imageService.Add(new Image(Image2, Data));
            Assert.That(_imageService.Count(), new EqualConstraint(3));
        }

        [Test]
        public void TestFetchImage(){
            Assert.Null(_imageService.Fetch(Image1));
            _imageService.Add(new Image(Image1, new byte[1]));
            Image image1 = _imageService.Fetch(Image1);
            Assert.NotNull(image1);
            Assert.That(image1.ImageId, new EqualConstraint(Image1));
            Assert.NotNull(image1.Data);
            Assert.NotNull(_imageService.Fetch(ImageService.DefaultImage));
        }
    }
}