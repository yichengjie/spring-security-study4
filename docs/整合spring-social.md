### 整合spring-social笔记
####配置并开启spring-social使用@EnableSocial，并配置SocialConfigurerAdapter,  
> 可配置多个SocialConfigurerAdapter，通常一个通用的SocialConfigurerAdapter配置，外加多个社交登录特殊配置
```$xslt
// 通用SocialConfigurerAdapter配置
@EnableSocial
@Configuration
public class SocialConfig extends SocialConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(
            ConnectionFactoryLocator locator) {
        JdbcUsersConnectionRepository repository =
                new JdbcUsersConnectionRepository(dataSource, locator, Encryptors.noOpText());
        //repository.setTablePrefix("mini_");
        if(connectionSignUp != null) {
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }
    
    @Override
    public UserIdSource getUserIdSource() {
        return new CurrentUserHolder();
    }
}

// qq的SocialConfigurerAdapter配置
@Configuration
@ConditionalOnProperty(prefix = "mini.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialConfigurerAdapter  {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void addConnectionFactories(
            ConnectionFactoryConfigurer configurer, Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }
    
    // 创建ConnectionFactory
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }
}
```
#### 配置配置SpringSocialConfigurer，并在spring-security中应用该配置,如果没有特殊配置
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
> 实例化SpringSocialConfigurer,并在SpringSocialConfigurer中使用
```$xslt
 @Bean
 public SpringSocialConfigurer miniSocialSecurityConfigurer(){
    // 自定义spring-social登录的地址，SocialAuthenticationFilter中默认为‘/auth’
    // 这里自定义为‘/qqLogin’
    String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
    MiniSpringSocialConfigurer socialConfigurer = new MiniSpringSocialConfigurer(filterProcessesUrl);
    return  socialConfigurer;
 }
 
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