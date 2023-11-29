package site.hannahlog.www.helper

import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ServiceTest {

    @Autowired
    lateinit var dataCleaner: DataCleaner

    @AfterEach
    fun tearDown() {
        dataCleaner.clear()
    }
}
