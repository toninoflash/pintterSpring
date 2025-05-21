/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pintter.businessdomain.exhibition.services;


import com.pintter.businessdomain.exhibition.dto.ExhibitionDto;
import com.pintter.businessdomain.exhibition.entities.Exhibition;
import com.pintter.businessdomain.exhibition.exceptions.BusinessRuleException;
import com.pintter.businessdomain.exhibition.transactions.BusinessTransactions;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.pintter.businessdomain.exhibition.mapper.ExhibitionMapper;
import com.pintter.businessdomain.exhibition.repository.ExhibitionRepository;

/**
 *
 * @author Pc
 */
@Service
@Slf4j
public class ExhibitionServiceImpl implements ExhibitionService {

    @Autowired
    private ExhibitionRepository followerRepository;
    @Autowired
    private ExhibitionMapper followerMapper;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private BusinessTransactions businessTransactions;
    // Add any required dependencies here (e.g., repositories, mappers)

    @Override
    public List<ExhibitionDto> getAllExhibitions() {
        List<ExhibitionDto> listExhibitionDto = followerMapper.toDtoList(followerRepository.findAll());

        return listExhibitionDto;
    }

    @Override
    public Optional<Exhibition> getExhibitionById(Long id) {
        Optional<Exhibition> opt = followerRepository.findById(id);
        return opt;
    }

    @Override
    public ExhibitionDto createExhibition(ExhibitionDto followerDto) {
        

        Exhibition follower = followerMapper.toEntity(followerDto);
        follower.setCreateAt(LocalDateTime.now());
        follower.setEndDate(LocalDateTime.now().plusDays(7));
        follower.setIsVirtual(true);
        follower.setStatus("Publica");
        follower.setViews(0L);
        follower.setVisibility(0L);
        follower = followerRepository.save(follower);
        return followerMapper.toDto(follower);
    }

    @Override
    public ExhibitionDto updateExhibition(Long id, ExhibitionDto followerDto) throws BusinessRuleException {
        // Implementation here
        Optional<Exhibition> opt = followerRepository.findById(id);
        log.info("resRole:::::::" + opt.get());
        Exhibition resExhibition = followerMapper.toOptional(opt);
        log.info("resRole:::::::" + resExhibition);

        if (resExhibition != null) {
            resExhibition.setId(id);
            

        } else {
            BusinessRuleException businessRuleException = new BusinessRuleException("0002", "Error validación. Transacion no localizada. ", HttpStatus.PRECONDITION_FAILED);
            throw businessRuleException;
        }
        log.info("resRole:::::::" + resExhibition);
        ExhibitionDto save = followerMapper.toDto(followerRepository.save(resExhibition));
        return save;
    }

    @Override
    public void deleteExhibition(Long id) {
        // Implementation here
    }

    @Override
    public  List<ExhibitionDto> findByUid(Long uid) {
        return followerMapper.toDtoList(followerRepository.findByUid(uid));
    }
}
