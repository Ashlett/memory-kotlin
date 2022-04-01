package com.ashlett.memory

interface Presenter<in T> {
    fun start(view: T) {}
    fun stop() {}
}
