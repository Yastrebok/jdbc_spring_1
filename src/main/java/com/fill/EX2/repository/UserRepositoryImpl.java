package com.fill.EX2.repository;

import com.fill.EX2.entity.User;
import com.fill.EX2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        String sql = "select user_id as id ,user_name as username,email,age from user order by user_id";
        return jdbcTemplate.query(sql,new UserMapper());
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO user (user_name,email,age) values (?,?,?)";
        jdbcTemplate.update(sql, user.getUsername(),user.getEmail(), user.getAge());
    }

    @Override
    public User getById(int id) {
        String sql = "SELECT user_id as id ,user_name as username,email,age FROM user WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(),id);
    }

    @Override
    public void deleteById(int id) {
        String sql =  "DELETE FROM user WHERE user_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user SET  user_name = ?, email = ?, age = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail(),user.getAge(),user.getId());

    }
}
