import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class ApiSerciceTest {

    @Before
    fun setup(){
        Properties.loadProperties()
    }

    @Test
    fun printApiUrl(){
        val apiService = ApiService()
        apiService.printApiUrl()
        assert(true)
    }

    @Test
    fun assertEnvironment(){
        assertEquals(expected = "unit", System.getenv("environment"))
    }
}