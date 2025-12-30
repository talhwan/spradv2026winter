package com.thc.sprbasic2025fall.service.impl;

import com.thc.sprbasic2025fall.domain.User;
import com.thc.sprbasic2025fall.dto.DefaultDto;
import com.thc.sprbasic2025fall.dto.UserDto;
import com.thc.sprbasic2025fall.mapper.UserMapper;
import com.thc.sprbasic2025fall.repository.UserRepository;
import com.thc.sprbasic2025fall.service.UserService;
import com.thc.sprbasic2025fall.util.TokenFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;
    final UserMapper userMapper;


    @Override
    public UserDto.LoginResDto login(UserDto.LoginReqDto param) {
        User user = userRepository.findByUsernameAndPassword(param.getUsername(), param.getPassword());
        if(user == null){
            throw new RuntimeException("no data");
        }

        String refreshToken = TokenFactory.createRefreshToken(user.getId());
        System.out.println("refreshToken : " + refreshToken);

        /*
        Long userId = TokenFactory.validateToken(refreshToken);
        System.out.println("userId : " + userId);
        */

        return UserDto.LoginResDto.builder().refreshToken(refreshToken).build();
    }

    /**/

    @Override
    public DefaultDto.CreateResDto create(UserDto.CreateReqDto param) {
        User user = userRepository.findByUsername(param.getUsername());
        if(user != null){
            throw new RuntimeException("already exist");
        }
        user = userRepository.findByNick(param.getNick());
        if(user != null){
            throw new RuntimeException("already exist");
        }

        return userRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public void update(UserDto.UpdateReqDto param) {
        User user = userRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        user.update(param);
        userRepository.save(user);
    }

    @Override
    public void delete(UserDto.UpdateReqDto param) {
        update(UserDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    public UserDto.DetailResDto get(DefaultDto.DetailReqDto param) {
//        User user = userRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
//        return UserDto.DetailResDto.builder()
//                .id(user.getId())
//                .deleted(user.getDeleted())
//                .title(user.getTitle())
//                .content(user.getContent())
//                .build();
        UserDto.DetailResDto res = userMapper.detail(param.getId());
        return res;
    }

    @Override
    public UserDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        return get(param);
    }

    public List<UserDto.DetailResDto> addlist(List<UserDto.DetailResDto> list) {
        List<UserDto.DetailResDto> newList = new ArrayList<>();
        for (UserDto.DetailResDto user : list) {
            newList.add(get(DefaultDto.DetailReqDto.builder().id(user.getId()).build()));
        }
        return newList;
    }

    @Override
    public List<UserDto.DetailResDto> list(UserDto.ListReqDto param) {
        List<UserDto.DetailResDto> list = new ArrayList<>();
        List<UserDto.DetailResDto> users = userMapper.list(param);
        return addlist(users);
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(UserDto.PagedListReqDto param) {
        DefaultDto.PagedListResDto res = param.init(userMapper.listCount(param));
        res.setList(addlist(userMapper.pagedList(param)));
        return res;
    }

    @Override
    public List<UserDto.DetailResDto> scrollList(UserDto.ScrollListReqDto param) {
        return addlist(userMapper.scrollList(param));
    }
}
