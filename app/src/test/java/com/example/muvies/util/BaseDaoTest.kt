package com.example.muvies.util

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.muvies.data.local.MovieDatabase
import com.example.muvies.data.local.dao.MovieDao
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import org.junit.After
import org.junit.Before
import org.junit.Rule

abstract class BaseDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var db: MovieDatabase
    lateinit var dao: MovieDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.movieDao()
    }

    @After
    fun closeDb() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()

        db.close()
    }
}
