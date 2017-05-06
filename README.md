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





### 05. 小结

1. 关于Dalvik 虚拟机和ART 运行环境相关知识需要学习。
2. **AndroidManifest.xml** 文件中指定的 **package** 和 **build.gradle** 文件中指定的 **applicationId** 区别。
3. 创建 Activity 时候系统指定的**模板**及**自定义模板**的学习使用。
4. 代码混淆相关的知识需要重新学习总结。
5. 打印日志是否会影响性能和效率？

## 第 2 章 Activity



### 0x. 小结

1. 关于向下兼容的 **AppCompatActivity** 需要学习。

