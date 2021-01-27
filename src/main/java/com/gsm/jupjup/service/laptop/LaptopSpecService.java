package com.gsm.jupjup.service.laptop;

import com.gsm.jupjup.dto.laptopSpec.LaptopSpecSaveRequestDto;
import com.gsm.jupjup.repo.LaptopSpecRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LaptopSpecService {

    //DI
    @Autowired
    private LaptopSpecRepo laptopSpecRepo;

    @Transactional
    public Long save(LaptopSpecSaveRequestDto laptopSpecSaveRequestDto){
        return laptopSpecRepo.save(laptopSpecSaveRequestDto.toEntity()).getSpecIdx();
    }
}