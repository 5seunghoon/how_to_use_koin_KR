# How to use Koin for DI (KR) 


### 1. View (MainActivity)

    private val mViewModel:MainViewModel by viewModel()


Koin의 viewModel()을 통해 의존성 주입


### 2. ViewModel (MainViewModel)

    class MainViewModel(private val modelImpl: HelloDataModel) : ViewModel() {}

android.arch.lifecycle.ViewModel을 상속, 생성자로 Model을 받도록 구현


### 3. Model (MainDataModel)


    override fun printHello() : HelloKotlinData {  
        return HelloKotlinData("KOTLIN", System.currentTimeMillis())  
    }  
    
요청시 간단하게 Data를 반환하는 모델 


### 4. Data (HelloKotlinData)

    data class HelloKotlinData(var name: String, var time: Long)

간단하게 data class 이용


### 5. AppModule (AppModule.kt - MyAppModule)

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

factory로 Model을 만들고 viewModel{}에서 get()을 통해 factory로 만든 modeldmf 가져옴으로서 DI 수행


### 6. Application class (MyApplication)

    startKoin(context, MyAppModule)

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


이렇게 하여 버튼을 눌렸을 때 데이터가 변경되고, 그것을 View가 관찰하며 즉각적이게 반응할 수 있다..
