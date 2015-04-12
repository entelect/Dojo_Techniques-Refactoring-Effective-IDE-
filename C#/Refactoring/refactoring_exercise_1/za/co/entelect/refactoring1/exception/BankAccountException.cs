using System;
using System.Runtime.Serialization;

namespace refactoring_exercise_1.za.co.entelect.refactoring1.exception
{
    public class BankAccountException : Exception, ISerializable
    {
        public BankAccountException(string message) : base(message)
        {
        }
    }
}
