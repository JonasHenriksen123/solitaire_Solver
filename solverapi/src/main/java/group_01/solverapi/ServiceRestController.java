package group_01.solverapi;

import group_01.solverapi.control.Controller;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class ServiceRestController {
    private Controller controller;

    public ServiceRestController(Controller controller) {
        this.controller = controller;
    }

    @RequestMapping("solver")
    @GetMapping
    public String greater() {
        return "Welcome from solitaire solver";
    }


}
