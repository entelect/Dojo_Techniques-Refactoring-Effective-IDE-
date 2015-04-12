using System;
using System.Runtime.Serialization;

namespace refactoring_exercise_3.za.co.entelect.refactoring3.exception
{
    public class BankAccountException : Exception, ISerializable
    {
        public BankAccountException(string message) : base(message)
        {
        }
    }
}
