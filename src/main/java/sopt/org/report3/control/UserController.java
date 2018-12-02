package sopt.org.report3.control;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.org.report3.model.DefaultRes;
import sopt.org.report3.model.User;
import sopt.org.report3.service.UserService;

import java.util.Date;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    /**
     * 생성자가 1개일 경우 @Autowired 생량 가능
     *
     * @param userService user 서버스
     */

    public UserController(final UserService userService){
        this.userService = userService;
    }


    @GetMapping("")
    public ResponseEntity getAllUsers(@RequestParam(value = "name", required = false) final Optional<String> name,
    @RequestParam(value = "part", required = false) final Optional<String> part) { //HTTP StatusConde를 함께 전송해주기 위해서 사용
        if (!name.isPresent() && !part.isPresent()) {
            log.info("get All users");
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        }
        else if(name.isPresent() && !part.isPresent())
        {
            log.info("get by name");
            return new ResponseEntity<>(userService.findByUserName(name.toString()),HttpStatus.OK);
        }
        else if(!name.isPresent() && part.isPresent())
        {
            log.info("get by part");
            return new ResponseEntity<>(userService.findByUserPart(part.toString()),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{userIdx}")
    public ResponseEntity getByUserIdx(@PathVariable(value = "userIdx") final int userIdx)
    {
        log.info("get user info by userIdx");
        return new ResponseEntity<>(userService.findByUserIdx(userIdx),HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity saveUser(@RequestBody final User user) {

        log.info("save user ");
        return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
    }

    @PutMapping("/{userIdx}")
    public ResponseEntity putUser(@PathVariable(value = "userIdx") final int userIdx,@RequestBody final User user)
    {
        log.info("update user info");

        return new ResponseEntity<>(userService.update(userIdx,user),HttpStatus.OK);
    }


    @DeleteMapping("/{userIdx}")
    public ResponseEntity deleteUser(@PathVariable(value = "userIdx") final int userIdx)
    {
        log.info("delete user");

        return new ResponseEntity<>(userService.deleteByUserIdx(userIdx),HttpStatus.OK);
    }





}