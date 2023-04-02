package kristileka.anagram.domain.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DispatcherModuleTest {

    private val dispatcherModule = DispatcherModule()

    @Test
    fun `assert background coroutine scope is correct`() {
        val scope = dispatcherModule.provideBackgroundCoroutineScope()
        assert(scope.coroutineContext[SupervisorJob().key] != null)
        assert(scope.coroutineContext[Dispatchers.IO.key] != null)
    }

    @Test
    fun provideDispatcher() {
        val dispatcher = dispatcherModule.provideDispatcher()
        assertEquals(dispatcher, Dispatchers.IO)
    }
}