import org.koin.core.component.KoinComponent

class ApiGateway: KoinComponent {

    fun callApiWithEnvUrl(): String = Properties.properties.getProperty("API_URL")
}