namespace refactoring_exercise_1.za.co.entelect.refactoring1.domain
{
    public class Image
    {
        private readonly string _imageId;
        private readonly byte[] _data;

        public Image(string imageId, byte[] data)
        {
            this._imageId = imageId;
            this._data = data;
        }

        public string ImageId
        {
            get { return _imageId; }
        }

        public byte[] Data
        {
            get { return _data; }
        }
    }
}
