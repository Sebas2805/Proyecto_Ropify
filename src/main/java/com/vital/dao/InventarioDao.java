package com.vital.dao;

import com.vital.domain.Inventario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioDao extends JpaRepository<Inventario, Integer> 
{

    
}
