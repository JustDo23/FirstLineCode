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

1. **`SharedPreferences`** 使用**键值对**的方式存储数据。支持**`多种`**不同的**数据类型**存储。

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

4. **注意：**SQL 语句中相应位置的空格及分割符很重要

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
   * **注意：**将某个字段设置为默认值需要调用 **`setToDefault(String fieldName)`** 参数字段名

     ```java
     Book book = new Book();// 实例化实体类
     book.setPages("776");// 更新数据
     book.setToDefault("price");// 设置默认值
     book.updateAll("name = ? and pages = ?", "老人与海", "76");
     ```

7. 删除数据

   * 通过调用**已存储的对象 **的 **`delete()`** 方法来删除
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
     * **`order()`** 方法用于指定查询结果的排序方式 **`desc`**表示降序 **`asc`**表示升序
     * **`limit()`** 方法用于指定查询结果的数量
     * **`offset()`** 方法用于指定查询结果偏移量

### 07. 小结

1. 文件存储核心是 `Java` 中的 **`I/O 流操作`** 因此需要进行复习练习。
2. 注意 `SharedPreferences` 提交 `apply()` 方法与 `commit()` 方法。
3. 数据库的原生 API 使用及一些第三方库的使用。







## 第 13 章 继续进阶

### 01. 创建定时任务

1. 实现方式：
   * Java API **Timer 类** 并不适合用于那些需要长期在后台运行的定时任务。
   * Android API **Alarm 机制**


