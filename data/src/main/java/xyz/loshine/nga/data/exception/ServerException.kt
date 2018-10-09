package xyz.loshine.nga.data.exception

class ServerException(val code: Int, message: String) : Exception(message)