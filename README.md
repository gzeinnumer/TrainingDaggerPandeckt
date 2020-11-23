# TrainingDaggerPandecKT
 Training tanggal 21 Mei 2020 Sabtu 16:00

[Kapt](https://github.com/gzeinnumer/KaptExample)

- TODO 1 
```gradle
//library
def dagger_version = "2.25.2"
implementation "com.google.dagger:dagger:$dagger_version"
kapt "com.google.dagger:dagger-compiler:$dagger_version"
implementation "com.google.dagger:dagger-android:$dagger_version"
implementation "com.google.dagger:dagger-android-support:$dagger_version"
kapt "com.google.dagger:dagger-android-processor:$dagger_version"

def retrofit_version = "2.5.0"
implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"
implementation 'com.google.code.gson:gson:2.8.5'
```

#
- TODO 2
```kotlin
class MyApp : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return null
    }
}
```

#
- TODO 3
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest>

    ...

    <application
        android:name=".MyApp">

        ...

    </application>

</manifest>
```

#
- TODO 4
```kotlin
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
    ]
)
interface AppComponent : AndroidInjector<MyApp> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application) : Builder

        fun build(): AppComponent
    }
}
```

#
- TODO 5 Rebuild

#
- TODO 6
```kotlin
class MyApp : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build();
    }
}
```

#
- TODO 7
```kotlin
@Module
abstract class ActivityBuildersModule{
    @ContributesAndroidInjector
    abstract fun constributesMainActivity() : MainActivity
}
```

#
- TODO 8
```kotlin
 @Singleton
 @Component(
     modules = [
         AndroidSupportInjectionModule::class,
         //todo 8
         ActivityBuildersModule::class,
         //end todo 8
     ]
 )
 interface AppComponent : AndroidInjector<MyApp> {

     @Component.Builder
     interface Builder{

         @BindsInstance
         fun application(application: Application) : Builder

         fun build(): AppComponent
     }
 }

```

#
- TODO 9 DaggerAppCompatActivity
```kotlin
class MainActivity : DaggerAppCompatActivity() {

    ...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
```

#
- TODO 10
```kotlin
@Module
class AppModule {
}
```

#
- TODO 11
```kotlin
 @Singleton
 @Component(
     modules = [
         AndroidSupportInjectionModule::class,
         ActivityBuildersModule::class,
         //todo 11
         AppModule::class
         //end todo 11
     ]
 )
 interface AppComponent : AndroidInjector<MyApp> {
    ...
 }
```

#
- TODO 12
```kotlin
@Module
class AppModule {

    //todo 12
    @Singleton
    @Provides
    fun providesString(): String {
        return "ProvideString"
    } //end todo 12
}
```

#
- TODO 13
```kotlin
class MainActivity : DaggerAppCompatActivity() {

    //todo 13
    @Inject
    lateinit var str: String

    override fun onCreate(savedInstanceState: Bundle?) {

        ...

    }
}
```

#
- TODO 14
```kotlin
class MainActivity : DaggerAppCompatActivity() {

    @Inject
    String str;

    override fun onCreate(savedInstanceState: Bundle?) {

        //todo 14
        myLogD(TAG, "String Inject : "+ str);

    }
}
```

#
- TODO 15
```kotlin
@Module
class AppModule {

    //todo 15
    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    } //end todo 15
}
```

#
- TODO 16
```kotlin
class MainActivity : DaggerAppCompatActivity() {

    //todo 16
    @Inject
    lateinit var context: Context

    ...
}
```

#
- TODO 17
```kotlin
class MainActivity : DaggerAppCompatActivity() {

    @Inject
    Context context;

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main);

        ..

        //todo 17
        Toast.makeText(context, "String Inject : "+ str, Toast.LENGTH_LONG).show();
    }
}
```

#
- TODO 18
```kotlin
data class ResponsePost(

	@field:SerializedName("id")
    var id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)
```

#
- TODO 19
```kotlin
interface ApiService {

    //https://jsonplaceholder.typicode.com/posts?userId=1
    @GET("posts")
    fun getPost(
        @Query("userId") id: Int
    ): Call<List<ResponsePost>>
}
```

#
- TODO 20
```kotlin
@Module
class AppModule {

    //todo 20
    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    } //end todo 20
}
```

#
- TODO 21
```kotlin
class MainActivity : DaggerAppCompatActivity() {

    //todo 21
    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
```

#
- TODO 22
```kotlin
class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //todo 22
        apiService.getPost(1)
            .enqueue(object : Callback<List<ResponsePost>> {
                override fun onResponse(call: Call<List<ResponsePost>>, response: Response<List<ResponsePost>>) {
                    myLogD(TAG, "ApiService Inject(Success) : " + response.body()!!.size)
                }

                override fun onFailure(call: Call<List<ResponsePost>>, t: Throwable) {
                    myLogD(TAG, "ApiService Inject(error) : " + t.message)
                }
            })
    }
}
```

#
- TODO 23
```kotlin
//todo 23
class SessionManager (private val context: Context) {

    private val TAG = "SessionManager"

    companion object {
        private const val PREF_NAME = "session"
        private const val KEY_ID = "id"
    }

    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val editor = prefs.edit()

    fun setId(id: Int) {
        editor.putInt(KEY_ID, id).apply()
    }

    fun getId(): Int = prefs.getInt(KEY_ID, 0)
}
```

#
- TODO 24
```kotlin
@Module
class AppModule {

    //todo 24
    @Singleton
    @Provides
    fun providesSessionManager(context: Context): SessionManager {
        return SessionManager(context)
    } //end todo 24
}
```

#
- TODO 25
```kotlin
class MainActivity : DaggerAppCompatActivity() {

    //todo 25
    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ...

    }
}
```

#
- TODO 26
```kotlin
class MainActivity : DaggerAppCompatActivity() {

    //todo 25
    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ...


        //todo 26
//        sessionManager = SessionManager(applicationContext);
        sessionManager.setId(1)
        Toast.makeText(context, sessionManager.getId().toString(), Toast.LENGTH_SHORT).show()
        myLogD(TAG, "Session Inject : " + sessionManager.getId())
    }
}
```

#
- TODO 27 Repository
```kotlin
@Singleton
class RepositoryExample @Inject constructor(
    private val context: Context,
    private val sessionManager: SessionManager
) {
    val TAG = "RepositoryExample"

    fun exampleFunction() {
        Toast.makeText(context, "exampleFunction", Toast.LENGTH_SHORT).show()
        myLogD(TAG, "exampleFunction : " + sessionManager.getId())
    }

}
```

#
- TODO 28
```kotlin
class MainActivity : DaggerAppCompatActivity() {

    //todo 28
    @Inject
    lateinit var repositoryExample: RepositoryExample

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ...

        //todo 29
//        val mySession = SessionManager(applicationContext);
//        repositoryExample = RepositoryExample(applicationContext, mySession);
        repositoryExample.exampleFunction()
    }
}
```

---

```
Copyright 2020 M. Fadli Zein
```
