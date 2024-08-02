# proj-demo

## JWT, JSON Web Token

```java
public void testJwt() {
    // generate secretKey
    SecretKey secretKey = Jwts.SIG.HS256.key().build();
    // base64 encode
    String encoded = Encoders.BASE64.encode(secretKey.getEncoded());
    // base64 decode
    SecretKey decoded = Keys.hmacShaKeyFor(Decoders.BASE64.decode(encoded));
    System.out.println(secretKey.equals(decoded));

    // generate payload
    Map<String, Object> claimsMap = new HashMap<>();
    claimsMap.put("id", 1);
    claimsMap.put("name", "James Gosling");

    String jwtString = Jwts.builder() // get a JwtBuilder
            .header().keyId("bronya").and().claims(claimsMap) // payload
            .signWith(secretKey) // signature
            .expiration(new Date(System.currentTimeMillis() + 60_000)) // expiration
            .compact();

    System.out.println(jwtString);

    // parse jwtString
    Claims claims = Jwts.parser() // get a JwtParserBuilder
            .verifyWith(decoded).build() // get a thread-safe JwtParser
            .parseSignedClaims(jwtString).getPayload(); // parse jwtString
    System.out.println(claims);
}
```

## 1 httpServletRequest corresponds to 1 thread

```java
public void testThreadLocal() {
    ThreadLocal<String> threadLocal = new ThreadLocal<>();
    new Thread(() -> {
        threadLocal.set("ValueA");
        System.out.println("K = " + Thread.currentThread().getName() + ", V = " + threadLocal.get());
    }, "ThreadA").start(); // K = ThreadA, V = ValueA
    new Thread(() -> {
        threadLocal.set("ValueB");
        System.out.println("K = " + Thread.currentThread().getName() + ", V = " + threadLocal.get());
    }, "ThreadB").start(); // K = ThreadB, V = ValueB
}
```

## MyBatis Plus Pagination

### configuration

```java

@Configuration
public class MyBatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
```

### controller

```java

@RestController
@RequestMapping("/test")
public class DaoController {

    private DaoService daoService;

    @Autowired
    public void setDaoMapper(DaoService daoService) {
        this.daoService = daoService;
    }


    @GetMapping
    public Result<List<Dao>> selectDaoList(Integer pageNum, Integer pageSize,
                                           @RequestParam(required = false) Integer propA,
                                           @RequestParam(required = false) String propB) {
        Page<Dao> daoPage = daoService.selectDaoList(pageNum, pageSize, propA, propB);
        return Result.ok(/* data */daoPage.getRecords());
    }
}

```

### service

```java
public interface DaoService {
    int insertDao(Dao dao);

    Page<Dao> selectDaoList(Integer pageNum, Integer pageSize, Integer propA, String propB);
}

```

DAO, Data Access Object, DAO is equivalent to pojo.

```java

@Service
public class DaoServiceImpl extends ServiceImpl<DaoMapper, Dao> implements DaoService {

    private DaoMapper daoMapper;

    @Autowired
    public void setDaoMapper(DaoMapper daoMapper) {
        this.daoMapper = daoMapper;
    }

    @Override
    public Page<Dao> selectDaoList(Integer pageNum, Integer pageSize, Integer propA, String propB) {
        Page<Dao> page = Page.of(pageNum, pageSize);
        page.addOrder(OrderItem.desc("create_time"));
        QueryWrapper<Dao> queryWrapper = new QueryWrapper<>();
        if (propA != null) {
            queryWrapper.eq("columnA", propA);
        }
        if (propB != null && !propB.isEmpty()) {
            queryWrapper.eq("columnB", propB);
        }
        return daoMapper.selectPage(page, queryWrapper);
    }
}

```

### Mapper

```java

@Mapper
public interface DaoMapper extends BaseMapper<Dao> {
}
```