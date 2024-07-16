# A SpringBoot Demo

### Annotations

```shell
@Configuration

@SpringBootConfiguration

@Bean

@Scope

@Controller

@Service

@Repository

@Component

@Import

@ComponentScan
# 1. SpringBoot scans the package "com.bronya.demo" where the main application is located and the subpackage "Controller" of this package, etc.
# 2. The class annotated with "@SpringBootApplication" is the main application class

@ConditionalOnXxx
# - @ConditionalOnClass
# - @ConditionalOnMissingClass
# - @ConditionalOnBean
# - @ConditionalOnMissingBean
# - @ConditionalOnResource
# - @ConditionalOnProperty

@ConfigurationProperties

@EnableConfigurationProperties
```

### ConditionalOnXxx Test

1. if the com.mysql.cj.jdbc.Driver component exists,  
then add a Cat bean to the IOC container (√),  
else add a Dog bean to the IOC container (×).

2. if the component dog exists,  
then add a User bean named tomcat to the IOC container (×),  
else add a User bean named nginx to the IOC container (√).

### whereis WSL

```tex
C:/Windows/System32/wsl.exe --distribution Ubuntu-24.04 --exec /usr/bin/zsh
```