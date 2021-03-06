package com.gsm.jupjup.controller.release;

import com.gsm.jupjup.dto.laptop.LaptopResponseDto;
import com.gsm.jupjup.dto.laptop.LaptopSaveReqDto;
import com.gsm.jupjup.model.Laptop;
import com.gsm.jupjup.model.LaptopSpec;
import com.gsm.jupjup.model.response.CommonResult;
import com.gsm.jupjup.model.response.ListResult;
import com.gsm.jupjup.model.response.ResponseService;
import com.gsm.jupjup.model.response.SingleResult;
import com.gsm.jupjup.service.laptop.LaptopService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Api(tags = {"노트북 관리 컨트롤러"})
@RestController
@CrossOrigin(origins = "http://localhost:3000") //해당 origin 승인하기
@RequestMapping("/v2")
public class LaptopController {
    //Dependency Injection
    private final LaptopService laptopService;
    private final ResponseService responseService;

    //CREATE
    @ApiOperation(value = "노트북 등록", notes = "노트북을 등록한다.")
    @PostMapping("/admin/laptop")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    public CommonResult save(@ApiParam(value = "노트북 저장 DTO", required = true) @RequestBody @Valid LaptopSaveReqDto laptopSaveReqDto){
        laptopService.save(laptopSaveReqDto);
        return responseService.getSuccessResult();
    }

    //READ
    @ApiOperation(value = "노트북 조회", notes = "노트북을 조회한다.")
    @GetMapping("/admin/laptop/{laptopSerialNumber}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    public SingleResult<Laptop> findByLaptopSerialNumber(@ApiParam(value = "노트북 시리얼넘버", required = true) @PathVariable String laptopSerialNumber){
        return responseService.getSingleResult(laptopService.findByLaptopSerialNumber(laptopSerialNumber));
    }

    //DELETE
    @ApiOperation(value = "노트북 삭제", notes = "노트북을 삭제한다.")
    @DeleteMapping("/admin/laptop/{laptopSerialNumber}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    public CommonResult delete(@ApiParam(value = "노트북 시리얼넘버", required = true) @PathVariable String laptopSerialNumber){
        laptopService.delete(laptopSerialNumber);
        return responseService.getSuccessResult();
    }

    //GET
    @ApiOperation(value = "노트북 전체 조회", notes = "노트북을 전체 조회한다.")
    @GetMapping("/admin/laptop")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    public ListResult<Laptop> findAll(){
        return responseService.getListResult(laptopService.findAll());
    }

}