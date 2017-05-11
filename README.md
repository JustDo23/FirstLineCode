# FirstLineCode

> 引言：2017年05月02日开始看郭霖的第一行代码第二版，在此过程中感觉有些地方需要代码实践一番。
>
> 时间：
>
> 作者：JustDo23
>
> 鼓励：Standing on Shoulders of Giants.

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

### 03. 项目目录结构

1. **gradle**
   * 目录下包含了 gradle wrapper 的配置文件，使用此方式不需要提前将 gradle 下载好，恩施自动根据本地缓存情况决定是否需要联网下载 gradle。
2. **.gitignore**
   * 配置 Git 的忽略文件。[https://github.com/GitHub/gitignore](https://github.com/GitHub/gitignore)
3. **gradlew**
   * 在 Linux 系统使用，用于在命令行执行 gradle 命令。
4. **gradlew.bat**
   * 在 Windows 系统使用，用于在命令行执行 gradle 命令。
5. **settings.gradle**
   * 指定项目中所有引入的模块。
6. **proguard-rules.pro**
   * 指定项目代码的混淆规则。

### 04. 详解 build.gradle 文件

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
       targetSdkVersion 25// 目标版本
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

   dependencies {// 依赖库
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

### 05. 小结

1. 关于Dalvik 虚拟机和ART 运行环境相关知识需要学习。
2. **AndroidManifest.xml** 文件中指定的 **package** 和 **build.gradle** 文件中指定的 **applicationId** 区别。
3. 创建 Activity 时候系统指定的**模板**及**自定义模板**的学习使用。
4. 代码混淆相关的知识需要重新学习总结。
5. 打印日志是否会影响性能和效率？





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

   只有<data>标签中指定的内容和 Intent 中携带的 Data 完全一致是，当前活动才能够响应该 Intent。

### 03. Activity 的生命周期

1. 返回栈

   其实 Android 是使用**任务（Task）**来管理活动的，**一个任务**就是**一组**存放在**栈**里的活动的集合，这个栈也被称作返回栈（Back Stack）。栈是一种后进先出的数据接口。

2. 活动状态

   1. 运行状态
   2. 暂停状态
   3. 停止状态
   4. 销毁状态

3. 生命周期函数

   * onCreat() 初始化操作，加载布局，绑定事件等
   * onStart()
   * onResume()
   * onPause()
     * 这个方法在系统准备去启动或者恢复另一个活动的时候调用。我们通常会在这个方法中将一些消耗 CPU 的**资源释放掉**，以及**保存一些关键数据**，但这个方法的执行速度一定要快，不然会影响到新的栈顶活动的使用。
   * onStop()
   * onDestroy() 活动被销毁之前调用
   * onRestart()

4. 活动的生存期

   * 完整生存期
   * 可见生存期
   * 前台生存期

### 04. 活动被回收

1. 场景：应用中有一个活动 A，用户在活动 A 的基础上启动了活动 B，活动 A 就进入了**停止状态**，这个时候由于**系统内存不足**，将活动 A 回收掉了，然后用户按下 **Back 键**返回活动 A，会出现什么情况？会正常显示活动 A 但**不会**执行活动 A 的 onRestart() 方法，**会**执行 onCreate() 方法将活动 A 重新创建一次。

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

### 05. 启动模式

1. 四种启动模式
   * standard
   * singleTop
   * singleTask
   * singleInstance
2. 指定为 singleInstance 模式的活动会启用一个新的返回栈来管理这个活动（其实如果 singTask 模式指定了不同的 **taskAffinity**，也会启动一个新的返回栈）。程序中有一个活动是允许其他程序调用的，则使用此模式。其他三种不能实现是因为每个应用都会有自己的返回栈，同一个活动在不同的返回栈中入栈时必须是创建了新的实例。

### 06. 小结

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
* **RelativeLayout**  相对布局
* 帧布局
* 百分比布局



### 0x. 小结

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

6. LinearLayout 的默认方向是 horizontal







