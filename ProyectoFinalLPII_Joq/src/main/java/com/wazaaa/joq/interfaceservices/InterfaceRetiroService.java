package com.wazaaa.joq.interfaceservices;

import java.util.List;
import java.util.Optional;

import com.wazaaa.joq.models.Retiro;

public interface InterfaceRetiroService {
	public List<Retiro> listarRetiro();
	public Optional<Retiro> listarRetiroPorId(int id);
	public int guardarRetiro(Retiro d);
	public void eliminarRetiro(int id);
}
