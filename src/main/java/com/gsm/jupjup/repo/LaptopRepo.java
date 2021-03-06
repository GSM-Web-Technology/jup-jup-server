package com.gsm.jupjup.repo;

import com.gsm.jupjup.model.Admin;
import com.gsm.jupjup.model.Laptop;
import com.gsm.jupjup.model.LaptopSpec;
import com.gsm.jupjup.model.response.ListResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaptopRepo extends JpaRepository<Laptop, String> {
    // 명확한 error, exception 처리.
    Optional<Laptop> findByLaptopSerialNumber(String laptopSerialNumber);

    List<Laptop> findByAdmin(Admin admin);

    @Query("select distinct a, b from Laptop a inner join LaptopSpec b on a.laptopSpec.specIdx = b.specIdx")
    List<Laptop> findAllBy();

    List<Laptop> findByLaptopSpec(LaptopSpec laptopSpec);
}
