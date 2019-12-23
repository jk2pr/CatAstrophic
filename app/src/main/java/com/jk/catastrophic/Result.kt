package com.jk.catastrophic

sealed class Result<out T: Any> {
  internal data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}