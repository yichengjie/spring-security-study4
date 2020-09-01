#### remember me功能开发
a. 需要提前建表
```$xslt
create table persistent_logins (
    username varchar(64) not null, 
    series varchar(64) primary key, 
    token varchar(64) not null, 
    last_used timestamp not null
) ;
```
b. 项目中配置数据源
```$xslt
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT
spring.datasource.username=root
spring.datasource.password=root
```
c. WebSecurityConfigurerAdapter配置中添加配置
```$xslt
@Autowired
private DataSource dataSource;
@Autowired
private UserDetailsService userDetailsService;


http.rememberMe()
    .tokenRepository(persistentTokenRepository())
    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
    .userDetailsService(userDetailsService) ;


//记住我功能的token存取器配置
@Bean
public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
    tokenRepository.setDataSource(dataSource);
    //tokenRepository.setCreateTableOnStartup(true);
    return tokenRepository;
}
```