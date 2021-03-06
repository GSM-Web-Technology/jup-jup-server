package com.gsm.jupjup.service.equipment;

import com.gsm.jupjup.dto.equipment.EquipmentResDto;
import com.gsm.jupjup.dto.equipment.EquipmentUploadDto;
import com.gsm.jupjup.model.Equipment;

import java.io.IOException;
import java.util.List;

public interface EquipmentService {
    //기자재 저장
    void save(EquipmentUploadDto equipmentUploadDto) throws Exception;

    //수량 업데이트
    void update(Long eq_idx, int count);

    //기자재 전부 업데이트
    void AllUpdate(Long eq_idx, EquipmentUploadDto equipmentUploadDto) throws IOException;

    //기자재 이름 삭제
    void deleteByEquipmentIdx(Long idx);

    //기자재 이름 검색
    EquipmentResDto findByName(String name) throws IOException;

    //기자재 모두 검색
    List<Equipment> findAll();

    //기자재 이름 검색
    Equipment equipmentFindBy(String name);

    //기자재 키워드 검색
    List<Equipment> findByKeyword(String keyword) throws Exception;

    //IDX 조회
    Equipment findByIdx(Long idx);
}
