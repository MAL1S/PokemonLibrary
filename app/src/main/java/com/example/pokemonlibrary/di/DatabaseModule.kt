package com.example.pokemonlibrary.di

import android.content.Context
import android.provider.DocumentsContract
import androidx.room.Room
import com.example.pokemonlibrary.data.local.PokeDB
import com.example.pokemonlibrary.data.local.PokeDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): PokeDB {
        return Room.databaseBuilder(
            context,
            PokeDB::class.java,
            "poke_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providePokeDao(pokeDB: PokeDB): PokeDao {
        return pokeDB.getPokeDao()
    }
}