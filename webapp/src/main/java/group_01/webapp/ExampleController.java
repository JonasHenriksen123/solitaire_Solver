package group_01.webapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ExampleController {

    @RequestMapping("/")
    @GetMapping
    public String index()
    {
        return "This is an example request";
    }

    @PostMapping
    public void post(String postPackage)
    {
        //do something
        System.out.println(postPackage);
    }
}
