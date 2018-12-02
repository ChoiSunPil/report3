package sopt.org.report3.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sopt.org.report3.model.DefaultRes;
import sopt.org.report3.model.User;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private static final List<User> userList = new LinkedList<>();

    @Override
    public DefaultRes<List<User>> findAll() {


        if(userList.isEmpty())return DefaultRes.res(HttpStatus.NOT_FOUND.value(),"리스트가 비었습니다.");

        return DefaultRes.res(HttpStatus.OK.value(),"리스트 조회 성공",userList);
    }



    //회원 고유 ID로 회원 조회
    @Override
    public DefaultRes<User> findByUserIdx(int userIdx) {
        for(User u : userList)
        {
            if(u.getUserIdx() == userIdx)
                return DefaultRes.res(HttpStatus.OK.value(),"회원 조회 성공",u);
        }



        return DefaultRes.res(HttpStatus.NOT_FOUND.value(),"회원 정보가 없습니다.");
    }

    @Override
    public DefaultRes<User> findByUserName(String name) {

        for(User u : userList)
        {
            if(u.getName().equals(name))
                return DefaultRes.res(HttpStatus.OK.value(),"회원 조회 성공",u);
        }



        return DefaultRes.res(HttpStatus.NOT_FOUND.value(),"회원 정보가 없습니다.");
    }

    @Override
    public DefaultRes<User> findByUserPart(String part) {

        for(User u : userList)
        {
            if(u.getPart().equals(part))
                return DefaultRes.res(HttpStatus.OK.value(),"회원 조회 성공",u);
        }



        return DefaultRes.res(HttpStatus.NOT_FOUND.value(),"회원 정보가 없습니다.");
    }

    //회원 저장
    @Override
    public DefaultRes save(User user) {

        userList.add(user);
        return DefaultRes.res(HttpStatus.OK.value(),"회원 정보 저장 완료.");
    }
    //회원 정보 수정
    @Override
    public DefaultRes<User> update(int userIdx, User user) {
        for(User u : userList)
        {

            if(u.getUserIdx() == userIdx) {
                u.setName(user.getName());
                u.setPart(user.getPart());
                return DefaultRes.res(HttpStatus.OK.value(), "회원 정보 수정 성공");
            }
        }


        return DefaultRes.res(HttpStatus.NOT_FOUND.value(),"회원 정보가 없습니다.");
    }
    //회원 고유 ID로 회원 삭제
    @Override
    public DefaultRes deleteByUserIdx(int userIdx) {

        for(User u : userList)
        {

            if(u.getUserIdx() == userIdx) {
                userList.remove(u);
                return DefaultRes.res(HttpStatus.OK.value(), "회원 삭제 성공");
            }
        }


        return DefaultRes.res(HttpStatus.NOT_FOUND.value(),"회원 정보가 없습니다.");
    }
}