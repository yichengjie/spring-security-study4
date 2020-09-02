### 整合spring-social笔记
##### 配置配置SpringSocialConfigurer，并在spring-security中应用该配置,如果没有特殊配置
> 继承SpringSocialConfigurer自定义拦截地址
```$xslt
public class MiniSpringSocialConfigurer extends SpringSocialConfigurer {
     private String filterProcessesUrl;
     // 留给用户自定义对filter进行个性化修改
     private SocialAuthenticationFilterPostProcessor postProcessor;
 
     public MiniSpringSocialConfigurer(String filterProcessesUrl) {
         this.filterProcessesUrl = filterProcessesUrl;
     }
 
     @Override
     protected <T> T postProcess(T object) {
         SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
         filter.setFilterProcessesUrl(filterProcessesUrl);
         // 自定义修改filter
         if (postProcessor != null) {
             postProcessor.process(filter);
         }
         return (T) filter;
     }
 }
```
> 实例化SpringSocialConfigurer实例供springSecurity使用
```$xslt
@Bean
 public SpringSocialConfigurer miniSocialSecurityConfigurer(){
    // 自定义spring-social登录的地址，SocialAuthenticationFilter中默认为‘/auth’
    // 这里自定义为‘/qqLogin’
    String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
    MiniSpringSocialConfigurer socialConfigurer = new MiniSpringSocialConfigurer(filterProcessesUrl);
    return  socialConfigurer;
 }
```
> spring-security中应用SpringSocialConfigurer配置
```$xslt
 // 社交登录
 @Autowired(required = false)
 private SpringSocialConfigurer miniSocialSecurityConfigurer ;
 
 @Override
 protected void configure(HttpSecurity http) throws Exception {
     //表单登录相关配置
     formAuthenticationConfig.configure(http);
     // 社交登录
     http.apply(miniSocialSecurityConfigurer) ;
 }
```

 
 

 
 
   

 
 
```