using System;
using System.Runtime.Serialization;

namespace refactoring_exercise_4.za.co.entelect.refactoring4.exception
{
    public class BankAccountException : Exception, ISerializable
    {
        public BankAccountException(string message) : base(message)
        {
        }
    }
}
