package com.antonio.cloudparking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antonio.cloudparking.controller.dto.ParkingDto;
import com.antonio.cloudparking.controller.mapper.ParkingMapper;
import com.antonio.cloudparking.model.Parking;
import com.antonio.cloudparking.service.ParkingService;

@RestController
@RequestMapping("/parking")
public class ParkingController {
	
	private final ParkingService parkingService;
	private final ParkingMapper parkingMapper;

	
	public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
		this.parkingService=parkingService;
		this.parkingMapper=parkingMapper;
	}
	
	@GetMapping
	public List<ParkingDto> findAll(){
		List<Parking> parkingList = parkingService.findAll();
		List<ParkingDto> result = parkingMapper.toParkingDtoList(parkingList);
		return result;

	}

}
