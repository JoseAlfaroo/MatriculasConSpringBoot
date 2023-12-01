package com.wazaaa.joq.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wazaaa.joq.interfaces.InterfaceRetiro;
import com.wazaaa.joq.interfaceservices.InterfaceRetiroService;
import com.wazaaa.joq.models.Retiro;

@Service
public class RetiroService implements InterfaceRetiroService {
	@Autowired
	private InterfaceRetiro data;
	
	@Override
	public List<Retiro> listarRetiro() {
		// TODO Auto-generated method stub
		return (List<Retiro>) data.findAll();
	}

	@Override
	public Optional<Retiro> listarRetiroPorId(int id) {
		// TODO Auto-generated method stub
		return data.findById(id);
	}

	@Override
	public int guardarRetiro(Retiro r) {
		int res = 0;
		
		Retiro retiro = data.save(r);
		
		if(!retiro.equals(null)) {
			res = 1;
		}
		
		// TODO Auto-generated method stub
		return res;
	}

	@Override
	public void eliminarRetiro(int id) {
		data.deleteById(id);
		
	}
}
