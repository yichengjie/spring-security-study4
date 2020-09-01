#### 向spring-security过滤器链中添加过滤器(以验证码为例)
> 编写过滤器实现
```$xslt
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
    //验证码校验失败处理器
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    //系统配置信息
    @Autowired
    private SecurityProperties securityProperties;
    //系统中的校验码处理器
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;
    //存放所有需要校验验证码的url
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();
    //验证请求url与配置的url是否匹配的工具类
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    //初始化要拦截的url配置信息
    @Override
    public void afterPropertiesSet() throws ServletException {
        urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);
        urlMap.put(SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);
    }

    //将系统中配置的需要校验验证码的URL根据校验的类型放入map
    protected void addUrlToMap(String urlString, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : urls) {
                urlMap.put(url, type);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        ValidateCodeType type = getValidateCodeType(request);
        if (type != null) {
            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                        .validate(new ServletWebRequest(request, response));
                logger.info("验证码校验通过");
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    //获取校验码的类型，如果当前请求不需要校验，则返回null
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (pathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }
}
```
> 添加验证码配置类
```$xslt
@Component("validateCodeSecurityConfig")
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }
}
```
> 将配置类添加到spring-security中
```$xslt
    // 验证码相关得配置
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单登录相关配置
        formAuthenticationConfig.configure(http);
        // 验证码相关配置
        http.apply(validateCodeSecurityConfig) ;
    }
```
