package sopt.org.report3.service;



import sopt.org.report3.model.DefaultRes;
import sopt.org.report3.model.User;

import java.util.List;

public interface UserService {

    DefaultRes<List<User>> findAll();

    DefaultRes<User> findByUserIdx(final int UserIdx);

    DefaultRes<User> findByUserName(final String name);

    DefaultRes<User> findByUserPart(final String part);

    DefaultRes save(final User user);

    DefaultRes<User> update (final int userIdx, final User user);

    DefaultRes deleteByUserIdx(final int userIdx);

}
