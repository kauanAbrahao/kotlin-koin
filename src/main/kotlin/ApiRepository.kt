import org.koin.core.component.KoinComponent

class ApiRepository: KoinComponent {

    fun save(response: String){
        println("saved $response")
    }
}