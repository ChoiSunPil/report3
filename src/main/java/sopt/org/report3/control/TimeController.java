package sopt.org.report3.control;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TimeController {
    @GetMapping("")
    public String getNowTime() {
        return new Date().toString();
    }
}
