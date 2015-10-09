# Logback-log-level-control
##### 作者：ryan.kong(孔政)
##### 日期：2015-09-24

## 目录
> * 说明
> * 使用

### 1、说明
本插件用于在运行时调整Logback[^LaTeX]日志级别

### 2、使用
在项目中引入`Logback-log-level-control.jar`包即可，不需要额外的配置

#### 2.1、接口说明
rootpath/logback 获取所有插件功能说明

rootpath/logback/logbackSetLevel 设置所有日志等级

rootpath/logback/getLoggerAndLevel 获取所有日志和等级信息

rootpath/logback/getLoggerAndLevelByName 根据日志名获取日志信息

rootpath/logback/logbackSetLevelByName 设置指定日志的日志等级

#### 2.2、参数
| 名称          | 说明      |  类型     |
| --------      | -----     | :----:    |
| level         | 日志等级  |   字符串  |
| loggerName    | 日志名称  |   字符串  |

#### 2.3、规范说明
POST和GET都支持
返回内容使用JSON格式


[^LaTeX]:仅仅支持logback，插件在启动时，会检查是否使用logback日志框架，不是的话，不会启动插件
