package com.example.secureauthentication.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.secureauthentication.reposistory.DataStoreRepository
import com.example.secureauthentication.utils.State
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class SignInViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()

    @MockK
    lateinit var dataStoreRepository: DataStoreRepository

    private lateinit var viewModel: SignInViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        viewModel = SignInViewModel(dataStoreRepository)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun testSignInUserSuccess() {
        runBlocking {
            val channel = Channel<State<Boolean>>()
            launch {
                channel.send(State.Success(true))
            }
            val flow = channel.consumeAsFlow()
            coEvery { dataStoreRepository.signInUser(any(), any()) } returns flow

            viewModel.signInUser("name", "password")
            Assert.assertNotNull(viewModel.onSignInUserSuccess.value)
            Assert.assertTrue(viewModel.onSignInUserSuccess.value == true)
        }
    }

    @Test
    fun testSignInUserError() {
        runBlocking {
            val errorMessage = "something went wrong"
            val channel = Channel<State<Boolean>>()
            launch {
                channel.send(State.Error(errorMessage))
            }
            val flow = channel.consumeAsFlow()
            coEvery { dataStoreRepository.signInUser(any(), any()) } returns flow

            viewModel.signInUser("name", "password")
            Assert.assertNull(viewModel.onSignInUserSuccess.value)
            Assert.assertNotNull(viewModel.onSignInUserError.value)
            Assert.assertEquals(viewModel.onSignInUserError.value, errorMessage)
        }
    }

    @Test
    fun testSignInUserFailure() {
        runBlocking {
            val channel = Channel<State<Boolean>>()
            launch {
                channel.send(State.Failed(Exception()))
            }
            val flow = channel.consumeAsFlow()
            coEvery { dataStoreRepository.signInUser(any(), any()) } returns flow

            viewModel.signInUser("name", "password")
            Assert.assertNotNull(viewModel.onSignInUserSuccess.value)
            Assert.assertFalse(viewModel.onSignInUserSuccess.value == true)
        }
    }
}