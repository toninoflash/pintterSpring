/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pintter.businessdomain.follower.services;


import com.pintter.businessdomain.follower.dto.FollowerDto;
import com.pintter.businessdomain.follower.entities.Follower;
import com.pintter.businessdomain.follower.exceptions.BusinessRuleException;
import com.pintter.businessdomain.follower.mapper.FollowerMapper;
import com.pintter.businessdomain.follower.repository.FollowerRepository;
import com.pintter.businessdomain.follower.transactions.BusinessTransactions;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author Pc
 */
@Service
@Slf4j
public class FollowerServiceImpl implements FollowerService {

    @Autowired
    private FollowerRepository followerRepository;
    @Autowired
    private FollowerMapper followerMapper;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private BusinessTransactions businessTransactions;
    // Add any required dependencies here (e.g., repositories, mappers)

    @Override
    public List<FollowerDto> getAllFollowers() {
        List<FollowerDto> listFollowerDto = followerMapper.toDtoList(followerRepository.findAll());

        return listFollowerDto;
    }

    @Override
    public Optional<Follower> getFollowerById(Long id) {
        Optional<Follower> opt = followerRepository.findById(id);
        return opt;
    }

    @Override
    public List<Follower> findByFollowerId(Long uid) {
        List<Follower> followerList = followerRepository.findByFollowerId(uid);
        return followerList;
    }

    @Override
    public List<FollowerDto> findByFollowedId(Long uid) {
        List<FollowerDto> followerList = followerRepository.findByFollowedId(uid);
        return followerList;
    }



    @Override
    public FollowerDto createFollower(FollowerDto followerDto) {
        

        Follower follower = followerMapper.toEntity(followerDto);
        follower.setCreateAt(LocalDateTime.now());
        follower = followerRepository.save(follower);
        return followerMapper.toDto(follower);
    }

    @Override
    public FollowerDto updateFollower(Long id, FollowerDto followerDto) throws BusinessRuleException {
        // Implementation here
        Optional<Follower> opt = followerRepository.findById(id);
        log.info("resRole:::::::" + opt.get());
        Follower resFollower = followerMapper.toOptional(opt);
        log.info("resRole:::::::" + resFollower);

        if (resFollower != null) {
            resFollower.setId(id);
            

        } else {
            BusinessRuleException businessRuleException = new BusinessRuleException("0002", "Error validación. Transacion no localizada. ", HttpStatus.PRECONDITION_FAILED);
            throw businessRuleException;
        }
        log.info("resRole:::::::" + resFollower);
        FollowerDto save = followerMapper.toDto(followerRepository.save(resFollower));
        return save;
    }

    @Override
    public void deleteFollower(Long id) {
        // Implementation here
    }


}
