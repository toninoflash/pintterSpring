/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pintter.businessdomain.follower.repository;

import com.pintter.businessdomain.follower.dto.FollowerDto;
import com.pintter.businessdomain.follower.entities.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Pc
 */
public interface FollowerRepository  extends JpaRepository<Follower, Long> {
    List<Follower> findByFollowerId(Long uid);
    List<FollowerDto> findByFollowedId(Long uid);
}
