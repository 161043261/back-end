# boot-demo

### DemoController

[emp](http://127.0.1:8080/emp?name=tom&age=22)

[str](http://127.0.1:8080/str?name=tom&age=22)

[orm](http://127.0.0.1:8080/orm?name=tom&age=22)

[obj](http://127.0.0.1:8080/obj?name=tom&age=22&address.province=jiangsu&address.city=nanjing)

[box](http://127.0.0.1:8080/box?like=sing&like=dance&like=rap&like=basketball)

[boxList](http://127.0.0.1:8080/box/list?city=nanjing&city=suzhou&city=wuxi)

[date](http://127.0.0.1:8080/date?date=2002-02-28)

[path](http://127.0.0.1:8080/path/tom/22)

[json](http://127.0.0.1:8080/json) use postman

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

[empRes](http://127.0.1:8080/emp/res?name=tom&age=22)

[objRes](http://127.0.0.1:8080/obj/res?name=tom&age=22&address.province=jiangsu&address.city=nanjing)

[boxListRes](http://127.0.0.1:8080/box/list/res?city=nanjing&city=suzhou&city=wuxi)
