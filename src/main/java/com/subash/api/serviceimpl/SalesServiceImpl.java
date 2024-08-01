package com.subash.api.serviceimpl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.subash.api.model.Sales;
import com.subash.api.ownrepo.SalesOwnRepo;
import com.subash.api.service.SalesService;

@Service
public class SalesServiceImpl implements SalesService {

	public SalesServiceImpl(SalesOwnRepo ownrepo) {
		super();
		this.ownrepo = ownrepo;
	}

	SalesOwnRepo ownrepo;

	public String addSales(Sales sales) {
		if(sales != null) {
			ownrepo.save(sales);
			return "Add Success";
		} else {
			return "Add Failure";
		}
	}

	public Sales getSales(int id) {
		return ownrepo.findById(id);
	}

	public List<Sales> getAllSales() {
		return ownrepo.findAll();
	}

	public void updateSales(Sales sales) {
		ownrepo.update(sales);
	}

	public void deleteSales(int id) {
		ownrepo.deleteById(id);
	}

}
