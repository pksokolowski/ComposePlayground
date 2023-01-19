package com.github.pksokolowski.composeplayground.di

import com.github.pksokolowski.composeplayground.samples.MotionLayoutWithAnchorSample
import com.github.pksokolowski.composeplayground.samples.setup.Sample
import org.koin.dsl.bind
import org.koin.dsl.module

val samplesModule = module {
    factory { getAll<Sample>() }

    factory { MotionLayoutWithAnchorSample() } bind Sample::class
}