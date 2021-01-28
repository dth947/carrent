package com.sxt.bus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.bus.domain.Customer;
import com.sxt.bus.mapper.CustomerMapper;
import com.sxt.bus.service.CustomerService;
import com.sxt.bus.vo.CustomerVo;
import com.sxt.sys.utils.DataGridView;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public DataGridView queryAllCustomer(CustomerVo customerVo) {
		Page<Object> page=PageHelper.startPage(customerVo.getPage(), customerVo.getLimit());
		List<Customer> data = this.customerMapper.queryAllCustomer(customerVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public void addCustomer(CustomerVo customerVo) {
		this.customerMapper.insertSelective(customerVo);
	}

	@Override
	public void deleteCustomer(String identity) {
		this.customerMapper.deleteByPrimaryKey(identity);
	}

	@Override
	public void deleteBatchCustomer(String[] identitys) {
		for (String identity : identitys) {
			this.deleteCustomer(identity);
		}
	}

	@Override
	public void updateCustomer(CustomerVo customerVo) {
		this.customerMapper.updateByPrimaryKeySelective(customerVo);
	}

	@Override
	public Customer queryCustomerByIdentity(String identity) {
		// TODO Auto-generated method stub
		return this.customerMapper.selectByPrimaryKey(identity);
	}

	@Override
	public List<Customer> queryAllCustomerForList(CustomerVo customerVo) {
		return this.customerMapper.queryAllCustomer(customerVo);
	}


}
