import org.koin.core.component.KoinComponent

class ApiService(private val apiGateway: ApiGateway, private val apiRepository: ApiRepository): KoinComponent {

    fun callApi(): String {
        val response = apiGateway.callApiWithEnvUrl()
        apiRepository.save(response);

        return response;
    }

}