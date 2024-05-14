package com.sparta.springmvc.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello/request")
public class RequestController {

  @GetMapping("/form/html")
  public String helloForm() {
    return "hello-request-form";
  }

  // @PathVariable 방식
  // [Request sample]
  // GET http://localhost:8080/hello/request/star/Robbie/age/95
  @GetMapping("/star/{name}/age/{age}")
  @ResponseBody
  public String helloRequestPath(@PathVariable String name, @PathVariable int age)
  {
    return String.format("Hello, @PathVariable.<br> name = %s, age = %d", name, age);
  }

  // @RequestParam 방식
  // [Request sample]
  // GET http://localhost:8080/hello/request/form/param?name=Robbie&age=95 -> 쿼리스트링
  @GetMapping("/form/param")
  @ResponseBody
  public String helloGetRequestParam(@RequestParam(required = false) String name, int age) {
    return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
  }

  // [Request sample]
  // POST http://localhost:8080/hello/request/form/param
  // Header
  //  Content type: application/x-www-form-urlencoded
  // Body -> POST는 Body를 가지고 있음
  //  name=Robbie&age=95
  @PostMapping("/form/param")
  @ResponseBody
  public String helloPostRequestParam(@RequestParam String name, @RequestParam int age) {
    return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
  }

  // @ModelAttribute 방식 -> 생략 가능
  // 파라미터 -> Simple value Type => RequestParam
  //        -> 다른 타입 -> ModelAttribute

  // [Request sample]
  // POST http://localhost:8080/hello/request/form/model
  // Header
  //  Content type: application/x-www-form-urlencoded
  // Body
  //  name=Robbie&age=95
  @PostMapping("/form/model")
  @ResponseBody
  public String helloRequestBodyForm(@ModelAttribute Star star) {
    return String.format("Hello, @ModelAttribute.<br> (name = %s, age = %d) ", star.name, star.age);
  }

  // [Request sample]
  // GET http://localhost:8080/hello/request/form/param/model?name=Robbie&age=95
  @GetMapping("/form/param/model")
  @ResponseBody
  public String helloRequestParam(Star star) {
    return String.format("Hello, @ModelAttribute.<br> (name = %s, age = %d) ", star.name, star.age);
  }

  // [Request sample]
  // POST http://localhost:8080/hello/request/form/json
  // Header
  //  Content type: application/json
  // Body
  //  {"name":"Robbie","age":"95"} -> JSON 형태 데이터를 Body에 보내려면 @RequestBody 필요
  @PostMapping("/form/json")
  @ResponseBody
  public String helloPostRequestJson(@RequestBody Star star) {
    return String.format("Hello, @RequestBody.<br> (name = %s, age = %d) ", star.name, star.age);
  }
}
