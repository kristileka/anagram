package kristileka.anagram.domain.di

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DispatcherModuleTest {

    private val dispatcherModule = DispatcherModule()

    @Test
    fun `assert background coroutine scope is correct`() {
        val scope = dispatcherModule.provideBackgroundCoroutineScope()
        assert(scope.coroutineContext[SupervisorJob().key] != null)
        assert(scope.coroutineContext[Dispatchers.IO.key] != null)
    }

    @Test
    fun `assert that dispatcher is of type IO`() {
        val dispatcher = dispatcherModule.provideDispatcher()
        assertEquals(dispatcher, Dispatchers.IO)
    }
}
