using System;
using System.Runtime.Serialization;

namespace refactoring_exercise_2.za.co.entelect.refactoring2.exception
{
    public class BankAccountException : Exception, ISerializable
    {
        public BankAccountException(string message) : base(message)
        {
        }
    }
}
