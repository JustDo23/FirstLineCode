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



### 0x. 小结

1. 关于向下兼容的 **AppCompatActivity** 需要学习。

