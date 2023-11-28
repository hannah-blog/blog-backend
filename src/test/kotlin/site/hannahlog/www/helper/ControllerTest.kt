package site.hannahlog.www.helper

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@AutoConfigureMockMvc
@SpringBootTest
class ControllerTest @Autowired constructor(
    protected val mockMvc: MockMvc,
    protected val objectMapper: ObjectMapper,
    protected val dataCleaner: DataCleaner
) {

    @AfterEach
    fun tearDown() {
        dataCleaner.clear()
    }
}