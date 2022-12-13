package com.example.secureauthentication


import androidx.room.Dao
import androidx.test.filters.SmallTest
import com.example.secureauthentication.application.MainApplication
import com.example.secureauthentication.database.AuthDatabase
import com.example.secureauthentication.database.dao.UserInfoDao
import com.example.secureauthentication.reposistory.DataStoreRepository
import com.example.secureauthentication.utils.Decryptor
import com.example.secureauthentication.utils.Encryptor

import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication


import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest


class DataRepositoryUnitTest {



    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var   authDatabase : AuthDatabase

    lateinit var   dao: UserInfoDao

    @Inject
    lateinit var  encryptor: Encryptor

    @Inject
    lateinit var  decryptor: Decryptor

    @Inject
    lateinit var  application: HiltTestApplication

    @Before
    fun init() {
        hiltRule.inject()
        dao= authDatabase.userInfoDao()
    }
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun check_save_data_success(){

    val repository = DataStoreRepository(dao,application, encryptor = encryptor, decryptor = decryptor)
        repository.encryptString("fsdfk")

    }
}