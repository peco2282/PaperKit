package com.github.peco2282.paperkit.internals

/**
 * Marks a class or interface for debugging purposes.
 * Classes annotated with @Debugged are considered to be in development/testing phase.
 * This annotation is retained at runtime and can only be applied to classes.
 *
 * @author peco2282
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@MustBeDocumented
annotation class Debugged
