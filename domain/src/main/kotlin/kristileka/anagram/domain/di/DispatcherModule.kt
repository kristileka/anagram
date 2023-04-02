package kristileka.anagram.domain.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DispatcherModule {
    @Bean
    fun provideBackgroundCoroutineScope() = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Bean
    fun provideDispatcher() = Dispatchers.IO
}
