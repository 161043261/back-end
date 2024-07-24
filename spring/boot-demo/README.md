# boot-demo

### PeopleController

[people](http://127.0.1:8080/people?name=tom&age=22)

[simple](http://127.0.1:8080/simple?name=tom&age=22)

[orm](http://127.0.0.1:8080/orm?name=tom&age=22)

[complex](http://127.0.0.1:8080/complex?name=tom&age=22&address.province=jiangsu&address.city=nanjing)

[checkbox](http://127.0.0.1:8080/checkbox?like=sing&like=dance&like=rap&like=basketball)

[checkboxList](http://127.0.0.1:8080/checkbox/list?city=nanjing&city=suzhou&city=wuxi)

[datetime](http://127.0.0.1:8080/date?date=2002-02-28)

[path](http://127.0.0.1:8080/path/tom/22)

[json](http://127.0.0.1:8080/json)

```json
{
  "name": "tom",
  "age": 22,
  "address": {
    "province": "jiangsu",
    "city": "nanjing"
  }
}
```

**Wrapped by Result**
[peopleResult](http://127.0.1:8080/people/result?name=tom&age=22)

[complexResult](http://127.0.0.1:8080/complex/result?name=tom&age=22&address.province=jiangsu&address.city=nanjing)

[checkboxListResult](http://127.0.0.1:8080/checkbox/list/result?city=nanjing&city=suzhou&city=wuxi)
