# FirstLineCode

> 引言：2017年05月02日开始看郭霖的第一行代码第二版，在此过程中感觉有些地方需要代码实践一番。
>
> 时间：
>
> 作者：JustDo23
>
> 鼓励：Standing on Shoulders of Giants.

[TOC]

## 第 1 章 开始启程

### 01. Android 简史

* 2003年10月，**Andy Rubin** 等人创办 Android 公司。
* 2005年08月，**谷歌**收购该公司，Andy Rubin 继续负责。
* 2008年09月，推出了 Android 系统的**第一个**版本。
* 2011年02月，谷歌发布了 Android 3.0 系统。
* 2011年10月，谷歌发布了 Android 4.0 系统。
* 2014年 Google I/O 大会发布了 Android 5.0 系统。
* 2015年 Google I/O 大会发布了 Android 6.0 系统。
* 2016年 Google I/O 大会发布了 Android 7.0 系统。

### 02. Android 系统架构

1. Linux Kernel
   * Linux 内核 提供**底层驱动**：显示驱动，音频驱动，照相机驱动，蓝牙驱动，Wi-Fi驱动，电源管理等。
2. Libraries & Android Runtime
   * 类库 通过 C/C++ 提供特性支持。SQLit 提供数据库，OpenGL|ES 提供 3D 绘图，Webkit 提供浏览器内核等。
   * 运行时 提供一些核心库，允许开发使用 Java 编写 Android 应用；**Dalvik 虚拟机** | **ART 运行环境**，使得每一个应用都能运行在**独立的进程**当中。
3. Application Framework
   * 应用程序框架层 提供 API 开发者调用开发程序。
4. Applications
   * 应用层 各种程序。

### 03. Android 应用开发特色

1. 四大组件
   * 活动 Activity
   * 服务 Service
   * 广播接收器 Broadcast Receiver
   * 内容提供器 Content Provider
2. 控件
   * 系统控件
   * 自定义控件
3. SQLite 数据库
4. 多媒体
   * 音乐，视频，录音，拍照，闹铃
5. 地理位置定位
6. 其他

### 04. 项目目录结构

1. **gradle**
   * 目录下包含了 gradle wrapper 的配置文件，使用此方式不需要提前将 gradle 下载好，而是自动根据本地缓存情况决定是否需要联网下载 gradle。
