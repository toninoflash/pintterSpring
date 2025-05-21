/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pintter.businessdomain.follower.services;

import com.pintter.businessdomain.follower.dto.FollowerDto;
import com.pintter.businessdomain.follower.entities.Follower;
import com.pintter.businessdomain.follower.exceptions.BusinessRuleException;
import java.util.List;
import java.util.Optional;


/**
 *
 * @author Pc
 */

public interface FollowerService {
    List<FollowerDto> getAllFollowers();
    Optional<Follower> getFollowerById(Long id);
    FollowerDto createFollower(FollowerDto followerDto);
    FollowerDto updateFollower(Long id, FollowerDto followerDto) throws BusinessRuleException;
    void deleteFollower(Long id);
    List<Follower> findByFollowerId(Long uid);
    List<FollowerDto> findByFollowedId(Long uid);
}
