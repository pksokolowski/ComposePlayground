package com.github.pksokolowski.composeplayground.di

import com.github.pksokolowski.composeplayground.samples.counterSample.CounterSample
import com.github.pksokolowski.composeplayground.samples.motionLayoutSample2.MotionLayoutAnchorSample2
import com.github.pksokolowski.composeplayground.samples.motionLayoutSample.MotionLayoutWithAnchorSample
import com.github.pksokolowski.composeplayground.samples.Sample
import org.koin.dsl.bind
import org.koin.dsl.module

val samplesModule = module {
    factory { getAll<Sample>() }

    factory { MotionLayoutWithAnchorSample() } bind Sample::class
    factory { MotionLayoutAnchorSample2() } bind Sample::class
    factory { CounterSample() } bind Sample::class
}