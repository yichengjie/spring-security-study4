### 整合oauth2
1. 添加maven依赖
```$xslt
<dependency>
    <groupId>org.springframework.security.oauth.boot</groupId>
    <artifactId>spring-security-oauth2-autoconfigure</artifactId>
    <version>2.1.16.RELEASE</version>
</dependency>
```
2. 编写UserDetailService的实现类，且角色必须包含ROLE_USER,否则无法访问
```$xslt
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户名{}",username);
        //根据用户名查找用户信息
        return buildUser(username);
    }
    private User buildUser(String userId) {
        // 根据用户名查找用户信息
        //根据查找到的用户信息判断用户是否被冻结
        String roles = "ROLE_USER" ;
        if ("admin".equals(userId)){
            roles = "ROLE_USER,ROLE_ADMIN" ;
        }
        return new User(userId, "123",
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList(roles));
    }
}
```
3. 编写spring-security的配置类，不然访问是会报错提示
```$xslt
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
            .csrf().disable();
    }
    
    // 密码模式时使用AuthenticationManager
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }
}
```
4. 编写授权服务器配置类
```$xslt
@Configuration
@EnableAuthorizationServer
// 必须继承至AuthorizationServerConfigurerAdapter
public class MiniAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    //客户端配置（必须得配置，不然访问会报错，1.x版本可不配置，会生成一个随机得输出到控制台上）
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        builder.withClient("clientId")
                .secret("clientSecret")
                .redirectUris("http://www.baidu.com")
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(2592000)
                .scopes("all");
    }
    
    // 如果想使用密码模式必须得配置这里
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}
```
5. 资源服务器配置
```$xslt
@Configuration
@EnableResourceServer
public class MiniResourceServerConfig extends ResourceServerConfigurerAdapter {

}
```

