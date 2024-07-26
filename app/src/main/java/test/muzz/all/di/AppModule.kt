package test.muzz.all.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import test.muzz.all.multithreading.Needle
import test.muzz.all.multithreading.ProdNeedle

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {
    @Binds
    fun bindNeedle(needle: ProdNeedle): Needle
}