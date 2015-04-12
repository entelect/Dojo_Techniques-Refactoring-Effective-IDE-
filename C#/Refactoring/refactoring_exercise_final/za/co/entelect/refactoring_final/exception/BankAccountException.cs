using System;
using System.Runtime.Serialization;

namespace refactoring_exercise_final.za.co.entelect.refactoring_final.exception
{
    public class BankAccountException : Exception, ISerializable
    {
        public BankAccountException(string message) : base(message)
        {
        }
    }
}
