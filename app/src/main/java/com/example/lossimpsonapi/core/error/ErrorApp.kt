package com.example.lossimpsonapi.core.error

 sealed class ErrorApp : Throwable () {

   object  ServerError : ErrorApp ()

     object EmptyBodyError : ErrorApp ()
}