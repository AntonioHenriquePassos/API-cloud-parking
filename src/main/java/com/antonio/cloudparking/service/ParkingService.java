package com.antonio.cloudparking.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.antonio.cloudparking.exception.ParkingNotFoundException;
import com.antonio.cloudparking.model.Parking;
import com.antonio.cloudparking.repository.ParkingRepository;

@Service
public class ParkingService {
	
	
	private final ParkingRepository parkingRepository;
	
	public ParkingService (ParkingRepository parkingRepository) {
		this.parkingRepository=parkingRepository;
	}

	private static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	@Transactional (readOnly = true, propagation = Propagation.SUPPORTS)
	public List<Parking> findAll(){
		return parkingRepository.findAll();

	}
	@Transactional (readOnly = true)
	public Parking findById(String id) {
		return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));	
		
	}
	@Transactional
	public Parking create(Parking parkingCreate) {
		String uuid = getUUID();
		parkingCreate.setId(uuid);
		parkingCreate.setEntryDate(LocalDateTime.now());
		parkingRepository.save(parkingCreate);
		return parkingCreate;
	}


	@Transactional
	public void delete(String id) {
		findById(id);
		parkingRepository.deleteById(id);	
	}
	@Transactional
	public Parking update(String id, Parking parkingCreate) {
		Parking parking = findById(id);
		parking.setColor(parkingCreate.getColor());
		parking.setBill(parkingCreate.getBill());
		parking.setLicense(parkingCreate.getLicense());
		parking.setState(parkingCreate.getState());
		parking.setModel(parkingCreate.getModel());
		parkingRepository.save(parking);
		return parking;

	}
	@Transactional
	public Parking checkOut (String id) {
		Parking parking = findById(id);
		parking.setBill(ParkingCheckOut.getBill(parking));
		parkingRepository.save(parking);
		return parking;
	}
	
}
