package com.thc.sprbasic2025fall.service.impl;

import com.thc.sprbasic2025fall.domain.RefreshToken;
import com.thc.sprbasic2025fall.domain.User;
import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.UserDto;
import com.thc.sprbasic2025fall.mapper.UserMapper;
import com.thc.sprbasic2025fall.repository.RefreshTokenRepository;
import com.thc.sprbasic2025fall.repository.UserRepository;
import com.thc.sprbasic2025fall.service.UserService;
import com.thc.sprbasic2025fall.util.TokenFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final UserMapper userMapper;
    final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public DefaultDto.CreateResDto signup(UserDto.CreateReqDto param, Long reqUserId) {
        return create(param, reqUserId);
    }

    /**/

    @Override
    public DefaultDto.CreateResDto create(UserDto.CreateReqDto param, Long reqUserId) {
        User user = userRepository.findByUsername(param.getUsername());
        if(user != null){
            throw new RuntimeException("already exist");
        }
        user = userRepository.findByNick(param.getNick());
        if(user != null){
            throw new RuntimeException("already exist");
        }
        //스프링 시큐리티 에서 사용하는 알고리즘 사용해야만 합니다!
        param.setPassword(bCryptPasswordEncoder.encode(param.getPassword()));
        User newUser = userRepository.save(param.toEntity());

        return newUser.toCreateResDto();
    }

    @Override
    public void update(UserDto.UpdateReqDto param, Long reqUserId) {
        User user = userRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        user.update(param);
        userRepository.save(user);
    }

    @Override
    public void delete(UserDto.UpdateReqDto param, Long reqUserId) {
        update(UserDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build(), reqUserId);
    }

    public UserDto.DetailResDto get(DefaultDto.DetailReqDto param, Long reqUserId) {
        UserDto.DetailResDto res = userMapper.detail(param.getId());
        return res;
    }

    @Override
    public UserDto.DetailResDto detail(DefaultDto.DetailReqDto param, Long reqUserId) {
        return get(param, reqUserId);
    }

    public List<UserDto.DetailResDto> addlist(List<UserDto.DetailResDto> list, Long reqUserId) {
        List<UserDto.DetailResDto> newList = new ArrayList<>();
        for (UserDto.DetailResDto user : list) {
            newList.add(get(DefaultDto.DetailReqDto.builder().id(user.getId()).build(), reqUserId));
        }
        return newList;
    }

    @Override
    public List<UserDto.DetailResDto> list(UserDto.ListReqDto param, Long reqUserId) {
        List<UserDto.DetailResDto> users = userMapper.list(param);
        return addlist(users, reqUserId);
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(UserDto.PagedListReqDto param, Long reqUserId) {
        DefaultDto.PagedListResDto res = param.init(userMapper.listCount(param));
        res.setList(addlist(userMapper.pagedList(param), reqUserId));
        return res;
    }

    @Override
    public List<UserDto.DetailResDto> scrollList(UserDto.ScrollListReqDto param, Long reqUserId) {
        return addlist(userMapper.scrollList(param), reqUserId);
    }
}
