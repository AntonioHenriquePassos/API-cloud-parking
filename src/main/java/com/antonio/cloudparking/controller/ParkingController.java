package com.antonio.cloudparking.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antonio.cloudparking.controller.dto.ParkingCreateDto;
import com.antonio.cloudparking.controller.dto.ParkingDto;
import com.antonio.cloudparking.controller.mapper.ParkingMapper;
import com.antonio.cloudparking.model.Parking;
import com.antonio.cloudparking.service.ParkingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {
	
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;

	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService=parkingService;
		this.parkingMapper=parkingMapper;
	}
	
	@GetMapping
	@ApiOperation ("Find all parkings")
	public ResponseEntity< List<ParkingDto>> findAll(){
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDto> result = parkingMapper.toParkingDtoList(parkingList);
		return ResponseEntity.ok(result);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity <ParkingDto> findById(@PathVariable String id){
		Parking parking = parkingService.findById(id);
		ParkingDto result = parkingMapper.toParkingDto(parking);
		return ResponseEntity.ok(result);

	}
	
	@PostMapping
	public ResponseEntity <ParkingDto> create(@RequestBody ParkingCreateDto dto){
		Parking parkingCreate = parkingMapper.toParkingCreate(dto);
		Parking parking = parkingService.create(parkingCreate);
		ParkingDto result = parkingMapper.toParkingDto(parking);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete (@PathVariable String id){
		parkingService.delete(id);
		return ResponseEntity.noContent().build();

	}

	@PutMapping("/{id}")
	public ResponseEntity <ParkingDto> update (@PathVariable String id, @RequestBody ParkingCreateDto dto){
		Parking parkingCreate = parkingMapper.toParkingCreate(dto);
		Parking parking = parkingService.update(id, parkingCreate);
		ParkingDto result = parkingMapper.toParkingDto(parking);
		return ResponseEntity.status(HttpStatus.OK).body(result);

	}
	
	@PostMapping ("/{id}")
	public ResponseEntity <ParkingDto> checkOut (@PathVariable String id){
		Parking parking = parkingService.checkOut(id);
		return ResponseEntity.ok(parkingMapper.toParkingDto(parking));
	}
	
}
