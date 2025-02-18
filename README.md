# NewManagerBySpringboot
* 将之前的javaweb项目换成了Springboot
* 搞半天发不了PUT请求，原来是没有在配置文件开启`spring.mvc.hiddenmethod.filter.enabled=true`
* 重写了拦截规则，也把之前SSM框架的项目也改了，包括拦截规则和映射文件的参数类型
* 分支的添加了可以使用mysql或者其他数据库，只需要在配置文件根据键值对的方式进行添加就行了，不需要改动java文件
