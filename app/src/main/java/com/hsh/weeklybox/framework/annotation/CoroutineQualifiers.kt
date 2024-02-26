package com.hsh.weeklybox.framework.annotation

import javax.inject.Qualifier

// [1] Dispatcher Qualifiers

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainImmediateDispatcher



// [2] Scope Qualifiers

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApplicationScope