# TextCard - 文字卡片桌面小组件

一个极简的 Android 桌面文字小组件应用，支持自定义文字内容、字体、字号、颜色、圆角和透明度。

## 功能特性

- 2×1 桌面小组件，点击后打开编辑页面
- 自定义文字内容
- 可调字号（12sp - 48sp）
- 可调圆角半径（0dp - 32dp）
- 可调背景透明度（10% - 100%）
- 支持默认字体和站酷快乐体
- 动态配色（DynamicTonalPalette）
- 夜间模式跟随系统
- 完全离线，零权限，无广告
- 本地持久化存储（DataStore）

## 技术栈

- **语言**: Kotlin 100%
- **UI 框架**: Jetpack Compose
- **小组件框架**: Glance AppWidget
- **数据存储**: DataStore Preferences
- **最低 SDK**: 26 (Android 8.0)
- **目标 SDK**: 35 (Android 15)
- **测试框架**: JUnit 5

## 项目结构

```
TextCard/
├── .github/
│   └── workflows/
│       └── ci.yml
├── app/
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/com/example/textcard/
│       │   │   ├── MainActivity.kt
│       │   │   ├── data/
│       │   │   │   ├── TextCardData.kt
│       │   │   │   ├── TextCardPreferences.kt
│       │   │   │   └── TextCardRepository.kt
│       │   │   ├── ui/
│       │   │   │   ├── EditScreen.kt
│       │   │   │   └── theme/
│       │   │   │       ├── Color.kt
│       │   │   │       ├── Theme.kt
│       │   │   │       └── Type.kt
│       │   │   └── widget/
│       │   │       ├── TextCardWidget.kt
│       │   │       └── TextCardWidgetReceiver.kt
│       │   └── res/
│       │       ├── drawable/
│       │       ├── layout/
│       │       ├── mipmap-*/
│       │       ├── values/
│       │       └── xml/
│       └── test/
│           └── java/com/example/textcard/
│               ├── TextCardPreferencesTest.kt
│               └── TextCardRepositoryTest.kt
├── build.gradle.kts
├── gradle.properties
├── gradlew
└── README.md
```

## 快速开始

### 环境要求

- Android Studio Ladybug (2024.2.1) 或更高版本
- JDK 17
- Android SDK 35

### 构建项目

1. 克隆项目到本地
2. 使用 Android Studio 打开项目
3. 等待 Gradle 同步完成
4. 连接 Android 设备或启动模拟器
5. 点击 Run 按钮或使用以下命令：

```bash
# 构建 Debug APK
./gradlew assembleDebug

# 安装到设备
adb install -r app/build/outputs/apk/debug/app-debug.apk

# 启动应用
adb shell am start -n com.example.textcard/.MainActivity

# 添加小组件到桌面
adb shell appwidget grantbind --package com.example.textcard
```

### 运行测试

```bash
# 运行单元测试
./gradlew test

# 运行所有测试（包括设备测试）
./gradlew connectedAndroidTest
```

## 使用说明

1. 长按桌面空白处，选择"小组件"
2. 找到"文字卡片"应用
3. 选择 2×1 尺寸的小组件添加到桌面
4. 点击小组件打开编辑页面
5. 修改文字、字号、颜色、圆角、透明度等设置
6. 点击"保存"按钮应用更改

## 开发指南

### 添加新功能

1. 在 `data/TextCardData.kt` 中添加新的数据字段
2. 在 `data/TextCardPreferences.kt` 中添加对应的键和存储逻辑
3. 在 `ui/EditScreen.kt` 中添加 UI 控件
4. 在 `widget/TextCardWidget.kt` 中应用新配置

### 样式定制

- 主题颜色：修改 `ui/theme/Color.kt`
- 字体样式：修改 `ui/theme/Type.kt`
- 主题配置：修改 `ui/theme/Theme.kt`

## 许可证

本项目采用 MIT 许可证。

## 贡献

欢迎提交 Issue 和 Pull Request！

## 联系方式

如有问题或建议，请通过 GitHub Issues 联系。