2. **.gitignore**
   * 配置 Git 的忽略文件。[https://github.com/GitHub/gitignore](https://github.com/GitHub/gitignore)
3. **gradlew**
   * 在 Linux 系统使用，用于在命令行执行 gradle 命令。
4. **gradlew.bat**
   * 在 Windows 系统使用，用于在命令行执行 gradle 命令。
5. ***.iml**
   * iml 文件是所有 IntelliJ IDEA 项目自动生成，用于标识一个 IntelliJ IDEA 项目。不用修改。
6. **settings.gradle**
   * 指定项目中所有引入的模块。
7. **proguard-rules.pro**
   * 指定项目代码的混淆规则。

### 05. 详解 build.gradle 文件

1. Gradle 是一个非常先进的项目构建工具，它使用了一种基于 Groovy 的领域特定语言（DSL）来声明项目设置，摒弃了传统基于 XML（如 Ant 和 Maven）的各种烦琐配置。

2. 根目录下的 build.gradle 文件

   ```groovy
   // Top-level build file where you can add configuration options common to all sub-projects/modules.

   buildscript {// 构建脚本
     repositories {// repositories 闭包
       jcenter()// 代码托管仓库 jcenter maven
     }
     dependencies {// 闭包 dependencies 依赖
       classpath 'com.android.tools.build:gradle:2.2.2'// 使用 classpath 声明了一个 Gradle 插件同时指定了插件版本号
       // 因为 Gradle 并不是专门为构建 Android 项目而开发的

       // NOTE: Do not place your application dependencies here; they belong
       // in the individual module build.gradle files
     }
   }

   allprojects {// 所有项目
     repositories {// 指定引用的仓库
       jcenter()
     }
   }

   task clean(type: Delete) {
     delete rootProject.buildDir
   }
   ```

3. app目录下的 build.gradle 文件

   ```groovy
   apply plugin: 'com.android.application'// 应用一个插件
   // com.android.application 表示这是一个应用程序模块[可直接运行]
   // com.android.library 表示这是一个库模块[作为代码库]

   android {
     compileSdkVersion 25// 编译版本
     buildToolsVersion "25.0.2"// 构建工具版本
     defaultConfig {// 默认配置
       applicationId "com.just.first"// 指定项目包名
       minSdkVersion 14// 最低兼容的 Android 系统版本
       targetSdkVersion 25// 目标版本 启用相应版本上的新功能新特性
       versionCode 1// 项目版本号
       versionName "1.0"// 项目版本名称
       testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"// 测试需要
     }
     buildTypes {// 构建类型
       release {// 发布
         minifyEnabled false// 是否对代码进行混淆
         proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
         // proguardFiles 指定混淆时使用的规则文件
         // proguard-android.txt 在 Android SDK 目录下的通用混淆规则
         // proguard-rules.pro 在当前项目的根目录下 自己编写特定的混淆规则
       }
     }
   }

   dependencies {// 依赖库 [本地依赖][库依赖][远程依赖]
     compile fileTree(include: ['*.jar'], dir: 'libs')// 本地依赖
     androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
       exclude group: 'com.android.support', module: 'support-annotations'
     })// Android 测试
     compile 'com.android.support:appcompat-v7:25.3.1'
     // com.android.support 是域名部分 用于和其他公司的库做区分
     // appcompat-v7 是组名称 用于和同一公司中不同的库做区分
     // 25.3.1 是版本号 用户和同一库不同的版本做区分
     testCompile 'junit:junit:4.12'// 测试依赖
     compile 'com.android.support:recyclerview-v7:25.3.1'// 远程依赖

     // Gradle 在构建项目时会首先检查一下本地是否已经有这个库的缓存，如果没有的话则会去自动联网下载，然后再添加到项目的构建路径当中。
   }
   ```

### 06. 小结

1. 关于Dalvik 虚拟机和ART 运行环境相关知识需要学习。
2. **AndroidManifest.xml** 文件中指定的 **package** 和 **build.gradle** 文件中指定的 **applicationId** 区别。
3. 创建 Activity 时候系统指定的**模板**及**自定义模板**的学习使用。
4. 代码混淆相关的知识需要重新学习总结。
5. 资源文件夹 **mipmap** 与 **drawable** 区别。
6. 打印日志是否会影响性能和效率？





## 第 2 章 Activity

### 01. 菜单

1. 创建菜单文件

   ```java
   <?xml version="1.0" encoding="utf-8"?>
   <menu xmlns:android="http://schemas.android.com/apk/res/android">

     <item
       android:id="@+id/menu_add"
       android:title="Add" />

     <item
       android:id="@+id/menu_remove"
       android:title="Remove" />

   </menu>
   ```

2. 在 Activity 中进行添加和处理

   ```java
     /**
      * 创建自定义菜单
      *
      * @param menu 系统指定的菜单对象
      * @return true, 表示允许创建的菜单显示出来
      */
     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu, menu);// 从资源中加载菜单
       return true;// 允许菜单显示
     }

     /**
      * 菜单 Item 的点击事件
      *
      * @param item 菜单 Item
      * @return
      */
     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
         case R.id.menu_add:
           ToastUtil.showShortToast(this, "Add");
           break;
         case R.id.menu_remove:
           ToastUtil.showShortToast(this, "Remove");
           break;
       }
       return true;
     }
   ```

### 02. Intent

1. 显式 Intent

2. 隐式 Intent

   * action  每个 Intent 中只能指定一个 action
   * category  每个 Intent 中可以指定多个 category
   * data

3. data 详解

   * android:scheme  用于指定数据的**协议**部分。
   * android:host  用于指定数据的**主机名**部分。
   * android:port  用于指定数据的**端口**部分。
   * android:path  用于指定**主机名和端口之后**部分。
   * android:mimeType  用于指定**可处理的数据类型**，允许使用**通配符**的方式进行指定。

4. 注意：

   * `android.intent.categore.DEFAULT` 是一种默认的 category 在调用 `startActivity()` 方法的时候会自动将这个 category 添加到 Intent 当中。
   * 只有`<action>`和`<category>`中的内容同时能够匹配上 Intent 中指定的 action 和 category 时，活动才能响应该 Intent。
   * 只有`<data>`标签中指定的内容和 Intent 中携带的 Data 完全一致是，当前活动才能够响应该 Intent。

5. 启动浏览器

   ```java
   Intent browserIntent = new Intent(Intent.ACTION_VIEW);
   browserIntent.setData(Uri.parse("https://www.baidu.com"));
   startActivity(browserIntent);
   ```

6. 启动拨号

   ```java
   Intent dialIntent = new Intent(Intent.ACTION_DIAL);
   dialIntent.setData(Uri.parse("tel:10086"));
   startActivity(dialIntent);
   ```

### 03. Activity 的生命周期

1. 返回栈

   其实 Android 是使用**任务（Task）**来管理活动的，**一个任务**就是**一组**存放在**栈**里的活动的集合，这个栈也被称作返回栈（Back Stack）。栈是一种后进先出的数据接口。

2. 活动状态

   1. **运行**状态
      * 栈顶，可见，可交互
   2. **暂停**状态
      * 栈中，可见，不可交互
   3. **停止**状态
      * 栈中，不可见，不可交互
   4. **销毁**状态
      * 出栈

3. 生命周期函数

   * onCreat()
     * 初始化操作，加载布局，绑定事件等。
   * onStart()
     * 活动由不可见变为了可见。
   * onResume()
     * 活动准备好和用户进行交互。
   * onPause()
     * 这个方法在系统准备去启动或者恢复另一个活动的时候调用。我们通常会在这个方法中将一些消耗 CPU 的**资源释放掉**，以及**保存一些关键数据**，但这个方法的**执行速度一定要快**，不然会影响到新的栈顶活动的使用。
   * onStop()
     * 活动完全不可见。如果启动的新活动是一个对话框式的活动，那么 `onPause()` 执行 `onStop()` 不执行。
   * onDestroy()
     * 活动被销毁之前调用。
   * onRestart()
     * 活动由停止状态变为运行状态之前调用。

4. 活动的生存期

   * 完整生存期
   * 可见生存期
   * 前台生存期

### 04. 生命周期图

![Activity_Lifecycle](http://osxmqydw4.bkt.clouddn.com/activity_lifecycle.png)

### 05. 活动被回收

1. 场景：应用中有一个活动 A，用户在活动 A 的基础上启动了活动 B，活动 A 就进入了**停止状态**，这个时候由于**系统内存不足**，将活动 A 回收掉了，然后用户按下 **Back 键**返回活动 A，会出现什么情况？会正常显示活动 A 但**不会**执行活动 A 的 onRestart() 方法，**而是会**执行 onCreate() 方法将活动 A 重新创建一次。

   问题：打个比方，活动 A 中有一个文本输入框，用户输入一段文字，然后启动了活动 B，这时候活动 A 被回收了，按下 Back 键返回活动 A，却发现输入的文字全部都没了。数据怎么存储？

2. 生命周期回调：

   ```java
   // 启动活动 A
   JustDo23: RecoveryActivity ---> onCreate()
   JustDo23: RecoveryActivity ---> onStart()
   JustDo23: RecoveryActivity ---> onResume()
   // 在活动 A 的基础上启动活动 B
   JustDo23: RecoveryActivity ---> onPause()
   JustDo23: RecoveryActivity ---> onSaveInstanceState()
   JustDo23: RecoveryActivity ---> onStop()
   JustDo23: RecoveryActivity ---> onDestroy()
   // 按下 Back 键返回活动 A
   JustDo23: RecoveryActivity ---> onCreate()
   JustDo23: RecoveryActivity ---> tempData = Something you just typed
   JustDo23: RecoveryActivity ---> onStart()
   JustDo23: RecoveryActivity ---> onResume()
   ```

3. 解决方法：Activity 中提供了一个 onSaveInstanceState() 方法，可以保证活动被回收之前一定会被调用。在活动的 onCreate() 方法中有一个对应的 Bundle 类型参数 saveInstanceState 从中获取保存的数据。

4. 实现代码：

   ```java
     @Override
     protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_recovery);
       if (savedInstanceState != null) {// 取出保存的数据
         String tempData = savedInstanceState.getString("data_key");
         LogUtils.e(simpleName + " ---> " + "tempData = " + tempData);
       }
     }

     @Override
     public void onSaveInstanceState(Bundle outState) {
       super.onSaveInstanceState(outState);// 在销毁前进行的数据保存
       String tempData = "Something you just typed";
       outState.putString("data_key", tempData);
     }
   ```

5. 测试方法

   第一步，打开**开发者选项**；第二步，勾选**不保留活动**用户离开后即销毁每个活动。

6. 测试发现

   在测试中使用 EditText 测试 Activity 被系统回收，但发现返回之后 Activity 和 EditText 的确都是被重新创建了，但是 EditText 中输入的内容却仍然存在。其实是 View 有类似的保存数据的效果。

7. 其他

   * [关于开发者选项中的启用严格模式](http://www.miui.com/thread-3130429-1-1.html)
   * [Android 中正确保存view的状态](http://jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0512/2870.html)
   * [View的onSaveInstanceState和onRestoreInstanceState过程分析](http://www.cnblogs.com/xiaoweiz/p/3813914.html)
   * [如何保存和恢复 Activity 状态](http://www.epubit.com.cn/book/onlinechapter/14222)

### 06. 启动模式

1. 四种启动模式
   * standard
   * singleTop
   * singleTask
   * singleInstance
2. 指定为 singleInstance 模式的活动会启用一个新的返回栈来管理这个活动（其实如果 singTask 模式指定了不同的 **taskAffinity**，也会启动一个新的返回栈）。程序中有一个活动是允许其他程序调用的，则使用此模式。其他三种不能实现是因为每个应用都会有自己的返回栈，同一个活动在不同的返回栈中入栈时必须是创建了新的实例。

### 07. 活动的实践

1. 获取 Activity 的 Task id

   ```java
   this.getTaskId();
   ```

2. 获取 Activity 的名字

   ```java
   this.getClass().getSimpleName();
   ```

3. 杀死当前进程

   ```java
   android.os.Process.killProcess(android.os.Process.myPid());// 删掉当前进程
   ```

4. 活动管理集合

   ```java
   public class ActivityCollector {

     public static List<Activity> activityList = new ArrayList<>();

     public static void addActivity(Activity activity) {
       activityList.add(activity);
     }

     public static void removeActivity(Activity activity) {
       activityList.remove(activity);
     }

     public static void finishAll() {
       for (Activity activity : activityList) {
         if (!activity.isFinishing()) {
           activity.finish();
         }
       }
       activityList.clear();
       android.os.Process.killProcess(android.os.Process.myPid());// 删掉当前进程
     }

   }
   ```

5. 启动活动

   ```java
   /**
    * 其他活动启动当前获取
    *
    * @param context 上下文
    * @param data1   传递数据
    * @param data2   传递数据
    */
   public static void actionStart(Context context, String data1, String data2) {
     Intent intent = new Intent(context, StartActivity.class);
     intent.putExtra("param1", data1);
     intent.putExtra("param2", data2);
     context.startActivity(intent);
   }
   ```

### 08. 小结

1. 关于向下兼容的 **AppCompatActivity** 需要学习。
2. 关于**栈**和**堆**的相关知识需要学习总结。
3. 弹出 **Dialog** 或者 **PopupWindow** 并**不影响** Activity 的生命周期。
4. 在**开发者选项**中的各个功能的使用方式。
5. 和启动模式相关的有一个 **onNewIntent(Intent intent)** 方法需要注意。
6. Activity 的 taskAffinity 属性。
7. 随时随地退出程序。
8. 启动活动的最佳写法。
9. 退出程序的相关问题，如何真正退出程序，杀掉进程，最优做法是什么？





## 第 3 章 View

### 01. Button

这里主要记录一下，在布局文件里面给 Button 设置的文字是**"Button"**，但是最终的显示结果却是**"BUTTON"**，小写变大写。这是由于系统会对 Button 中的所有英文字母自动进行大写转换，使用以下代码禁止这一默认特性：

```java
android:textAllCaps="false"
```

其次，给按钮设置点击事件应该有四种方式。

### 02. 可见属性

通过 **android:visibility** 进行指定

* **visible**  可见。
* **invisible**  不可见，但仍然占据着原来的位置和大小，变成透明状态了。
* **gone**  不可见，而且不再占用任何屏幕空间。

### 03. 布局

* **LinearLayout**  线性布局
  * LinearLayout 的默认方向是 `horizontal`
  * `android:layout_weight` 属性的计算方法，对剩余空间按比例分配
  * `android:layout_gravity="center"` 与 `android:gravity="center"` 两者之间的区别

* **RelativeLayout**  相对布局

  * `android:layout_centerInParent="true"`
  * `android:layout_alignParentLeft="true"`
  * `android:layout_above="@id/bt_center"`
  * `android:layout_below="@id/bt_center"`
  * `android:layout_toLeftOf="@id/bt_center"`
  * `android:layout_alignLeft="@id/bt_center"`
  * `android:layout_alignBottom="@id/bt_center"`
  * `android:layout_alignBaseline="@id/bt_center"`

* **FrameLayout**  帧布局

  * 一层一层的覆盖

* **PercentRelativeLayout**  和  **PercentFrameLayout**  百分比布局
  * 由于 `LinearLayout` 本身已经支持按比例指定控件的大小，因此百分比布局只为 `RelativeLayout` 和 `FrameLayout` 进行了功能扩展。

  * 不再使用 `wrap_content` 和 `match_parent` 而是直接指定百分比。

    ```java
    <android.support.percent.PercentRelativeLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1">

        <Button
          android:id="@+id/bt_center"
          android:layout_centerInParent="true"
          android:text="Center"
          android:textAllCaps="false"
          app:layout_heightPercent="20%"
          app:layout_marginPercent="5%"
          app:layout_widthPercent="20%" />

      </android.support.percent.PercentRelativeLayout>
    ```

### 04. ViewGroup 图

![ViewGroup](http://osxmqydw4.bkt.clouddn.com/viewgroup.png)

1. 所有**控件**都是直接或者间接继承自 **View** 的。
2. 所有**布局**都是直接或者间接继承自 **ViewGroup** 的。
3. **View** 是 Android 中**最基本**的一种 **UI 组件**，它可以在屏幕上**绘制**一块矩形区域，并能**响应**这块区域的各种**事件**。
4. **ViewGroup** 是一种**特殊的 View**，它可以**包含**很多**子 View** 和**子 ViewGroup**，是一个用于**放置**控件和布局的**容器**。

### 05. 自定义控件

1. 引入布局，使用 `<include>` 标签引入一个已经写好的布局。
2. **注意：** 获取上下文使用 `getContext()` 方法。
3. **注意：** 加载布局使用`LayoutInflater.from(context).inflate(R.layout.inclue_title, this);` **注意** 第二个参数
4. 最简单的自定义控件，查看代码即可。

### 06. ListView

1. 适配器有多种，简单的使用`ArrayAdapter`。
2. 掌握使用 `ViewHolder` 进行复用来提升运行效率。
3. 点击事件的基本使用。

### 07. RecyclerView

1. 注意在使用的时候需要先设置`布局管理器`。

2. 为什么 ListView 很难或者根本无法实现的效果在 RecyclerView 上这么轻松就能实现？这主要得益于 RecyclerView 出色的设计。ListView 的布局排列是由自身去管理的，而 RecyclerView 则将这个工作交给了 LayoutManager，LayoutManager 中制定了一套可扩展的布局排列接口，子类只要按照接口的规范来实现，就能定制出各种不同排列方式的布局了。

3. RecyclerView 并没有提供像样的点击事件，其实，ListView 的在点击事件上的处理并不人性化，`setOnItemCLickListener()`方法注册的是子项的点击事件，但如果想点击的是子项里具体的某一个按钮呢？虽然 ListView 也能做到，但是实现起来就相对比较麻烦了。为此，RecyclerView 干脆直接摒弃了子项点击事件的监听，所有的点击事件都由具体的 View 去注册，就再没有这个困扰了。

   ```
   int position = viewHolder.getAdapterPosition();
   ```

### 08. 界面最佳实践

1. 点九图片`Nine-Patch`的相关知识和使用。
   * 在上边框和左边框绘制的部分表示当图片需要拉伸时就拉伸黑点标记的区域。
   * 在下边框和右边框绘制的部分表示内容会被放置的区域。
   * 使用鼠标在图片的边缘拖动就可以进行绘制。
   * 按住`shift`键拖动可以进行擦除。
2. `RecycleView`数据更新。

### 09. 小结

1. 屏幕适配相关知识。慕课网 [Android-屏幕适配全攻略](http://www.imooc.com/learn/484)。

2. Android Studio 中的 **drawable-xhdpi** 和 **mipmap-xhdpi** 文件夹的区别及使用。

3. EditText 使用时候**软键盘**的弹起和收起监听。

4. ImageView 使用时候的有三个属性需要注意

   ```java
   android:src="@mipmap/ic_launcher"
   android:background="@mipmap/ic_launcher"
   android:scaleType="centerCrop"
   ```

5. ProgressBar 如何修改颜色？

6. 百分比布局需要更多的学习和使用。

7. 关于 `RecycleView` 的源码分析可以简单的学习一下。

8. 在 Android sdk 目录下有一个 tools 文件夹，其中的工具可以学习使用一下。

9. 关于 `LayoutInflater` 布局填充的一些方法需要留意。





## 第 4 章 Fragment

### 01. 碎片

1. 碎片是可以嵌入在活动当中的 UI 片段，轻量迷你的活动。
2. 使用 **support-v4** 库中的 `Fragment` 作为基类。在 Android 4.2 系统中才开始支持在 `Fragment` 中**嵌套**使用 `Fragment`。
3. 在 `build.gradle` 文件中添加了 **appcompat-v7** 库的依赖，这个库会将 **support-v4** 库也一起引入进来。

### 02. 静态使用

1. 继承并添加布局

   ```java
   /**
    * 继承 Fragment 并填充布局
    *
    * @author JustDo23
    */
   public class LeftFragment extends Fragment {

     @Nullable @Override
     public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {// 简单加载布局
       View rootView = inflater.inflate(R.layout.fragment_simple_left, container, false);
       return rootView;
     }

   }
   ```

2. 在**布局文件**中使用 `Fragmet`

   ```java
   <fragment
     android:id="@+id/frag_left"
     android:name="com.just.first.chapter04.LeftFragment"
     android:layout_width="0dp"
     android:layout_height="match_parent"
     android:layout_weight="1" />
   ```

   代码关键点

   * 标签使用 **`<fragment />`**
   * 属性使用 **`android:name="all_name"`**  并制定**全部路径名称**
   * 属性 **`android:id="frag_id"`** 是**必须要有**的否则会崩溃

### 03. 动态使用

1. 布局文件

   ```java
   <FrameLayout
     android:id="@+id/fl_bottom"
     android:layout_width="match_parent"
     android:layout_height="0dp"
     android:layout_weight="1"
     android:background="@android:color/holo_orange_dark" />
   ```

2. 碎步切换代码

   ```java
   /**
    * 切换 Fragment 操作
    *
    * @param fragment 新的碎片
    */
   private void replaceFragment(Fragment fragment) {
     FragmentManager supportFragmentManager = this.getSupportFragmentManager();// 获取碎步管理类
     FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();// 获取碎片管理事务
     fragmentTransaction.replace(R.id.fl_bottom, fragment);// 进行替换
     fragmentTransaction.commit();// 事务提交
   }
   ```

3. 动态加载碎片5个步骤

   1. 创建待添加的碎步实例
   2. 通过 `this.getSupportFragmentManager()` 方法获取 `FragmentManager` 实例
   3. 通过 `fragmentManager.beginTransaction()` 方法开启一个`事务`
   4. 使用 `fragmentTransaction.replace()` 指定位置及碎片进行动态添加
   5. 最后通过 `fragmentTransaction.commit()` 方法提交事务以完成动态添加

4. 碎片模拟返回栈

   添加完碎片后按下返回键就直接退出了。在 `commit()` 方法之前执行以下代码来模拟返回栈。

   ```
   fragmentTransaction.addToBackStack(null);// 字符串参数用于描述返回栈状态
   ```

### 04. 碎片和活动通信

1. 在 Activity 中通过 `FragmentManager` 获取

   ```java
   LeftFragment leftFragment = (LeftFragment) getSupportFragmentManager().findFragmentById(R.id.frag_left);
   ```

2. 在 Fragment 中直接调用 `getActivity();` 方法

   ```java
   FragmentActivity activity = this.getActivity();
   ```

### 05. 碎片生命周期

1. 生命周期函数
   * onAttach()
     * 当 Fragment 与 Activity 建立关联时候调用
   * onCreate()
   * onCreateView()
     * 为 Fragment 加载布局
   * onActivityCreated()
     * 确保与 Fragment 相关联的 Activity 一定已经创建完毕的时候调用
   * onStart()
   * onResume()
   * onPause()
   * onStop()
   * onDestroyView()
     * 当与 Fragment 相关联的视图被移除的时候调用
   * onDestroy()
   * onDetach()
     * 当 Fragment 与 Activity 解除关联的时候调用
2. 静态加载时 Activity 及 Fragment 生命周期
3. 动态加载时 Activity 及 Fragment 生命周期
4. 调用 `addToBackStack` 方法对 Fragment 生命周期的影响

### 06. 碎片生命周期图

![Fragment_Lifecycle](http://osxmqydw4.bkt.clouddn.com/fragment_lifecycle.png)

### 07. 动态加载技巧

1. 使用限定符
   * 在 **`res`** 目录下创建与 **`layout`** 目录平级的文件夹 **`layout-large`**
   * 在 **`layout`** 目录`和` **`layout-large`** 目录下创建**同名的布局文件**
   * 两个布局文件虽然同名但是**布局内容不同**
   * 分别在**手机**和**平板**上运行才能看到实现的效果

   其中 **`large`** 就是一个**`限定符`**，程序运行在类似平板这种 `large` 屏幕的设备上会自动加载 `layout-large` 目录下的布局。

2. 使用最小宽度限定符

   * 在 **`res`** 目录下创建 **`layout-sw600dp`** 目录

   **最小宽度限定符 Smallest-width Qualifier** 允许我们对屏幕指定一个最小值，以 **dp** 为单位，然后以这个最小值为**临界点**，屏幕宽度大于这个值的设备就加载一个布局，屏幕宽度小于这个值的设备就加载另一个布局。


### 08. 小结

1. 关于 `Activity` 与 `Fragment` 之间交互的总结。
2. 熟练 `Fragment` 的各个生命状态及生命周期。
3. 注意 `Activity` + `Fragment` 详细的生命周期。
4. 经常开发手机版应用可以接触一下`平板`开发。





## 第 5 章 广播机制

### 01. 广播机制简介

1. 在**一个 IP 网络范围**中，**最大的 IP 地址**是**被保留**作为**广播地址**来使用的。比如某个网络的 IP 范围是 **`192.168.0.xxxx`** 子网掩码是 **`255.255.255.0`** 那么这个网络的广播地址就是 **`192.168.0.255`** 了。**广播数据包**会被发送到**同一网络**上的**所有端口**，这样在该网络中的每台主机都将会收到这条广播。
2. 在 Android 中每个应用可以对自己感兴趣的广播进行**注册**，这样该程序就**只会接收**到自己关心的广播。应用可以自由的**发送**和**接收**广播。

### 02. 广播分类

* 标准广播
  * 一种完全**异步**执行的广播。广播发出后，所有接收器**几乎同时**接收到广播消息，因此**没有先后顺序**，**效率高**，**无法被截断**。
* 有序广播
  * 一种**同步**执行的广播。广播发出后，**同一时刻只有一个**接收器接收到广播消息，这个接收器**执行完毕**广播**才会继续传递**，因此**有先后顺序**，**优先级**高的接收器先收到，并且前面的**可以截断**正在传递的广播，这样后面的就无法收到广播消息了。

### 03. 注册广播

* 动态注册
  * 需要使用 `IntentFilter` 类指定相应的 `action`
  * `注册`与`解注册`**成对**出现，在`onCreate`中进行注册在`onDestroy`中进行解注册
* 静态注册
  * 需要在功能清单中使用 `intent-filter` 及 `action` 标签指定
  * 注册之后为系统`全局`的广播接收器
  * 程序进程`运行中`则`可以接收`，程序进程`完成退出`则`无法接收`

### 04. 广播示例代码

1. 动态注册-监听网络变化

   1. 继承 BroadcastReceiver

      ```java
      /**
       * 动态广播监听网络变化
       *
       * @author JustDo23
       */
      public class NetworkChangeReceive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
          ToastUtil.showShortToast(context, "Network changes.");
          ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
          NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
          if (networkInfo != null && networkInfo.isAvailable()) {
            ToastUtil.showShortToast(context, "Network is available.");
          } else {
            ToastUtil.showShortToast(context, "Network is unavailable.");
          }
        }

      }
      ```

   2. 在 Activity 中注册与解注册

      ```java
      /**
       * 动态广播监听网络变化
       *
       * @author JustDo23
       */
      public class NetworkChangeActivity extends BaseActivity {

        private IntentFilter intentFilter;
        private NetworkChangeReceive networkChangeReceive;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_network_change);
          register();// 注册广播
        }

        @Override
        protected void onDestroy() {
          super.onDestroy();
          unRegister();// 解注册广播
        }

        /**
         * 注册广播
         */
        private void register() {
          intentFilter = new IntentFilter();
          intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
          networkChangeReceive = new NetworkChangeReceive();
          this.registerReceiver(networkChangeReceive, intentFilter);
        }

        /**
         * 解注册广播
         */
        private void unRegister() {
          unregisterReceiver(networkChangeReceive);
        }

      }
      ```

   3. 添加权限

      ```java
      <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
      ```

2. 静态注册-监听手机开机

   1. 使用 Android Studio 创建广播接收器

      * `右击` 选择 `New` 选择 `Other` 选择 `Broadcast Receiver` 进行命名
      * 此操作与手动创建一致

   2. 功能清单新增

      ```java
      <receiver
        android:name=".chapter05.CustomReceiver"
        android:enabled="true"
        android:exported="true">
      </receiver>
      ```

      * `enabled` 属性表示是否启用这个广播接收器
      * `exported` 属性表示是否允许这个广播接收器接收本程序以外的广播

   3. 添加 `<intent-filter>` 标签并指定 `<action>` 标签信息

      ```java
      <receiver
        android:name=".chapter05.CustomReceiver"
        android:enabled="true"
        android:exported="true">
        <intent-filter android:priority="100">
          <action android:name="com.just.first.CUSTOM" />
        </intent-filter>
      </receiver>
      ```

      * `priority` 指定了广播的优先级

   4. 添加权限

      ```java
      <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
      ```

### 05. 发送自定义广播

1. 发送标准广播

   ```java
   sendBroadcast(new Intent("com.just.first.CUSTOM"));// Intent 指定 action
   ```

2. 发送有序广播

   ```java
   sendOrderedBroadcast(new Intent("com.just.first.CUSTOM"), null);// 第二参数是一个与权限相关的字符串
   ```

   * 发送有序广播需要指定`优先级`，可以在功能清单中使用 `priority` 指定优先级
   * 前面的广播可以截断广播的传递，在 `onReceive()` 方法中调用 `abortBroadcast()` 方法截断广播

### 06. 本地广播

前面的广播都属于**系统全局**广播，即发出的广播**可以**被其他任何程序接收到，并且程序**也可以**接收来自其他任何应用的广播。这样存在**安全问题**。Android 引入了一套**本地广播机制**，使用本地广播机制广播**只能**在本应用内部进行**发送**和**接收**。主要是使用了一个 **`LocalBroadcastManager`** 来对广播进行管理。

1. 本地广播注册

   ```java
     private LocalBroadcastReceiver localBroadcastReceiver;
     private LocalBroadcastManager localBroadcastManager;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_local_broadcast);

       localBroadcastManager = LocalBroadcastManager.getInstance(this);// 获取实例
       IntentFilter intentFilter = new IntentFilter();
       intentFilter.addAction("com.just.first.LOCAL");
       localBroadcastReceiver = new LocalBroadcastReceiver();// 实例化接收器
       localBroadcastManager.registerReceiver(localBroadcastReceiver, intentFilter);// 注册本地广播
     }
   ```

2. 本地广播解注册

   ```java
     @Override
     protected void onDestroy() {
       super.onDestroy();
       localBroadcastManager.unregisterReceiver(localBroadcastReceiver);
     }
   ```

3. 本地广播发送

   ```java
     public void sendLocalBroadcast(View view) {
       Intent intent = new Intent("com.just.first.LOCAL");
       localBroadcastManager.sendBroadcast(intent);// 发送本地广播
     }
   ```

4. 重要提示

   * 本地广播是无法通过静态注册的方式来接收的。
   * 静态注册主要就是为了让程序在未启动的情况下也能接收到广播。

5. 本地广播优势

   * 可以**明确知道**正在发送的广播**不会离开**我们的程序，不必担心机密**数据泄露**。
   * 其他程序**无法**将广播发送到我们程序的内部，不必担心有**安全漏洞**隐患。
   * 发送本地广播比发送系统全局广播更加**高效**。


### 07. 重点提示

1. **注意：**开发时**不能**在 `onReceive()` 方法中添加**过多逻辑**或者进行任何的**耗时操作**，因为在广播接收器中**不允许开启线程**的，当 `onReceive()` 方法运行较长时间而没有结束，程序就会出现 **ANR** 错误。

2. 广播是一种可以**跨进程**的`通信`方式，例如我们可以接收系统广播。

3. 在**静态广播接收器**及**本地广播接收器**中是**没有办法**弹出对话框的。

   ```java
   android.view.WindowManager$BadTokenException: Unable to add window -- token null is not valid; is your activity running?
   ```

4. 在**动态广播接收器**中是**可以**弹出对话框的。

5. 当在 `BaseActivity` 中注册广播从而使得所有子类都动态注册该广播的操作中，需要将广播的`注册`与`解注册`分别放在生命周期的 `onResume()` 与 `onPause()` 中。这样可以**保障只有**处于**栈顶**的活动才能接收到广播信息。


### 08. 初识 Git

1. **Git** 是一个**开源**的**分布式**`版本控制`**工具**。

2. 配置身份

   ```shell
   $ git config --global user.name "JustDo23"
   $ git config --global user.email "JustDo_23@163.com"
   ```

3. 创建代码仓库

   ```shell
   $ git init
   ```

4. 添加与提交

   ```shell
   $ git add .
   $ git commit -m "describe for commit"
   ```

### 09. 小结

1. 广播的生命周期简单了解。
2. 进一步了解广播的工作原理。





## 第 6 章 数据存储

### 01. 持久化简介

1. **瞬时数据**就是指那些**存储**在**内存**当中，有可能会因为**程序关闭**或其他原因导致**内存被回收**而**丢失**的数据。
2. **持久化数据**就是指将那些**内存中**的瞬时数据保存到**存储设备**中，保证即使在手机或电脑关机的情况下，这些数据仍然**不会丢失**。

### 02. 文件存储

1. 文件存储**不对**存储的**内容**进行任何的**格式化处理**，所有数据都是**原封不动**地保存到文件当中。因而**比较适合**用于存储一些简单的**文本数据**或**二进制数据**。

2. 通过 `Context` 提供的 `openFileOutput()` 方法将文件存入内部存储路径中。操作模式

   * **`Context.MODE_PRIVATE`** 默认模式私有且覆盖
   * **`Context.MODE_APPEND`** 模式每次写入数据进行追加

3. 通过 `Context` 提供的 `openFileInput()` 方法用于从文件中进行数据读取。

4. 存数据

   ```java
   /**
    * 保存数据到内部存储文件
    *
    * @param fileName 文件名称
    * @param saveData 写入的数据
    */
   private void saveToFile(String fileName, String saveData) {
     FileOutputStream fileOutputStream = null;
     BufferedWriter bufferedWriter = null;
     try {
       fileOutputStream = this.openFileOutput(fileName, Context.MODE_APPEND);// [/data/data/com.just.first/files]
       bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
       bufferedWriter.write(saveData);
     } catch (IOException e) {
       e.printStackTrace();
     } finally {
       try {
         if (bufferedWriter != null) {
           bufferedWriter.close();
         }
         if (fileOutputStream != null) {
           fileOutputStream.close();
         }
       } catch (IOException e) {
         e.printStackTrace();
       }
     }
   }
   ```

5. 取数据

   ```java
   /**
    * 从内部存储文件中读取数据
    *
    * @param fileName 文件名称
    * @return 文件内容
    */
   private String loadFromFile(String fileName) {
     FileInputStream fileInputStream = null;
     BufferedReader bufferedReader = null;
     StringBuilder dataContent = new StringBuilder();
     try {
       fileInputStream = this.openFileInput(fileName);
       bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
       String line = "";
       while ((line = bufferedReader.readLine()) != null) {
         dataContent.append(line);
       }
     } catch (IOException e) {
       e.printStackTrace();
     } finally {
       try {
         if (bufferedReader != null) {
           bufferedReader.close();
         }
         if (fileInputStream != null) {
           fileInputStream.close();
         }
       } catch (IOException e) {
         e.printStackTrace();
       }
     }
     return dataContent.toString();
   }
   ```

### 03. SharedPreferences

1. **`SharedPreferences`** 使用**键值对**的方式存储数据。支持**`多种`**不同的**`数据类型`**存储。

2. 文件存储路径 **`/data/data/主包名/shared_prefs`**

3. 文件存储的是 **`xml`** 文件。

4. 获取 `sharedPreferences` 实例的三种方法
   * 通过 `Context` 类的 `getSharedPreferences(String name, int mode)` 方法获取。
     * 第一个参数文件名称
     * 第二个参数模式
   * 通过 `Activity` 类的 `getPreferences(int mode)` 方法获取。
     * 一个参数模式
     * 文件名称会自动获取当前活动类名 `getLocalClassName()`
   * 通过 `PreferenceManager` 类的 `getDefaultSharedPreferences(Context context)` 方法获取。
     * 一个参数上下文
     * 文件名称会自动获取当前程序主包名 `context.getPackageName() + "_preferences"`

5. 存储数据需要三个步骤
   1. 获取 `SharedPreferences.Editor` 对象
   2. 通过 `Editor` 对象进行数据的添加
   3. 调用 `Editor` 的 `apply()` 方法进行数据的提交

6. 存数据

   ```java
   /**
    * 将数据保存到 SharedPreferences
    *
    * @param fileName 文件名
    * @param keyWord  键
    * @param saveData 值
    */
   private void saveToSharedPreferences(String fileName, String keyWord, String saveData) {
     SharedPreferences sharedPreferences = this.getSharedPreferences(fileName, MODE_APPEND);
     SharedPreferences.Editor edit = sharedPreferences.edit();
     edit.putString(keyWord, saveData);
     edit.apply();
   }
   ```

7. 取数据

   ```java
   /**
    * 从 SharedPreferences 加载数据
    *
    * @param fileName 文件名
    * @param keyWord  键
    * @return 值
    */
   private String loadFromSharedPreferences(String fileName, String keyWord) {
     SharedPreferences sharedPreferences = this.getSharedPreferences(fileName, MODE_APPEND);
     return sharedPreferences.getString(keyWord, null);// 默认值
   }
   ```

8. 数据的**写入**和**读取**均可以根据类型进行操作。

### 04. SQLite 数据库

1. `SQLite` 数据库是一款**轻量级**的**关系型数据库**，运算速度快，占用资源少。支持**标准 SQL 语法**，遵循数据库的 **ACID 事务**。

2. 自定义 `SQLiteOpenHelper` 继承系统 `SQLiteOpenHelper`

   ```java
   /**
    * SQLiteOpenHelper 实现数据库创建与升级
    *
    * @author JustDo23
    */
   public class BookOpenHelper extends SQLiteOpenHelper {

     /**
      * 构造方法[必须实现]
      *
      * @param context 上下文
      * @param name    数据库名称[带上后缀 .db]
      * @param factory 工厂[允许数据查询使用自定义 Cursor][一般传 null]
      * @param version 版本[整型]
      */
     public BookOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
       super(context, "BookStore" + ".db", null, 1);
     }

     /**
      * 创建数据表方法[必须实现]
      *
      * @param db 数据库操作对象
      */
     @Override
     public void onCreate(SQLiteDatabase db) {
       String sql = "create table Book ( "
           + "id integer primary key autoincrement" + ", "
           + "author text" + ", "
           + "price real" + ", "
           + "pages integer" + ", "
           + "name text"
           + ")";
       db.execSQL(sql);// 执行 SQL 语句
     }

     /**
      * 数据库升级方法[必须实现]
      *
      * @param db         数据库操作对象
      * @param oldVersion 旧的版本号
      * @param newVersion 新的版本号
      */
     @Override
     public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

     }

   }
   ```

3. 数据库文件存储路径 **`/data/data/主包名/databases`**

4. **`注意：`** SQL 语句中相应位置的空格及分割符很重要

5. 数据类型

   * **`integer`** 表示**整型**
   * **`real`** 表示**浮点型**
   * **`text`** 表示**文本类型**
   * **`blob`** 表示**二进制类型**
   * **`primary key`** 表示**主键**
   * **`autoincrement`** 表示**自增长**

6. 数据库的创建

   * 继承系统 `SQLiteOpenHelper` 并实现相应方法后并**没有**实现数据库的**创建**
   * 在 `SQLiteOpenHelper` 中有两个重要的方法
     * `getReadableDatabase()` 获取**读数据**操作的对象
     * `getWritableDatabase()` 获取**写数据**操作的对象
     * 这两个方法可以**创建**或**打开**一个数据库。数据库**存在**则直接**打开**，数据库**不存在**则**创建并打开**。
     * 这两个方法**返回的对象**都**可以**对数据库进行**读写**操作。
     * 当数据**不可写入**时候如磁盘空间已满，`getReadableDatabase()` 方法将以**只读**方式打开数据库，`getWritableDatabase()` 方法会抛出异常。

7. ADB 调试工具

   * 进入 Shell 内核

     ```shell
     $ adb shell
     ```

   * 打开数据库

     ```shell
     $ sqlite3 BookStore.db
     ```

   * 查看数据库中的数据表

     ```shell
     $ .table
     ```

     * 数据表 `android_metadata` 是每个数据库自动生成的

   * 查看建表语句

     ```shell
     $ .schema
     ```

   * 退出

     ```shell
     $ .exit
     $ .quit
     ```

### 05. 数据库操作

1. 升级数据库

   ```java
   /**
    * 数据库升级方法
    */
   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("drop table if exists Book");// 删除原来的表
     onCreate(db);// 重新进行创建
   }
   ```

   * 先将已经存在的数据表进行**删除**，然后**创建**新的表。数据表**不能重复**创建，否则会**崩溃**。
   * 修改**构造方法**中的数据库**版本号**。

2. 概述

   数据库操作**有 4 种**简称 **`CRUD`**

   * **`C`** 代表 **`Create`** 添加 **` insert`**
   * **`R`** 代表 **`Retrieve`** 查询 **` select`**
   * **`U`** 代表 **` Update`** 更新 **` update`**
   * **`D`** 代表 **`Delete`** 删除 **` delete`**

3. 添加数据

   * 利用 **`SQLiteDatabase`** 对象的 `insert(String table, String nullColumnHack, ContentValues values)` 方法进行数据数据添加
   * 第一个参数**表名**
   * 第二个参数用于在**未指定**添加数据的情况下给某些可为空的列**自动赋值 NULL** 一般传入 **null** 即可
   * 第三个参数数据集合**键值对**关系**键**为**列名**因此**值**为**数据**

     ```java
     public void insert() {
       SQLiteDatabase writableDatabase = bookOpenHelper.getWritableDatabase();// 获取数据操作对象
       ContentValues contentValues = new ContentValues();// 键值对集合
       contentValues.put("name", "FirstLine");// 列名-数据
       contentValues.put("author", "Guo");
       contentValues.put("pages", "570");
       contentValues.put("price", 79.0);
       writableDatabase.insert("Book", null, contentValues);// 指定表名添加
     }
     ```

   * 数据库的查询语句

     ```shell
     $ select * from Book;
     ```

4. 更新数据

   * 利用 **`SQLiteDatabase`** 对象的 `update(String table, ContentValues values, String whereClause, String[] whereArgs)` 方法进行数据数据更新
   * **后两个**参数用于**约束**更新某一行或者某几行的数据，不指定**默认**更新**所有行**。
   * **第三个**参数对应 **SQL** 语句的 **where** 部分其中 **`?`** 代表占位符
   * **第四个**参数按照**先后顺序**对应为**占位符**进行**赋值**

     ```java
     public void update() {
       SQLiteDatabase writableDatabase = bookOpenHelper.getWritableDatabase();// 获取数据操作对象
       ContentValues contentValues = new ContentValues();// 键值对集合
       contentValues.put("price", 99.9);
       writableDatabase.update("Book", contentValues, "name = ?", new String[]{"FirstLine"});
     }
     ```

5. 删除数据

   * 利用 **`SQLiteDatabase`** 对象的 `delete(String table, String whereClause, String[] whereArgs)` 方法进行数据数据删除

     ```java
     public void delete() {
       SQLiteDatabase writableDatabase = bookOpenHelper.getWritableDatabase();// 获取数据操作对象
       writableDatabase.delete("Book", "name = ?", new String[]{"FirstLine"});
     }
     ```

6. 查询数据

   * **`SQL`** 的全称是 **`Structured Query Language`** 即 **`结构化查询语言`**
   * 利用 **`SQLiteDatabase`** 对象的 `query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)` 方法进行数据数据查询
   * `query()` 重载函数比较多功能与 SQL 语句中的查询类似

     | query方法参数     | 对应 SQL 部分                 | 描述                   |
     | ------------- | ------------------------- | -------------------- |
     | table         | from table_name           | 指定查询的表名              |
     | columns       | select column1, column2   | 指定查询的列名              |
     | selection     | where column = value      | 指定 where 的约束条件       |
     | selectionArgs | -                         | 为 where 中的占位符提供具体的值  |
     | groupBy       | group by column           | 指定需要 group by 的列     |
     | having        | having column = value     | 对 group by 后的结果进一步约束 |
     | orderBy       | order by column1, column2 | 指定查询结果的排序方式          |

   * 不需要的参数可以指定为 **null**

     ```java
     public void query() {
       SQLiteDatabase readableDatabase = bookOpenHelper.getReadableDatabase();// 获取数据操作对象
       Cursor cursor = readableDatabase.query("Book", null, null, null, null, null, null);// 查询获得游标
       if (cursor.moveToFirst()) {// 是否可以移动位置
         do {
           String author = cursor.getString(cursor.getColumnIndex("author"));
           String name = cursor.getString(cursor.getColumnIndex("name"));
           String pages = cursor.getString(cursor.getColumnIndex("pages"));
           String price = cursor.getString(cursor.getColumnIndex("price"));
           LogUtils.e("Book: " + author + " -- " + name + " -- " + pages + " -- " + price);
         } while (cursor.moveToNext());// 是否可以继续往下移动
       }
     }
     ```

7. 使用 SQL 语句

   * 添加数据

     ```java
     writableDatabase.execSQL("insert into Book (name, author, pages, price) values (?, ?, ?, ?)", new String[]{"SecondLine", "Lin", "123", "66.6"});writableDatabase.execSQL("insert into Book (name, author, pages, price) values (?, ?, ?, ?)", new String[]{"SecondLine", "Lin", "123", "66.6"});
     ```

   * 更新数据

     ```java
     writableDatabase.execSQL("update Book set pages = ? where author = ?", new String[]{"333", "Lin"});writableDatabase.execSQL("update Book set pages = ? where author = ?", new String[]{"333", "Lin"});
     ```

   * 删除数据

     ```java
     writableDatabase.execSQL("delete from Book where pages > ?", new String[]{"10"});
     ```

   * 查询数据

     ```java
     readableDatabase.rawQuery("select * from Book", null);// 查询获得游标
     ```

### 06. LitePal 数据库

1. **LitePal** 采用了**对象关系映射 ORM **的模式。简单说，我们使用的编程语言是**面向对象语言**，而使用的数据库则是**关系型数据库**，那么将面向对象的语言和面向关系的数据库之间**建立一种映射关系**，这就是**对象关系映射**了。因此，可以用面向对象的思维来操作数据库，而不用再和 SQL 语句打交道。

2. GitHub 链接[https://github.com/LitePalFramework/LitePal](https://github.com/LitePalFramework/LitePal)

3. 使用步骤

   1. 添加**依赖**
   2. 创建 **`assets`** 文件夹
   3. 创建 **`litepal.xml`** 配置文件
   4. 在 **`Application`** 中进行**初始化**
   5. 创建**实体类**也就是**表结构**
   6. 配置 **`litepal.xml`** 文件

4. 创建数据库

   ```java
   LitePal.getDatabase();// 使用 LitePal 创建数据库
   ```

5. 添加数据

   * 实体类需要要继承 **`DataSupport`** 类
   * 直接调用实体类的 **`save()`** 方法

     ```java
     Book book = new Book();// 实例化实体类
     book.save();// 使用 LitePal 插入数据
     ```

6. 更新数据

   * 通过对**已存储的对象**重新设值后重新调用 **`save()`** 方法来更新。
   * 调用 `model.isSaved()` 方法返回 `true` 则表示**已存储的对象**。一种是调用过 `save()` 方法的对象，一种是通过 `LitePal` 的 `查询 API` 得到的对象。

     ```java
     Book book = new Book();// 实例化实体类
     book.save();// 使用 LitePal 插入数据
     book.setPages("324");// 更新数据
     book.save();// 对插入的数据进行更新
     ```

   * 通过**任意对象**设置需要更新的值后调用 **`updateAll(String... conditions)`** 方法来更新
   * 第一个参数可以指定**条件约束**，**不指定**代表**更新所有**
   * **`注意：`**将某个字段设置为默认值需要调用 **`setToDefault(String fieldName)`** 参数字段名

     ```java
     Book book = new Book();// 实例化实体类
     book.setPages("776");// 更新数据
     book.setToDefault("price");// 设置默认值
     book.updateAll("name = ? and pages = ?", "老人与海", "76");
     ```

7. 删除数据

   * 通过调用**已存储的对象**的 **`delete()`** 方法来删除
   * 直接使用 **`DataSupport.deleteAll()`** 传递参数进行删除，传递表名及约束，不传则删除所有

     ```java
     DataSupport.deleteAll(Book.class, "pages < ?", "400");// 指定表名及约束进行删除
     ```

8. 查询数据

   * 直接使用 **`DataSupport`** 类中的相关方法进行查询
   * 查询所有

     ```java
     DataSupport.findAll(Book.class);// 查询所有
     DataSupport.findFirst(Book.class);// 查询第一条
     DataSupport.findLast(Book.class);// 查询最后一条
     ```

   * 更多查询功能

     ```java
     DataSupport.select("name", "author", "pages")// 指定查询的列
         .where("pages > ?", "400")// 指定查询的约束条件
         .order("pages desc")// 指定查询结果排序
         .limit(10)// 指定查询结果数量
         .offset(2)// 指定查询结果偏移-抛弃前2条
         .find(Book.class);// 指定查询的表名
     ```

     * **`select()`** 方法用于指定查询哪几列的数据
     * **`where()`** 方法用于指定查询的约束条件
     * **`order()`** 方法用于指定查询结果的排序方式 另 **`desc`** 表示降序 **`asc`** 表示升序
     * **`limit()`** 方法用于指定查询结果的数量
     * **`offset()`** 方法用于指定查询结果偏移量

### 07. 小结

1. 文件存储核心是 `Java` 中的 **`I/O 流操作`** 因此需要进行复习练习。
2. 注意 `SharedPreferences` 提交 `apply()` 方法与 `commit()` 方法。
3. 数据库的原生 API 使用及一些第三方库的使用。
4. 数据库操作对象及游标对象等在使用结束后一定要进行关闭。





## 第 7 章 内容提供器

### 01. 内容提供器简介

1. **跨程序共享数据**，内容提供器 **Content Provider** 主要用于在**不同**的应用**程序**之间是实现**数据共享**功能。它提供了一套**完整的机制**，允许一个程序**访问**另一个程序中的数据，同时还能保证**被访问**数据的**安全性**。
2. 内容提供器可以**选择**只对**哪一部分**数据进行**共享**，从而保证我们程序中的**隐私数据**不会有泄漏的风险。

### 02. 运行时权限

1. `Android 6.0 以下`版本**安装时**授权，不授权**不安装**。可在应用管理界面**查看**权限**申请情况**。

2. `Android 6.0 及以上`版本**运行时**授权，不授权**部分功能不能用**。可在应用管理界面**管理**权限**授权或不授权**。

3. 权限分类

   * 普通权限
   * 危险权限
   * 特殊权限

4. 每个危险权限都属于一个**权限组**，申请的**某个权限被授权**时，**该组所有权限**也会同时被授权。

5. 请求权限核心方法

   * **`ContextCompat.checkSelfPermission(@NonNull Context context, @NonNull String permission)`** 检查是否有权限

   * **`ActivityCompat.requestPermissions(Activity activity, String[] permissions, int requestCode)`** 请求权限

   * **`onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)`** 权限请求结果的回调

     ```java
     /**
      * 点击按钮执行操作
      */
     public void request(View view) {
       if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {// 判断没有权限
         ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);// 请求权限[上下文][权限数组集合][请求码]
         return;
       } else {// 判断有权限
         callPhone();
       }
     }

     /**
      * 请求权限用户操作后回调函数
      *
      * @param requestCode  请求码
      * @param permissions  权限数组集合
      * @param grantResults 授权情况数组集合
      */
     @Override
     public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       switch (requestCode) {
         case 1:
           if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
             callPhone();
           } else {
             ToastUtil.showShortToast(this, "You denied the permission");
           }
           break;
       }
     }
     ```

### 03. 访问其他程序的数据

1. 读取系统联系人

   ```java
   private void readContacts() {
     Cursor cursor = null;// 游标对象
     try {
       cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
       if (cursor != null) {
         while (cursor.moveToNext()) {// 循环读取数据
           String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));// 姓名
           String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));// 手机号
         }
       }
     } catch (Exception e) {
       e.printStackTrace();
     } finally {
       if (cursor != null) {
         cursor.close();// 关闭游标
       }
     }
   }
   ```

2. **ContentResolver** 的基本用法

   1. 通过 `Context` 中的 `getContentResolver()` 方法获取到 `ContentResolver` 的实例。

   2. 利用 `ContentResolver` 实例进行数据的 `CRUD` 操作

      * `insert()` 方法进行`添加`数据
      * `update()` 方法进行`更新`数据
      * `delete()` 方法进行`删除`数据
      * `query()` 方法进行`查询`数据

   3. `不同于` SQLite 的是方法都不接收表名参数，而是使用一个 **`Uri`** 参数代替。

   4. 内容 URI 给内容提供器中的数据建立了**唯一标识符**，主要由两部分组成：`authority` 和 `path`

      * `authority` 用于对不同的应用程序做区分，采用包名进行命名

      * `path` 则是用于对同一应用不同表名进行区分，添加在 `authority` 之后

      * `schema` 协议添加于头部

        ```java
        String uriString = "content://com.just.first/table";
        Uri uri = Uri.parse(uriString);
        ```

   5. 查询数据

      ```java
      Cursor cursor = getContentResolver().query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
      ```

      * 查询返回 `Cursor` 对象

      | query() 方法参数  | 对应 SQL 部分                 | 描述                  |
      | ------------- | ------------------------- | ------------------- |
      | uri           | from table_name           | 指定查询某应用程序的某张表       |
      | projection    | select column1, column2   | 指定查询的列名             |
      | selection     | where column = value      | 指定 where 的约束条件      |
      | selectionArgs | -                         | 为 where 中的占位符提供具体的值 |
      | sortOrder     | order by column1, column2 | 指定查询结果的排序方式         |

   6. 添加数据

      ```java
      getContentResolver().insert(Uri url, ContentValues values);
      ```

      * 同样使用 `ContentValues` 键值对进行数据的封装

   7. 修改数据

      ```java
      getContentResolver().update(Uri uri, ContentValues values, String where, String[] selectionArgs)
      ```

   8. 删除数据

      ```java
      getContentResolver().delete(Uri uri, ContentValues values, String where, String[] selectionArgs)
      ```

### 04. 创建自己的内容提供器

1. 自定义内容提供器继承 `ContentProvider`

2. 实现 6 个抽象方法

   ```java
   /**
    * 7.4.1 自定义内容提供器
    *
    * @author JustDo23
    */
   public class FirstContentProvider extends ContentProvider {

     /**
      * 初始化内容提供器。完成数据库的创建和升级操作。[只有当存在 ContentResolver 尝试访问时才会初始化]
      *
      * @return [true, 初始化成功][false,初始化失败]
      */
     @Override
     public boolean onCreate() {
       return false;
     }

     /**
      * 从内容提供器查询数据。
      *
      * @param uri           指定查询哪张表
      * @param projection    确定查询哪些列
      * @param selection     约束查询哪些行
      * @param selectionArgs 为约束赋值
      * @param sortOrder     查询结果排序
      * @return 游标对象
      */
     @Nullable
     @Override
     public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
       return null;
     }

     /**
      * 向内容提供器中添加数据。
      *
      * @param uri    指定哪张表
      * @param values 待添加数据键值对
      * @return 返回一个用户表示这条新纪录的 URI
      */
     @Nullable
     @Override
     public Uri insert(Uri uri, ContentValues values) {
       return null;
     }

     /**
      * 更新内容提供器中已有数据。
      *
      * @param uri           指定哪张表
      * @param values        待更新数据键值对
      * @param selection     约束更新哪些行
      * @param selectionArgs 为约束赋值
      * @return 返回受影响的行数
      */
     @Override
     public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
       return 0;
     }

     /**
      * 从内容提供器中删除数据。
      *
      * @param uri           指定哪张表
      * @param selection     约束删除哪些行
      * @param selectionArgs 为约束赋值
      * @return 返回被删除的行数
      */
     @Override
     public int delete(Uri uri, String selection, String[] selectionArgs) {
       return 0;
     }

     /**
      * 返回 MIME 类型
      *
      * @param uri 指定哪张表
      * @return 返回 MIME 类型
      */
     @Nullable
     @Override
     public String getType(Uri uri) {
       return null;
     }

   }
   ```

3. 通配符

   一个标准的内容 URI 写法

   ```java
   content://com.just.first/table
   ```

   表示访问应用 `com.just.first` 中的 `table` 数据表。还可以在其后添加一个 `id`

   ```java
   content://com.just.first/table/23
   ```

   表示访问表中 `id` 为 `23` 的数据。

   `内容 URI` 的格式主要有以上**两种**，以**路径结尾**就表示期望访问**该表**中的**所有**数据，以 **id** 结尾就表示期望访问**该表**中拥有相应 id 的数据。可以使用**通配符**来分别匹配这两种格式的内容 URI。

   * **星号**表示**匹配任意长度的任意字符**
   * **井号**表示**匹配任意长度的数字**

   一个能够**匹配任意表**的内容 URI 格式可以写成

   ```java
   content://com.just.first/*
   ```

   一个能够**匹配表中任意一行数据**的内容 URI 格式可以写成

   ```java
   content://com.just.first/table/#
   ```

4. 通配符使用

   ```java
   public class FirstContentProvider extends ContentProvider {

     public static final int TABLE_1_DIR = 0;// 自定义码
     public static final int TABLE_1_ITEM = 1;
     public static final int TABLE_2_DIR = 2;
     public static final int TABLE_2_ITEM = 3;

     public static UriMatcher uriMatcher;// 用于匹配的对象
     public static final String PACKAGE_NAME = "com.just.first";// 主包名

     static {// 静态代码块
       uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);// 用于匹配的对象
       uriMatcher.addURI(PACKAGE_NAME, "table1", TABLE_1_DIR);// 添加路径
       uriMatcher.addURI(PACKAGE_NAME, "table1/#", TABLE_1_ITEM);// 可以使用通配符
       uriMatcher.addURI(PACKAGE_NAME, "table2", TABLE_2_DIR);
       uriMatcher.addURI(PACKAGE_NAME, "table2/#", TABLE_2_ITEM);
     }

     @Override
     public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
       switch (uriMatcher.match(uri)) {// 进行匹配并返回相应的自定义码
         case TABLE_1_DIR:
           LogUtils.e("查询 table1 表中的所有数据");
           break;
         case TABLE_1_ITEM:
           LogUtils.e("查询 table1 表中的单条数据");
           break;
         case TABLE_2_DIR:
           LogUtils.e("查询 table2 表中的所有数据");
           break;
         case TABLE_2_ITEM:
           LogUtils.e("查询 table2 表中的单条数据");
           break;
       }
       return null;
     }

   }
   ```

5. 关于类型

   1. `getType()` 方法是所有内容提供器必须提供的一个方法，用于获取相应的 **MIME** 类型。

   2. 一个内容 URI 所对应的 `MIME` 字符串主要由 **3** 部分组成

      * `必须`以 **vnd** 开头
      * 如果 URI 以`路径`结尾则后接 `android.cursor.dir/`
      * 如果 URI 以 `id` 结尾则后接 `android.cursor.item/`
      * 最后接上 `vnd.<authority>.<path>`

      **内容 URI**

      ```java
      content://com.just.first/table
      ```

      **返回 MIME 类型**

      ```java
      vnd.android.cursor.dir/vnd.com.just.first.table
      ```

      **内容 URI**

      ```java
      content://com.just.first/table/23
      ```

      **返回 MIME 类型**

      ```java
      vnd.android.cursor.item/vnd.com.just.first.table
      ```

   3. 根据以上内容重写 `getType()` 方法

6. 数据安全问题

   **因为**所有的 **CRUD** 操作都**一定**要**匹配**到相应的内容 **URI** 格式才能进行，而我们当然**不可能**向 UriMatcher 中添加**隐私数据**的 URI，所以这部分数据根本**无法**被外部程序访问到，安全问题也就不存在了。

### 05. 实现跨程序数据共享

1. 跨进程访问时不能直接使用 **Toast**

2. 使用内容提供器需要进行注册

   ```java
   <provider
         android:name=".chapter07.DataBaseProvider"
         android:authorities="com.just.first.provider"
         android:enabled="true"
         android:exported="true" />
   ```

### 06. 进阶 Git

1. 忽略文件

   项目目录下 `.gitignore` 是忽略文件，允许用户将指定的文件排除在版本控制之外。

2. 查看状态

   ```shell
   $ git status
   ```

3. 查看修改内容

   ```shell
   $ git diff
   ```

   其后可以指定文件来查看该文件的更改记录

   * **加号**代表**新增**
   * **减号**代表**删除**

4. 撤销未添加的修改

   ```shell
   $ git checkout fileName
   ```

   * 前提是还没有执行 `add` 命令

5. 撤销未提交的修改

   ```shell
   $ git reset HEAD
   ```

   * 前提是执行了 `add` 命令但还没有执行 `commit` 命令

6. 查看提交记录

   ```shell
   $ git log
   ```

   * 提交记录包含 `提交 id` 及 `提交人` 及 `提交日期` 及 `提交描述` 这4个信息

   ```shell
   $ git log id -l
   ```

   * 命令后为 `提交 id` 及 `小写-L` 查看该 ID 的记录

   ```shell
   $ git log id -l -p
   ```

   * 查看该 ID 的修改内容

### 07. 小结

1. 运行时权限
2. 内容提供者





## 第 8 章 多媒体

### 01. 通知

1. **通知 Notification** 在手机最上方的**状态栏**中会显示一个通知的**图标**，**下拉**状态栏后可以看到通知的**详细内容**。

2. 通知可以在**活动**里创建，可以在**广播接收器**中创建，可以在**服务**里创建。

3. 创建并显示通知

   ```java
   private void showNotification() {
     NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);// 获取通知管理对象
     Notification notification = new NotificationCompat.Builder(this)// Builder 设计模式
         .setContentTitle("This is title")// 设置标题
         .setContentText("This is content")// 设置文本
         .setWhen(System.currentTimeMillis())// 指定通知被创建的时间以毫秒为单位
         .setSmallIcon(R.mipmap.ic_launcher)// 设置小图标只能使用纯 alpha 图层的图片
         .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_mario))// 设置大图标
         .build();// 创建
     notificationManager.notify(23, notification);// 通知管理器去显示该条通知[通知的ID][通知对象]要保证 ID 的不同
   }
   ```

   * 通过 **Context** 获取**通知管理**对象
   * 为了保证各个版本**兼容**使用 `support-v4` 包中的 `NotificationCompat` 来创建 `NotificationManager` 对象
   * 注意设计模式之 `Builder` 设计模式
   * 设置**小图标**只能使用**纯 alpha 图层**的图片
   * 通知显示时需要 **ID** 同时要保证 ID 的不同

4. **PendingIntent**

   * 类似 **Intent** 指明意图，可用于`启动活动`，`启动服务`，`发送广播`等。
   * 不同点 **Intent** 更加倾向于**立即执行**某个动作，而 **PendingIntent** 更加倾向于**在某个合适的时机去执行**某个动作。
   * 可以把 **PendingIntent** 简单理解为**延迟执行**的 **Intent**

   ```java
   Intent intent = new Intent(this, MainActivity.class);
   PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
   ```

   * 第**二**个参数**请求码**
   * 第**四**个参数**确定行为的标识**，取值有四种 `FLAG_ONE_SHOT` , `FLAG_NO_CREATE` , `FLAG_CANCEL_CURRENT` , `FLAG_UPDATE_CURRENT`

5. 通知取消

   * 在 `build()` 方法之前进行调用

     ```java
     .setAutoCancel(true)// 设置自动取消
     ```

   * 通过**通知管理对象**取消**指定 ID** 的通知

     ```java
     notificationManager.cancel(23);// 通知管理对象取消指定 ID 的通知
     ```

### 02. 通知进阶

1. 设置声音

   ```java
   .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/23_Game.ogg")))// 设置声音
   ```

   * 通过 **Uri** 传递一个**音频文件**的地址

2. 设置振动

   ```java
   .setVibrate(new long[]{0, 1000, 1000, 1000, 1000, 1000})// 设置振动[静止时长][振动时长]单位毫秒
   ```

   * 参数是**长整型**的**数组**，用于设置手机**静止**和**振动**的**时长**，以**毫秒**为单位
   * 参数顺序 `[静止时长][振动时长][静止时长][振动时长]` 如此循环
   * 振动需要手机**权限**

   ```java
   <uses-permission android:name="android.permission.VIBRATE" />
   ```

3. 设置 LED 灯

   ```java
   .setLights(Color.RED, 1000, 1000)// 设置 LED 灯 [颜色][亮灯时长][灭灯时长]单位毫秒
   ```

   * 发送通知后**息屏**过一会儿观察 LED 灯
   * 程序被死之后 LED 也会停止闪烁

4. 设置通知为默认配置

   ```java
   .setDefaults(NotificationCompat.DEFAULT_ALL)// 设置默认配置
   ```

   * 它会根据当前手机的环境来决定播放什么铃声，以及如何振动等

### 03. 通知高阶

1. 构建富文本通知

   * 显示**超长**的文本

     ```java
     .setStyle(new NotificationCompat.BigTextStyle().bigText("If we can only encounter each other rather than stay with each other,then I wish we had never encountered."))// 显示特别长的文本
     ```

   * 显示**大图片**

     ```java
     .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_mountain)))// 显示大图片
     ```

2. 设置通知重要程度

   ```java
   .setPriority(NotificationCompat.PRIORITY_MAX)// 设置通知的重要程度
   ```

   * `PRIORITY_DEFAULT` 默认程度，和不设置一样
   * `PRIORITY_MIN` 最低重要程度，系统会在特定情况显示比如下拉状态栏的时候
   * `PRIORITY_LOW` 较低重要程度，系统会将通知缩小或改变其显示的顺序将其靠后
   * `PRIORITY_HIGH` 较高重要程度，系统会将通知放大或改变其显示的顺序将其靠前
   * `PRIORITY_MAX` 最高重要程度，必须让用户立刻看到甚至需要用户做出响应操作

### 04. 摄像头

1. 调用摄像头拍照

   ```java
   private Uri imageUri;// 获取一个 URI 对象
   public static final int TAKE_PHOTO = 23;

   /**
    * 调用摄像头拍照
    */
   public void takePhoto(View view) {
     File imageFile = new File(getExternalCacheDir(), "image.jpg");// 指定文件的路径及名称
     if (imageFile.exists()) {
       imageFile.delete();// 文件存在就删除
     }
     try {
       imageFile.createNewFile();// 创建新的文件
     } catch (IOException e) {
       e.printStackTrace();
     }
     // 内容提供者
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {// Android 7.0 进行适配
       imageUri = FileProvider.getUriForFile(this, "com.just.first.fileprovider", imageFile);// [上下文][任意一个唯一字符串][File对象]
     } else {// 这个 URI 标识者图片的本地真是路径
       imageUri = Uri.fromFile(imageFile);
     }
     Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");// 创建意图并指定 Action
     intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);// 携带参数
     startActivityForResult(intent, TAKE_PHOTO);// 启动意图
   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     switch (requestCode) {// 此方式中指定了拍照图片位置因此 data 为 null
       case TAKE_PHOTO:
         try {// 手机拍照图片一般3M左右因此处理图片内存溢出需要注意
           Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));// 从内容提供者中获取数据
           iv_photo.setImageBitmap(bitmap);// 进行图片的显示
         } catch (FileNotFoundException e) {
           e.printStackTrace();
         }
         break;
     }
   }
   ```

2. 兼容适配

   调用 **FileProvider** 的 **getUriForFile()** 方法将 **File** 对象转换成一个**封装过的 Uri 对象**。该 **getUriForFile()** 方法接收**3个参数**，第一个 **Context** 对象，第二个可以是**任意唯一的字符串**，第三个是 **File** 对象。从 **Android 7.0** 开始直接使用本地真实路径的 Uri 被认为是**不安全**的，会抛出一个 **FileUriExposedException** 异常。而 **FileProvider** 则是一种**特殊的内容提供器**，它使用了和内容提供器**类似的机制**来对数据进行保护，可以**选择性地**将封装过的 Uri 共享给外部，从而提高了应用的安全性。

   **注意：**并没有结束，还需要在功能清单中注册内容提供器

   ```java
   <!-- Android 7.0 -->
   <provider
     android:name="android.support.v4.content.FileProvider"
     android:authorities="com.just.first.fileprovider"
     android:exported="false"
     android:grantUriPermissions="true">
     <meta-data
       android:name="android.support.FILE_PROVIDER_PATHS"
       android:resource="@xml/file_paths" />
   </provider>
   ```

   * `android:name` 属性的值是固定的
   * `android:authorities` 属性的值必须和 `getUriForFile()` 方法第二个参数一致
   * `<meta-data/>` 标签指定 Uri 的共享路径，引用了一个资源文件
   * 在项目的 `res` 路径下创建 `xml` 文件夹并创建 `file_paths.xml` 文件

     ```java
     <?xml version="1.0" encoding="utf-8"?>
     <resources>
       <paths>
         <external-path
           name="camera_photos"
           path="Android/data/com.just.first/" />
         <external-path
           name="external_storage_root"
           path="." />
       </paths>
     </resources>
     ```

     * `<external-path/>` 标签指定 Uri 共享
     * `name` 属性可以**随便填写**
     * `path` 属性值表示**共享的具体路径**
     * `path` 属性不填写表示**将整个 SD 卡进行共享**

3. 权限

   ```java
   <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
   ```

   在 **Android 4.4** 系统之后访问 **SD 卡**的**应用关联目录**不用声明权限。

### 05. 相册

1. 从相册中选择照片

   ```java
   public static final int CHOOSE_ALBUM = 24;

   /**
    * 点击按钮从手机相册中选取
    */
   public void chooseAlbum(View view) {
     if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {// 检查是否有权限
       ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 25);// 没有权限进行申请权限
     } else {
       openAlbum();// 有权限则打开相册
     }
   }

   @Override
   public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
     switch (requestCode) {
       case 25:
         if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
           openAlbum();// 有权限则打开相册
         } else {
           ToastUtil.showShortToast(this, "You denied the permission.");
         }
         break;
     }
   }

   private void openAlbum() {
     Intent intent = new Intent("android.intent.action.GET_CONTENT");// 指定 action
     intent.setType("image/*");// 指定类型
     startActivityForResult(intent, CHOOSE_ALBUM);// 通过 Intent 打开相册
   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     switch (requestCode) {
       case CHOOSE_ALBUM:
         if (RESULT_OK == resultCode) {// 正常的返回码
           if (Build.VERSION.SDK_INT >= 19) {// Android 4.4 及以上版本
             HandleImageOnKitKat(data);
           } else {// Android 4.4 以下版本
             HandleImageBeforeKitKat(data);
           }
         }
         break;
     }
   }

   @RequiresApi(api = Build.VERSION_CODES.KITKAT)
   private void HandleImageOnKitKat(Intent data) {
     String imagePath = null;// 图片路径
     Uri uri = data.getData();// 获取 Uri 对象
     if (DocumentsContract.isDocumentUri(this, uri)) {//  如果是 Document 类型的 Uri
       String documentId = DocumentsContract.getDocumentId(uri);
       if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
         String id = documentId.split(":")[1];// 解析出数字格式的 id
         String selection = MediaStore.Images.Media._ID + "=" + id;
         imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
       } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
         Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
         imagePath = getImagePath(contentUri, null);
       }
     } else if ("content".equalsIgnoreCase(uri.getScheme())) {//  如果是 content 类型的 Uri
       imagePath = getImagePath(uri, null);
     } else if ("file".equalsIgnoreCase(uri.getScheme())) {//  如果是 file 类型的 Uri
       imagePath = uri.getPath();
     }
     displayImage(imagePath);// 文件路径进行图片展示
   }

   private void HandleImageBeforeKitKat(Intent data) {
     Uri uri = data.getData();
     String imagePath = getImagePath(uri, null);
     displayImage(imagePath);
   }

   private String getImagePath(Uri uri, String selection) {
     String path = null;// 通过内容提供者获取图片路径
     Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
     if (cursor != null) {
       if (cursor.moveToFirst()) {// 拿出第一条数据
         path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
       }
       cursor.close();// 游标用完要关闭
     }
     return path;
   }

   private void displayImage(String imagePath) {
     if (!TextUtils.isEmpty(imagePath)) {// 注意内存溢出
       Bitmap bitmap = BitmapFactory.decodeFile(imagePath);// 利用工厂类从路径加载出图片
       iv_photo.setImageBitmap(bitmap);// 显示图片
     } else {
       ToastUtil.showShortToast(this, "Failed to get image.");
     }
   }
   ```

2. 动态权限申请

   相册中的图片都是**存储在 SD 卡**上的，我们要从 SD卡中读取照片就需要申请这个权限。

3. 意图启动

   指定意图 **action** 为 `android.intent.action.GET_CONTENT` 以及意图**类型**为 `image/*` 启动相册

4. 兼容适配

   在 Android 4.4 及以上的版本中，选取相册中的图片不再返回图片真实的 Uri 了，而是一个封装过的 Uri 对象，因此需要对这个封装过的对象进行解析。

### 06. 播放音频

1. 使用 MediaPlayer 播放本地音频

   ```java
   private MediaPlayer mediaPlayer;

   /**
    * 初始化播放器
    */
   private void initMediaPlay() {
     release();// 资源释放
     mediaPlayer = new MediaPlayer();// 初始化
     File audioFile = new File(Environment.getExternalStorageDirectory() + "/JustDo23/audio/", "Sugar.mp3");// 指定文件路径
     try {
       mediaPlayer.setDataSource(audioFile.getPath());
       mediaPlayer.prepare();
     } catch (IOException e) {
       e.printStackTrace();
     }
   }

   /**
    * 释放资源
    */
   private void release() {
     if (mediaPlayer != null) {
       if (mediaPlayer.isPlaying()) {
         mediaPlayer.stop();
         mediaPlayer.reset();
       }
       mediaPlayer.release();
       mediaPlayer = null;
     }
   }

   @Override
   public void onClick(View v) {
     switch (v.getId()) {
       case R.id.bt_play:// 播放
         if (mediaPlayer != null) {
           mediaPlayer.start();
         }
         break;
       case R.id.bt_pause:// 暂停
         if (mediaPlayer != null && mediaPlayer.isPlaying()) {
           mediaPlayer.pause();
         }
         break;
       case R.id.bt_stop:// 停止
         initMediaPlay();
         break;
     }
   }
   ```

2. 使用 MediaPlayer 常用方法

   | 方法名             | 功能描述                 |
   | --------------- | -------------------- |
   | setDataSource() | 设置要播放的音频文件的位置        |
   | prepare()       | 进行准备操作               |
   | start()         | 开始或继续播放              |
   | pause()         | 暂停或者继续播放             |
   | reset()         | 重置到刚刚创建的状态           |
   | seekTo()        | 从指定位置开始播放            |
   | stop()          | 停止播放调用之后需要重新初始才能继续播放 |
   | release()       | 释放相关的资源              |
   | isPlaying()     | 是否正在播放               |
   | getDuration()   | 获取载入的音频文件的总时长        |

3. 注意方法的调用顺序

4. 注意配合 Activity 的生命周期进行控制

   ```java
   @Override
   protected void onDestroy() {
     super.onDestroy();
     release();
   }
   ```

### 07. 播放视频

1. 使用 VideoView 播放本地视频

   ```java
   private VideoView videoView;

   /**
    * 初始视频路径
    */
   private void initVideoPath() {
     File videoFile = new File(Environment.getExternalStorageDirectory() + "/JustDo23/video/", "luck.mp4");// 指定文件路径
     videoView.setVideoPath(videoFile.getPath());
   }

   @Override
   public void onClick(View v) {
     switch (v.getId()) {
       case R.id.bt_play:// 播放
         if (!videoView.isPlaying()) {
           videoView.start();
         }
         break;
       case R.id.bt_pause:// 暂停
         if (videoView.isPlaying()) {
           videoView.pause();
         }
         break;
       case R.id.bt_restart:// 停止
         if (videoView.isPlaying()) {
           videoView.resume();
         }
         break;
     }
   }

   @Override
   protected void onDestroy() {
     super.onDestroy();
     if (videoView != null) {
       videoView.suspend();// 资源释放
     }
   }
   ```

2. 使用 VideoView 常用方法

   | 方法名            | 功能描述          |
   | -------------- | ------------- |
   | setVideoPath() | 设置要播放的视频文件的位置 |
   | start()        | 开始或继续播放       |
   | pause()        | 暂停播放          |
   | resume()       | 视频从头开始播放      |
   | seekTo()       | 从指定位置开始播放     |
   | isPlaying()    | 是否正在播放        |
   | getDuration()  | 获取载入的视频文件的总时长 |

### 08. 小结

1. 类似网易云音乐这种可以控制音乐播放的通知的实现。自定义通知布局。
2. 图片的相关处理，避免内存溢出等。
3. 对 MediaPlayer 进行详细的总结。
4. 对 VideoView 进行详细的学习总结。
5. 查阅 VideoView 源码。





## 第 9 章 网络技术

### 01. WebView

1. 使用 WebView 加载网页

   ```java
   private void initWebView() {
     wb_net.getSettings().setJavaScriptEnabled(true);// 支持 JavaScript 脚本
     wb_net.setWebViewClient(new WebViewClient());// 网页跳转仍在当前浏览器
     wb_net.loadUrl("http://www.baidu.com");// 加载网页
   }
   ```

2. 方法

   * `setWebViewClient()` 方法作用，当需要从一个网页跳转另一个网页时，目标网页仍然在当前 WebView 中显示，而不是打开系统浏览器。

3. 网络权限

   ```java
   <uses-permission android:name="android.permission.INTERNET" />
   ```

### 02. 使用 HTTP 协议访问网络

1. 工作原理

   **客户端**向**服务器**发出一条 HTTP **请求**，服务器收到请求之后**返回**一些**数据**给客户端，然后客户端再对这些数据进行**解析**和**处理**就可以了。

2. 使用 HttpURLConnection

   ```java
   private void sendRequestWithHttpURLConnection() {
     new Thread(new Runnable() {// 网络请求耗时操作放在子线程中

       @Override
       public void run() {
         HttpURLConnection httpURLConnection = null;// 连接对象
         BufferedReader bufferedReader = null;// 数据读取流
         try {
           URL url = new URL("http://www.baidu.com");// URL 对象
           httpURLConnection = (HttpURLConnection) url.openConnection();// 打开连接
           httpURLConnection.setRequestMethod("GET");// 设置网络请求模式
           httpURLConnection.setConnectTimeout(8000);// 设置连接超时时间
           httpURLConnection.setReadTimeout(8000);// 设置数据读取超时时间
           InputStream inputStream = httpURLConnection.getInputStream();// 获取数据读取流
           bufferedReader = new BufferedReader(new InputStreamReader(inputStream));// 对流封装提供效率
           StringBuilder response = new StringBuilder();// 请求结果
           String line;
           while ((line = bufferedReader.readLine()) != null) {
             response.append(line);// 从数据读取流中读取数据
           }
           showResponse(response.toString());// 进行界面展示
         } catch (IOException e) {
           e.printStackTrace();
         } finally {
           if (bufferedReader != null) {
             try {
               bufferedReader.close();// 关闭数据流
             } catch (IOException e) {
               e.printStackTrace();
             }
           }
           if (httpURLConnection != null) {
             httpURLConnection.disconnect();// 关闭网络连接
           }
         }
       }
     }).start();
   }

   private void showResponse(final String response) {
     runOnUiThread(new Runnable() {// 界面刷新的工作必须放在主线程中

       @Override
       public void run() {
         tv_result.setText(response);
       }
     });
   }
   ```

3. 小细节

   * 网络请求模式有 `GET` 和 `POST` 等，其中 `GET` 表示希望从服务器那里**获取**数据，而 `POST` 表示希望**提交**数据给服务器。
   * 设置**网络连接**超时时间
   * 设置**数据读取**超时时间
   * 设置**请求头**数据
   * 网络请求等**耗时操作**需要放在**子线程**
   * **界面控件刷新**需要放在**主线程**

### 03. 使用 OKHttp

1. 添加依赖

   ```groovy
   compile 'com.squareup.okhttp3:okhttp:3.4.1'// OKHttp
   ```

2. 使用 OKHttp

   ```java
   private void sendRequestWithOkHttp() {
     new Thread(new Runnable() {// 网络请求耗时操作放在子线程中

       @Override
       public void run() {
         try {
           OkHttpClient okHttpClient = new OkHttpClient();// OK 客户端
           RequestBody requestBody = new FormBody.Builder()
               .add("userName", "admin")
               .add("passWord", "232323")
               .build();// 参数封装
           Request request = new Request.Builder()
               .url("https://www.baidu.com")
               .post(requestBody)// 用 POST 请求携带参数
               .build();
           Response response = okHttpClient.newCall(request).execute();// 执行请求返回响应对象
           String responseContent = response.body().string();// 从响应对象中获取字符串
           showResponse(responseContent);// 进行界面展示
         } catch (IOException e) {
           e.printStackTrace();
         }
       }
     }).start();
   }
   ```

### 04. Pull 方式解析 XML

1. XML 格式数据内容

   ```xml
   <apps>
     <app>
       <id>1</id>
       <name>Google</name>
       <version>1.1</version>
     </app>
     <app>
       <id>2</id>
       <name>FaceBook</name>
       <version>1.2</version>
     </app>
     <app>
       <id>3</id>
       <name>Twitter</name>
       <version>1.3</version>
     </app>
   </apps>
   ```

2. 使用 Pull 解析

   ```java
   private void parseXMLWithPull(String responseContent) {
     try {
       XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();// 获取工厂实例
       XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();// 工厂实例获取一个解析器
       xmlPullParser.setInput(new StringReader(responseContent));// 以流的方式给解析器设置数据源
       int eventType = xmlPullParser.getEventType();// 获取事件类型
       String id = "";
       String name = "";
       String version = "";
       while (eventType != XmlPullParser.END_DOCUMENT) {// 不是文档结尾
         String nodeName = xmlPullParser.getName();// 获取节点名称
         switch (eventType) {// 事件类型
           case XmlPullParser.START_TAG:// 标签开始
             if ("id".equals(nodeName)) {// 判断标签名称
               id = xmlPullParser.nextText();// 获取标签中的内容
             } else if ("name".equals(nodeName)) {
               name = xmlPullParser.nextText();
             } else if ("version".equals(nodeName)) {
               version = xmlPullParser.nextText();
             }
             break;
           case XmlPullParser.END_TAG:// 标签结束
             if ("app".equals(nodeName)) {
               LogUtils.e("id = " + id + " name = " + name + " version = " + version + "\n");
             }
             break;
           default:
             break;
         }
         eventType = xmlPullParser.next();// 获取下一个事件
       }
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
   ```

3. 重要方法

   * `getName()` 方法获取当前节点名称
   * `nextText()` 方法获取当前节点内的具体内容

### 05. SAX 方式解析 XML

1. 自定义 SaxHandler 继承自 DefaultHandler

   ```java
   /**
    * 9.3.2 SAX 解析方式
    *
    * @author JustDo23
    * @since 2017年08月01日
    */
   public class SaxHandler extends DefaultHandler {

     private String nodeName;
     private StringBuilder id;
     private StringBuilder name;
     private StringBuilder version;

     /**
      * 开始解析文档
      *
      * @throws SAXException 异常
      */
     @Override
     public void startDocument() throws SAXException {
       super.startDocument();
       id = new StringBuilder();
       name = new StringBuilder();
       version = new StringBuilder();
     }

     /**
      * 开始解析节点
      *
      * @param uri        命名空间字符串[可能为空]
      * @param localName  节点名称[可能为空]
      * @param qName      限定名[可能为空]
      * @param attributes 属性
      * @throws SAXException 异常
      */
     @Override
     public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       super.startElement(uri, localName, qName, attributes);
       nodeName = localName;// 当前节点名称
     }

     /**
      * 获取节点内容[可能会调用多次，一些换行符也被当作内容解析出来]
      *
      * @param ch     字节数组
      * @param start  起始位置
      * @param length 有效长度
      * @throws SAXException
      */
     @Override
     public void characters(char[] ch, int start, int length) throws SAXException {
       super.characters(ch, start, length);
       if ("id".equals(nodeName)) {
         id.append(ch, start, length);
       } else if ("name".equals(nodeName)) {
         name.append(ch, start, length);
       } else if ("version".equals(nodeName)) {
         version.append(ch, start, length);
       }
     }

     /**
      * 完成节点解析
      *
      * @param uri       命名空间字符串[可能为空]
      * @param localName 节点名称[可能为空]
      * @param qName     限定名[可能为空]
      * @throws SAXException 异常
      */
     @Override
     public void endElement(String uri, String localName, String qName) throws SAXException {
       super.endElement(uri, localName, qName);
       if ("app".equals(localName)) {
         LogUtils.e("id = " + id.toString() + " name = " + name.toString() + " version = " + version.toString() + "\n");
         id.setLength(0);
         name.setLength(0);
         version.setLength(0);
       }
     }

     /**
      * 完成文档解析
      *
      * @throws SAXException 异常
      */
     @Override
     public void endDocument() throws SAXException {
       super.endDocument();
       LogUtils.e("SAX 解析结束");
     }

   }
   ```

2. 使用 SAX 解析

   ```java
   private void parseXMLWithSAX(String responseContent) {
     try {
       SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();// 获取工厂实例
       XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();// 利用工厂获取解析器后获取XML读取器
       SaxHandler saxHandler = new SaxHandler();// 实例化自定义的 Handler
       xmlReader.setContentHandler(saxHandler);//  读取器设置 Handler
       xmlReader.parse(new InputSource(new StringReader(responseContent)));// 开始解析
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
   ```

### 06. 解析 JSON 格式数据

1. json 格式数据内容

   ```json
   [
     {
       "id": "4",
       "name": "Chrome",
       "version": "1.4"
     },
     {
       "id": "5",
       "name": "Safari",
       "version": "1.5"
     },
     {
       "id": "6",
       "name": "Firefox",
       "version": "1.6"
     }
   ]
   ```

2. 使用 JSONObject

   ```java
   private void parseJson(String responseContent) {
     try {
       JSONArray jsonArray = new JSONArray(responseContent);// 获取数组对象
       for (int i = 0; i < jsonArray.length(); i++) {// 对数组进行循环
         JSONObject jsonObject = jsonArray.getJSONObject(i);// 挨个获取JSONObject
         String id = jsonObject.getString("id");
         String name = jsonObject.getString("name");
         String version = jsonObject.getString("version");
         LogUtils.e("id = " + id + " name = " + name + " version = " + version + "\n");
       }
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
   ```

3. 使用 Gson

   1. 添加依赖

      ```groovy
      compile 'com.google.code.gson:gson:2.8.1'// Gson
      ```

   2. 定义实体类

   3. 使用 Gson

      ```java
      private void parseJsonWithGson(String responseContent) {
        Gson gson = new Gson();// 实例化对象
        List<Product> productList = gson.fromJson(responseContent, new TypeToken<List<Product>>() {}.getType());// 解析数组方法
        for (Product product : productList) {
          LogUtils.e("id = " + product.getId() + " name = " + product.getName() + " version = " + product.getVersion() + "\n");
        }
      }
      ```

### 07. 最佳实践

1. 回调接口

   ```java
   public interface HttpCallBackListener {

     /**
      * 网络请求完成时回调
      *
      * @param response 返回数据
      */
     void onFinish(String response);

     /**
      * 网络请求出现错误
      *
      * @param e 异常
      */
     void onError(Exception e);

   }
   ```

2. 工具类封装

   ```java
   public class HttpUtil {

     public static void sendHttpRequest(final String address, final HttpCallBackListener httpCallBackListener) {
       new Thread(new Runnable() {

         @Override
         public void run() {
           HttpURLConnection httpURLConnection = null;
           try {
             URL url = new URL(address);
             httpURLConnection = (HttpURLConnection) url.openConnection();
             httpURLConnection.setRequestMethod("GET");
             httpURLConnection.setConnectTimeout(8000);
             httpURLConnection.setReadTimeout(8000);
             httpURLConnection.setDoInput(true);
             httpURLConnection.setDoOutput(true);
             InputStream inputStream = httpURLConnection.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
             StringBuilder response = new StringBuilder();
             String line;
             while ((line = bufferedReader.readLine()) != null) {
               response.append(line);
             }
             if (httpCallBackListener != null) {
               httpCallBackListener.onFinish(response.toString());
             }
           } catch (Exception e) {
             if (httpCallBackListener != null) {
               httpCallBackListener.onError(e);
             }
           } finally {
             if (httpURLConnection != null) {
               httpURLConnection.disconnect();
             }
           }
         }
       }).start();
     }

     public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
       OkHttpClient okHttpClient = new OkHttpClient();
       Request request = new Request.Builder()
           .url(address)
           .build();
       okHttpClient.newCall(request).enqueue(callback);// 开启子线程
     }

   }
   ```

3. 注意线程问题

### 08. 小结

1. 对 **WebView** 进行更详细的学习使用。
2. 对 **HTTP 协议**进行更详细的学习使用。
3. 如果可以那就看看 OKHttp 等的**源码解析**。
4. 其他第三方网络请求框架简单了解。





## 第 10 章 服务

### 01. 服务是什么

1. **服务 Service** 是 Android 中实现程序**后台运行**的解决方案，它非常适合去执行那些**不需要**和用户**交互**而且还要求**长期运行**的任务。

2. 需要**注意**的是服务**并不是**运行在一个独立的进程当中，而是**依赖于**创建服务时所在的**应用程序进程**。当某个应用程序进程被**杀掉时**，所有依赖于该进程的服务**也会停止运行**。

3. 实际上服务**并不会**自动**开启线程**，所有的代码都是**默认**运行在**主线程**当中的。服务中的**耗时操作**仍然需要我们为其创建**子线程**，否则会出现主线程**被阻塞**的情况。

### 02. 线程的基本用法

1. 继承 Thread

   ```java
   class FirstThread extends Thread {

     @Override
     public void run() {
       // 耗时操作
     }
   }
   ```

   启动线程

   ```java
   new FirstThread().start();
   ```

2. 实现 Runnable

   ```java
   class FirstRunnable implements Runnable {

     @Override
     public void run() {
       // 耗时操作
     }
   }
   ```

   启动线程

   ```java
   new Thread(new FirstRunnable()).start();
   ```

3. 综合匿名内部类

   ```java
   new Thread(new Runnable() {

     @Override
     public void run() {
       // 耗时操作
     }
   }).start();
   ```

### 03. 更新 UI 操作

1. **Android** 中的 **UI** 是**线程不安全**的，也就是说**必须**在**主线程**中进行更新，否则会**异常**。

2. 在**子线程**中需要更新 UI 是可以使用 **Handler** 机制进行。

   ```java
   private Handler handler = new Handler() {

     @Override
     public void handleMessage(Message msg) {
       super.handleMessage(msg);
       switch (msg.what) {
         case 32:
           // 更新 UI 操作
           tv_result.setText("Nice to meet you");
           break;
       }
     }
   };

   public void uiReferenceHandler(View view) {
     new Thread(new Runnable() {

       @Override
       public void run() {
         Message message = handler.obtainMessage();// 获取消息对象
         message.what = 32;// 设置标志码
         handler.sendMessage(message);// 发送消息
       }
     }).start();
   }
   ```

### 04. 解析异步消息处理机制

1. 异步消息处理主要由 4 个部分组成

   * **`Message`** 是在**线程**之间**传递**的**消息**，它可以在内部**携带**少量的**信息**，用于在**不同线程之间交换数据**。
   * **`Handler`** 是**消息处理者**，主要用于**发送**和**处理**消息。**发送消息**一般是使用 **Handler** 的 **`sendMessage()`** 方法，而发出的消息经过一系列地辗转处理后，**最终**会**传递**到 **Handler** 的 **`handleMessage()`** 方法中。
   * **`MessageQueue`** 是**消息队列**，它主要用于**存放所有**通过 **Handler** 发送的消息。这部分消息会**一直存在**于**消息队列**中，**等待被处理**。**每个线程**中**只会有一个**消息队列 **`MessageQueue`** 对象。
   * **`Looper`** 是**每个线程**中的 **MessageQueue** 的**管家**，调用 **Looper** 的 **`loop()`** 方法后，就会进入到一个**无限循环**当中，然后每当发现 **`MessageQueue`** 中存在**一条消息**，就会**将它取出**，并**传递**到 **Handler** 的 **`handleMessage()`** 方法中。**每个线程**中也只**会有一个 Looper** 对象。

2. 上小节中由于 **Handler** 是在**主线程**中**创建**的，所以此时 `handlerMessage()` 方法中代码也会在**主线程中运行**，于是就可以**安心**进行 UI 操作了。

3. 上小节中使用的 **`runOnUiThread()`** 方法其实就是一个**异步消息处理机制**的**接口封装**。

### 05. 使用 AsyncTask

1. 使用 **AsyncTask** 可以十分简单地从子线程**切换**到主线程。当然，AsyncTask 背后的**实现原理**也是基于**异步消息处理机制**。

2. 自定义类**继承**抽象类 **`AsyncTask<Params, Progress, Result>`** 同时指定 **3** 个**泛型参数**

   * **`Params`** 在执行 AsyncTask 时需要传入的参数，可用于在后台任务中使用。
   * **`Progress`** 后台任务执行时，如果需要在界面上显示当前的进度，则使用这里指定的泛型作为进度单位。
   * **`Result`** 当任务执行完毕后，如果需要对结果进行返回，则使用这里指定的泛型作为返回值类型。

3. 实现抽象类中的抽象方法

   * **`onPreExecute()`**
     * 后台任务开始之前进行回调
     * 用于进行一些界面上的初始化操作
   * **`doInBackground(Params... params)`**
     * 在子线程中执行耗时操作
     * 耗时操作执行结束之后将结果返回
     * 此方法中不能进行 UI 操作
   * **`onProgressUpdate(Progress... values)`**
     * 后台耗时操作执行过程中的进度回调
     * 此方法中可以进行 UI 操作
   * **`onPostExecute(Result result)`**
     * 后台耗时操作执行结束并通过 return 语句进行返回时被调用
     * 可以利用返回的数据进行 UI 的刷新

4. 使用 AsyncTask

   ```java
   /**
    * 自定义异步任务
    *
    * @since 2017年08月03日
    */
   class NetAsyncTask extends AsyncTask<String, Integer, String> {

     /**
      * 任务启动之前
      */
     @Override
     protected void onPreExecute() {
       super.onPreExecute();
     }

     /**
      * 任务启动并后台运行
      */
     @Override
     protected String doInBackground(String... params) {
       publishProgress(20);// 进行进度刷新
       return null;
     }

     /**
      * 任务运行进度
      */
     @Override
     protected void onProgressUpdate(Integer... values) {
       super.onProgressUpdate(values);
     }

     /**
      * 任务执行完毕
      */
     @Override
     protected void onPostExecute(String s) {
       super.onPostExecute(s);
     }

   }
   ```

   * **`publishProgress(Progress... values)`**
       * 进行进度刷新调用之后会回调 `onProgressUpdate(Progress... values)` 方法

5. 启动 AsyncTask

   ```java
   new NetAsyncTask().execute("https://www.baidu.com");
   ```

### 06. 服务基本用法

1. 自定义服务继承 Service

   ```java
   /**
    * 10.3.1 服务入门
    *
    * @since 2017年08月03日
    */
   public class FirstService extends Service {

     /**
      * 在服务被创建时调用
      */
     @Override
     public void onCreate() {
       super.onCreate();
     }

     /**
      * @param intent 意图
      * @return 绑定对象
      */
     @Override
     public IBinder onBind(Intent intent) {
       // TODO: Return the communication channel to the service.
       throw new UnsupportedOperationException("Not yet implemented");
     }

     /**
      * 在每次服务启动的时候调用
      *
      * @param intent  意图
      * @param flags   标识
      * @param startId 启动码
      * @return 整型
      */
     @Override
     public int onStartCommand(Intent intent, int flags, int startId) {
       return super.onStartCommand(intent, flags, startId);
     }

     /**
      * 在服务销毁时调用
      */
     @Override
     public void onDestroy() {
       super.onDestroy();
     }
   }
   ```

2. 功能清单中注册

   ```java
   <service
     android:name=".chapter10.FirstService"
     android:enabled="true"
     android:exported="true" />
   ```

3. 启动与停止

   * 启动

     ```java
     Intent startIntent = new Intent(this, FirstService.class);// 意图指定服务
     startService(startIntent);// 启动服务
     ```

   * 停止

     ```java
     Intent stopIntent = new Intent(this, FirstService.class);// 意图指定服务
     stopService(stopIntent);// 停止服务
     ```

   * 注意

     * 调用 `Context` 类中的 `startService()` 方法和 `stopService()` 方法进行启动和停止
     * 完全由 Activity 进行控制，服务本身有一个 ` stopSelf()` 方法可以停止服务

4. 打印日志

   ```java
   // 点击启动
   E/JustDo23: FirstService --> onCreate()
   E/JustDo23: FirstService --> onStartCommand()
   // 再次点击启动
   E/JustDo23: FirstService --> onStartCommand()
   // 再次点击启动
   E/JustDo23: FirstService --> onStartCommand()
   // 点击停止
   E/JustDo23: FirstService --> onDestroy()
   ```

   * `onCreate()` 方法是在服务**第一次创建**的时候调用
   * `onStartCommande()` 方法则在**每次启动**服务的时候都会调用

### 07. Activity 与 Service 进行通信

1. 使用 Binder

   ```java
   public class FirstService extends Service {

     private DownloadBinder downloadBinder = new DownloadBinder();

     @Override
     public IBinder onBind(Intent intent) {
       LogUtils.e("FirstService --> onBind()");
       return downloadBinder;
     }

     /**
      * 使用 Binder 机制
      *
      * @since 2017年08月04日
      */
     class DownloadBinder extends Binder {

       public void startDownload() {
         LogUtils.e("DownloadBinder --> startDownload()");
       }

       public int getProgress() {
         LogUtils.e("DownloadBinder --> getProgress()");
         return 0;
       }

     }

   }
   ```

2. 获取 Binder

   ```java
   public class FirstServiceActivity extends BaseActivity {

     private FirstService.DownloadBinder downloadBinder;

     /**
      * 服务连接对象
      */
     private ServiceConnection serviceConnection = new ServiceConnection() {

       /**
        * 服务连接回调
        */
       @Override
       public void onServiceConnected(ComponentName name, IBinder service) {
         downloadBinder = (FirstService.DownloadBinder) service;
         downloadBinder.startDownload();
         downloadBinder.getProgress();
       }

       /**
        * 服务断开连接回调
        */
       @Override
       public void onServiceDisconnected(ComponentName name) {

       }
     };

   }
   ```

3. 绑定与解绑

   * 绑定

     ```java
     Intent bindIntent = new Intent(this, FirstService.class);// 意图指定服务
     bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);// 绑定服务[标志位表示活动和服务进行绑定之后自动创建服务]
     ```

   * 解绑

     ```java
     unbindService(serviceConnection);// 解绑服务[停止服务]
     ```

4. 打印日志

   ```java
   // 点击绑定
   E/JustDo23: FirstService --> onCreate()
   E/JustDo23: FirstService --> onBind()
   E/JustDo23: DownloadBinder --> startDownload()
   E/JustDo23: DownloadBinder --> getProgress()
   // 点击解绑绑
   E/JustDo23: FirstService --> onDestroy()
   ```

   * 进行**绑定**服务时传递的**标志位**需要**注意**会**影响生命周期**函数
   * 这里 **`BIND_AUTO_CREATE`** 标志位在绑定时**执行**了 **`onCreate()`** 方法而**不执行**上边 **`onStartCommande()`** 方法
   * 一旦绑定成功，再次进行绑定就不会执行任何方法
   * **解绑**之后活动随即会被**销毁**
   * 任何一个服务在整个应用程序范围内都是通用的，可以和任何一个活动进行绑定，获得相同的 Binder 对象。

### 08. 服务生命周期

1. 生命周期图

   ![ServiceLifecycle](http://osxmqydw4.bkt.clouddn.com/service_lifecycle.png)

2. 生命周期整理

   * 在**任何**位置调用 **`Context`** 的 **`startService()`** 方法，服务**启动**并回调 **`onStartCommand()`** 方法。如果服务之前**没有创建**则 **`onCreate()`** 方法**先**于 `onStartCommand()` 方法。服务启动后便会**一直保持运行状态**，虽然每次调用 `startService()` 方法后都会回调 `onStartCommand()` 方法，但实际上服务**只会存在一个实例**。因此，只需调用**一**次 **`stopService()`** 方法或 **`stopSelf()`** 方法，服务就会**停止**。
   * 调用 **`Context`** 的 **`bindService()`** 方法获取一个服务的**持久连接**，并回调 **`onBind()`** 方法。类似地，如果服务之前**没有创建**则 **`onCreate()`** 方法**先**于 `onbind()` 方法。之后调用方获取 **IBinder** 对象可以用于**通信**。如果再次绑定**不会**回调 `onCreate()` 和 `onbind()` 方法。只要调用方和服务之间的连接**没有断开**，服务就**一直保持运行状态**。
   * 当调用 **`startService()`** 方法启动服务时，则调用 **`stopService()`** 方法来停止并销毁服务。当调用 **`bindService()`** 方法绑定并启动服务时，则调用 **` unbindService()`** 方法来解绑并停止并销毁服务。如果对一个服务既调用了 **`startService()`** 方法又调用了 **`bindService()`** 方法，那如何销毁服务？根据 Android 系统的机制，一个服务只要被启动或者被绑定之后，就会一直处于运行状态，必须要让以上两种条件同时满足才能销毁服务。因此这种情况下需要同时调用 **`stopService()`** 方法和 **` unbindService()`** 方法，**`onDestroy`** 方法才会执行。

### 09. 服务的更多技巧

1. 前台服务

   * 后台服务的系统**优先级**比较低，当系统**内存不足**的情况下，有可能会**回收掉**正在后台运行的服务。
   * **前台服务**和**普通服务**最大的**区别**在于，它会一直有一个**正在运行的图标**在系统的**状态栏**显示，**下拉**可以看到更加详细的信息。

   ```java
   public class ForegroundService extends Service {

     @Override
     public void onCreate() {
       super.onCreate();// 构建一个通知
       Intent intent = new Intent(this, MainActivity.class);
       PendingIntent pendingIntent = PendingIntent.getActivity(this, 23, intent, 0);
       Notification notification = new NotificationCompat.Builder(this)
           .setContentTitle("ForegroundService")
           .setContentText("This the foreground service")
           .setWhen(System.currentTimeMillis())
           .setSmallIcon(R.mipmap.ic_launcher)
           .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_mountain))
           .setContentIntent(pendingIntent)
           .build();
       startForeground(22, notification);// 前台运行服务
     }

   }
   ```

   * 前台服务需要创建一个**通知**
   * 调用 **`startForeground()`** 方法设置前台

2. IntentService

   * 服务中的代码**默认**运行在**主线程**中，因此**不能直接**在服务中执行**耗时操作**而需要**多线程**技术，**线程停止**需要**杀死服务**。为了简单地创建一个**异步的且会自动停止**的服务，Android 中提供了 **IntentService** 类。

   ```java
   public class FirstIntentService extends IntentService {

     public FirstIntentService() {
       super("FirstIntentService");// 父类含参构造。参数用来命名工作线程。
     }

     /**
      * 运行在子线程中，运行结束后销毁服务。每次只处理一个 Intent。
      *
      * @param intent 意图
      */
     @Override
     protected void onHandleIntent(Intent intent) {
       LogUtils.e("FirstIntentService --> onHandleIntent()");
       LogUtils.e("FirstIntentService --> Thread id is " + Thread.currentThread().getId());
       LogUtils.e("FirstIntentService --> Thread name is " + Thread.currentThread().getName());
     }

     @Override
     public void onDestroy() {
       super.onDestroy();
       LogUtils.e("FirstIntentService --> onDestroy()");
     }

   }
   ```

   * 需要注意**构造方法**必须要**调用父类构造**传递当前线程的**名称**
   * 在 `onHandleIntent()` 方法中执行具体的逻辑，运行于子线程。
   * **IntentService** 集开启线程和自动停止服务于一身。

### 10. 最佳实践

1. 完整的下载案例
2. 接口回调
3. 状态封装，每次写入数据是判断状态。
4. 通过一次网络请求获取文件的大小。
5. 通过设置网络请求头实现断点续传，写入文件时先跳过已下载的字节。
6. 注意文件路径的使用，取消则删除文件。
7. 同时调用 `startService()` 和 `bindService()` 方法来启动和绑定服务。这一点至关重要，因为启动服务可以保证服务一直在后台运行，绑定服务则可以让活动与服务进行通信。
8. 活动销毁同时需要进行服务的解绑，不然可能会造成内存泄露。

### 11. 小结

1. 定时关机与开机的功能如何实现。
2. Android 中的线程需要更加深入了解。
3. 主线程的一些运行机制及原理需要了解。
4. 编程思想。
5. 更多实践。





## 第 11 章 位置服务

### 01. 位置服务简介

1. 基于位置的服务 **Location Based Service** 简称 **LBS** 主要的工作**原理**就是利用**无线电通讯网络**和 **GPS** 等**定位方式**来确定出**移动设备**所在的**位置**。
2. 核心是确定**位置**，通常有**两种**方式：一种是通过 **GPS** 定位，一种是通过**网络**定位。
   * **GPS** 定位的**工作原理**是基于**手机内置**的 GPS **硬件**直接和**卫星交**互来获取当前的**经纬度信息**，这种方式**精确度非常高**。**缺点**是**只能**在**室外**使用，室内基本无法接收到卫星的信号。
   * **网络**定位的**工作原理**是根据手机当前网络**附近**的**三个基站**进行**测速**，以此计算出手机和每个**基站**之间的**距离**，再通过**三角定位**确定出一个**大概**的**位置**，这种方式**精确度一般**，优点是**室内外**均可使用。
   * **GPS** 定位**不需要网络**。

### 02. 百度 LBS

1. 获取签名文件的 SHA1 指纹

   * 在 `Android Studio` 右侧工具栏 `Gradle` 选择 `项目名` 选择 `:app` 选择 `Tasks` 选择 `android` 双击 `signingReport` 在控制台输出 SHA1 指纹。

   * 使用命令

     ```shell
     $ keytool -list -v -keystore <签名文件>
     ```

2. 要使用 **GPS** 定位必须要用户在**设置**中**自主**选择**打开**后才可以。

3. 并**不需要**担心**一旦启用** GPS 定位功能后，手机的**电量**就会直线**下滑**，这**只是表明**你已经**同意**让应用程序来对你的手机进行 GPS 定位了，但只有当**定位操作真正开始**的时候，才会影响到手机的**电量**。

### 03. Git 高级用法

1. 分支

   * 分支的主要作用就是在现有代码的基础上开辟一个分叉口，使得代码可以在主干线和分支线上同时进行开发，且相互之间不会影响。

   * 查看分支

     ```shell
     $ git branch
     ```

   * 创建分支

     ```shell
     $ git branch name
     ```

     指定新分支名字

   * 切换分支

     ```shell
     $ git checkout name
     ```

     指定名字进行切换

   * 合并分支

     ```shell
     $ git checkout master
     $ git merge dev
     ```

     首先切换到 master 分支然后将 dev 分支内容合并到 master 分支

   * 删除分支

     ```shell
     $ git branch -D name
     ```

     指定名字进行删除

2. 与远程版本库协作

   * 提交代码

     ```shell
     $ git push origin master
     ```

     其中 `origin` 指定的是远程仓库的 Git 地址而 `master` 指定的是同步到哪一个分支

3. 从远程仓库拉去

   方式一：

   * 拉去远程仓库

     ```shell
     $ git fetch origin master
     ```

     将**远程**仓库代码**同步**到**本地**，但是同步下来的代码并**不会合并到任何分支**，而是会**存放**到一个 `orgin/master` 分支上，这时可以通过 **`diff`** 命令来查看修改的地方

   * 查看修改的地方

     ```shell
     $ git diff origin/master
     ```

     之后使用 **`merge`** 命令将 `orgin/master` 分支合并到主分支

   * 合并

     ```shell
     $ git merge origin/master
     ```

   方式二：

   * pull 命令

     ```shell
     $ git pull origin master
     ```

     相当于 **`fetch`** 和 **`merge`** 两个命令放在一起执行了

### 04. 小结

1. 百度定位更多方法查看官方文档。
2. Git 分支使用很重要。





## 第 13 章 继续进阶

### 01. 创建定时任务

1. 实现方式：
   * Java API **Timer 类** 并不适合用于那些需要长期在后台运行的定时任务。
   * Android API **Alarm 机制**


