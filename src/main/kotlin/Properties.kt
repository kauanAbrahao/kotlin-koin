import java.io.FileNotFoundException
import java.io.FileReader
import java.util.Properties
import kotlin.system.exitProcess

object Properties {

    private const val defaultEnv = "dev"
    val properties: Properties = Properties()

    fun loadProperties(){
        val environment = System.getenv("environment") ?: defaultEnv

        try{
            FileReader("environments/$environment/application.properties").use {
                properties.load(it)
            }
            println("rodando no environment -> $environment")
        } catch (ex: FileNotFoundException){
            println("Environment não existente ou mal formatado")
            exitProcess(1)
        }

    }

}