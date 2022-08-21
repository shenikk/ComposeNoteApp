package com.composenoteapp.di

import android.app.Application
import androidx.room.Room
import com.composenoteapp.data.NoteRepositoryImpl
import com.composenoteapp.data.database.NoteDatabase
import com.composenoteapp.domain.NoteInteractor
import com.composenoteapp.domain.NoteInteractorImpl
import com.composenoteapp.domain.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDataBase(app: Application): NoteDatabase =
        Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            "noteTable"
        ).build()

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository =
        NoteRepositoryImpl(db.noteDao)

    @Provides
    @Singleton
    fun provideNoteInteractor(repository: NoteRepository): NoteInteractor =
        NoteInteractorImpl(repository)
}
