# NewManagerBySpringboot
* 将之前的javaweb项目换成了Springboot
* 搞半天发不了PUT请求，原来是没有在配置文件开启`spring.mvc.hiddenmethod.filter.enabled=true`
* 重写了拦截规则，也把之前SSM框架的项目也改了，包括拦截规则和映射文件的参数类型
