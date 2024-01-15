import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject

fun main() {
    Properties.loadProperties()
    startKoin {
        modules(appModule)
    }

    val apiService: ApiService by inject(ApiService::class.java)
    val functionUtils: FunctionUtils by inject(FunctionUtils::class.java)

    println( apiService.callApi())


    val result = makeSomeMath(2, 4) { a: Int, b: Int -> a * b } // 8
    val result2 = makeSomeMath(4, 4, FunctionUtils()::multiplication) // 16
    val result3 = makeSomeMath(10, 9, FunctionUtils()::onePlusAnother) // 19
    val result4 = makeSomeMath(10, 9, FunctionUtils.ObjectFunctionUtils::multiplication) // 90
    val result5 = makeSomeMath(10, 10, functionUtils::onePlusAnother) // 20
    val result6 = makeSomeMath(20, 30) { a: Int, b:Int ->
        val mathResult = a+b
        mathResult.toDouble()
    } //50.0



    println("${result.javaClass.simpleName}  $result")
    println("${result2.javaClass.simpleName}  $result2")
    println("${result3.javaClass.simpleName}  $result3")
    println("${result4.javaClass.simpleName}  $result4")
    println("${result5.javaClass.simpleName}  $result5")
    println("${result6.javaClass.simpleName}  $result6")


}

val appModule: Module = module {
    single { ApiGateway() }
    single { ApiRepository() }
    single { ApiService(get(), get()) }
    single {FunctionUtils()}
}

private fun makeSomeMath(a: Int, b: Int, mathFunction: (Int, Int) -> Any): Any{
    return mathFunction(a, b)
}
