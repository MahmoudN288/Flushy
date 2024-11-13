package com.elTohamy.flushy.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.elTohamy.flushy.data.local.database.FlushyDatabase
import com.elTohamy.flushy.data.remote.api.FlushyApi
import com.elTohamy.flushy.data.remote.api.NewsApi
import com.elTohamy.flushy.data.remote.retrofitInstance.RequestNewsInterceptor
import com.elTohamy.flushy.data.remote.retrofitInstance.RequestRapidInterceptor
import com.elTohamy.flushy.data.repos.favouriteLeagues.FavouriteLeaguesRepositoryImpl
import com.elTohamy.flushy.domain.repository.FavouriteLeaguesRepository
import com.elTohamy.flushy.domain.use_case.favouriteLeagues.AddFavouriteLeagues
import com.elTohamy.flushy.domain.use_case.favouriteLeagues.GetFavouriteLeagues
import com.elTohamy.flushy.domain.use_case.favouriteLeagues.RemoveFavouriteLeague
import com.elTohamy.flushy.domain.use_case.favouriteLeagues.UseCases
import com.elTohamy.flushy.services.NetworkConnectivityService
import com.elTohamy.flushy.services.NetworkConnectivityServiceImpl
import com.elTohamy.flushy.utils.Constants
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun okHttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(RequestRapidInterceptor())
            .addInterceptor(RequestNewsInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, FlushyDatabase::class.java, "flushy_database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    fun provideTaskDao(db: FlushyDatabase) = db.leaguesDao()

    @Provides
    fun provideSavedNewsDao(db: FlushyDatabase) = db.savedNewsDau()

    @Provides
    @Singleton
    fun flushyApiService(retrofit: Retrofit): FlushyApi {
        return retrofit.create(FlushyApi::class.java)
    }

    @Provides
    @Singleton
    fun newsApiService(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivityService(
        @ApplicationContext context: Context
    ): NetworkConnectivityService = NetworkConnectivityServiceImpl(context)

    @Provides
    fun provideFavouriteRef() = Firebase.firestore.collection("favourite")

    @Provides
    fun provideFavouriteRepository(
        favouriteRef: CollectionReference
    ): FavouriteLeaguesRepository = FavouriteLeaguesRepositoryImpl(favouriteRef)

    @Provides
    fun provideFavouriteLeaguesUseCases(
        repo: FavouriteLeaguesRepository
    ) = UseCases(
        getFavouriteLeagues = GetFavouriteLeagues(repo),
        addFavouriteLeagues = AddFavouriteLeagues(repo),
        removeFavouriteLeague = RemoveFavouriteLeague(repo)
    )
}