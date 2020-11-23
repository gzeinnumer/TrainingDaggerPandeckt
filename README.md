# TrainingDaggerPandec
 Training tanggal 21 Mei 2020 Sabtu 16:00

- TODO 1
```gradle
//library
def dagger_version = "2.25.2"
implementation "com.google.dagger:dagger:$dagger_version"
annotationProcessor "com.google.dagger:dagger-compiler:$dagger_version"
implementation "com.google.dagger:dagger-android:$dagger_version"
implementation "com.google.dagger:dagger-android-support:$dagger_version"
annotationProcessor "com.google.dagger:dagger-android-processor:$dagger_version"
def retrofit_version = "2.5.0"
implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"
implementation 'com.google.code.gson:gson:2.8.5'
```

#
- TODO 2
```java
public class MyApp extends DaggerApplication {

   @Override
   protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
       return null;
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
```java
//todo 4
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
        }
)
public interface AppComponent extends AndroidInjector<MyApp> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
```

#
- TODO 5 Rebuild

#
- TODO 6
```java
public class MyApp extends DaggerApplication {

   @Override
   protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
       // todo 6
       return DaggerAppComponent.builder().application(this).build();
   }
}
```

#
- TODO 7
```java
@Module
public abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract MainActivity contributesMainActivity();
}
```

#
- TODO 8
```java
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                //todo 8
                ActivityBuildersModule.class,
                //end todo 8
        }
)
public interface AppComponent extends AndroidInjector<MyApp> {

    ...

}
```

#
- TODO 9 DaggerAppCompatActivity
```java
public class MainActivity extends DaggerAppCompatActivity {

    ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
```

#
- TODO 10
```java
@Module
class AppModule {
}
```

#
- TODO 11
```java
@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
                //todo 11
                AppModule.class
                //end todo 11
        }
)
public interface AppComponent extends AndroidInjector<MyApp> {

    ...

}
```

#
- TODO 12
```java
@Module
class AppModule {

    //todo 12
    @Singleton
    @Provides
    static String providesString(){
        return "ProvideString";
    }//end todo 12
}
```

#
- TODO 13
```java
public class MainActivity extends DaggerAppCompatActivity {

    //todo 13
    @Inject
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ...

    }
}
```

#
- TODO 14
```java
public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //todo 14
        myLogD(TAG, "String Inject : "+ str);

    }
}
```

#
- TODO 15
```java
@Module
class AppModule {

    //todo 15
    @Singleton
    @Provides
    static Context provideContext(Application application){
        return application.getApplicationContext();
    }//end todo 15
}
```

#
- TODO 16
```java
public class MainActivity extends DaggerAppCompatActivity {

    //todo 16
    @Inject
    Context context;

    ...
}
```

#
- TODO 17
```java
public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ..

        //todo 17
        Toast.makeText(context, "String Inject : "+ str, Toast.LENGTH_LONG).show();
    }
}
```

#
- TODO 18
```java
public class ResponsePost {

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("body")
	private String body;

	@SerializedName("userId")
	private int userId;
	//getter setter
}
```

#
- TODO 19
```java
public interface ApiService {

    //https://jsonplaceholder.typicode.com/posts?userId=1
    @GET("posts")
    Call<List<ResponsePost>> getPost(
            @Query("userId") int id
    );
}
```

#
- TODO 20
```java
@Module
class AppModule {

    //todo 20
    @Provides
    @Singleton
    static Retrofit providesRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
```

#
- TODO 21
```java
public class MainActivity extends DaggerAppCompatActivity {

    //todo 21
    @Inject
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
```

#
- TODO 22
```java
public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //todo 22
        apiService.getPost(1).enqueue(new Callback<List<ResponsePost>>() {
            @Override
            public void onResponse(Call<List<ResponsePost>> call, Response<List<ResponsePost>> response) {
                myLogD(TAG, "ApiService Inject(Success) : "+ response.body().size());
            }

            @Override
            public void onFailure(Call<List<ResponsePost>> call, Throwable t) {
                myLogD(TAG, "ApiService Inject(error) : "+ t.getMessage());
            }
        });
    }
}
```

#
- TODO 23
```java
//todo 23
public class SessionManager {
    private static final String TAG = "SessionManager";

    private static String PREF_NAME = "session";
    private static String KEY_ID = "id";

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    void setId(int id){
        editor.putInt(KEY_ID, id);
        editor.apply();
    }

    int getId(){
        return prefs.getInt(KEY_ID,0);
    }
}
```

#
- TODO 24
```java
@Module
class AppModule {

    //todo 24
    @Singleton
    @Provides
    static SessionManager providesSessionManager(Context context){
        return new SessionManager(context);
    }
    //end todo 24
}
```

#
- TODO 25
```java
public class MainActivity extends DaggerAppCompatActivity {

    //todo 25
    @Inject
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ...

    }
}
```

#
- TODO 26
```java
public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ...

        //todo 26
        //sessionManager = new SessionManager(getApplicationContext());
        sessionManager.setId(1);
        Toast.makeText(context, String.valueOf(sessionManager.getId()), Toast.LENGTH_SHORT).show();
        myLogD(TAG, "Session Inject : "+ sessionManager.getId());
    }
}
```

#
- TODO 27 Repository
```java
@Singleton
public class RepositoryExample {
    private static final String TAG = "RepositoryExample";

    private SessionManager sessionManager;
    private Context context;

    @Inject
    RepositoryExample(Context context, SessionManager sessionManager){
        this.context = context;
        this.sessionManager = sessionManager;
    }

    public void exampleFunction(){
        Toast.makeText(context, "exampleFunction", Toast.LENGTH_SHORT).show();
        myLogD(TAG, "exampleFunction : "+ sessionManager.getId());
    }
}
```

#
- TODO 28
```java
public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    RepositoryExample repositoryExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ...

        //todo 29
        //SessionManager mySession = new SessionManager(getApplicationContext());
        //repositoryExample = new RepositoryExample(getApplicationContext(), mySession);
        repositoryExample.exampleFunction();
    }
}
```

---

```
Copyright 2020 M. Fadli Zein
```
