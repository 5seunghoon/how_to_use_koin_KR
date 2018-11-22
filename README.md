# how_to_use_koin_KR


### 1. View

    private val mViewModel:MainViewModel by viewModel()


Koin의 viewModel()을 통해 의존성 주입


### 2. ViewModel

    class MainViewModel(private val modelImpl: HelloDataModel) : ViewModel() {}

android.arch.lifecycle.ViewModel을 상속, 생성자로 Model을 받도록 구현


### 3. Model


    override fun printHello() : HelloKotlinData {  
        return HelloKotlinData("KOTLIN", System.currentTimeMillis())  
    }  
    
  간단하게 Data를 반환 


### 4. Data

    data class HelloKotlinData(var name: String, var time: Long)

코틀린의 data class 이용


### 5. AppModule

    val MyModule : Module = module {  
      factory <HelloDataModel> {  
        HelloDataModelImpl()  
      }  
      
      viewModel {  
        MainViewModel(get()) as MainViewModel  
      }  
    }  
      
    val MyAppModule = listOf(MyModule)

객채간의 관계를 정의

factory로 DataModel을 만들고 viewModel에서 get()을 통해 DI 수행


### 6. Application class

    startKoin(context, AppModule)

startKoin의 인자로 컨텍스트와 AppModule을 넘겨줌으로서 DI 수행


### 번외 - RxJava (PublishSubject)
1.

    var observableData: PublishSubject<String> = PublishSubject.create()

> ViewModel에 PublishSubject를 두고,


2.

    fun changeData() {  
      observableData.onNext(  
        """  
        HELLO. ${modelImpl.printHello().name} DATA! 
        It's ${modelImpl.printHello().time} time
        """.trimIndent()  
        )  
    }

> PublishSubject.onNext() 를 통해 PublishSubject를 변경


3.

    addDisposable(mViewModel.observableData.subscribe{  
      kotlin_textview.text = it  
    })
    

> 그 후 Activity에서 해당 PublishSubject를 관찰하며 변경사항이 있으면 textView 변경

4.

    kotlin_button.setOnClickListener { mViewModel.changeData() }

> 버튼을 누르면 ViewModel의 ChangeData를 호출하여 PublishSubject를 변경


이렇게 하여 버튼을 눌렸을 때 데이터가 변경되고, 그것을 View가 관찰하며 즉각적인 반응을 할 수 있게 구현 완료
