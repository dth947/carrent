package com.sxt.bus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.bus.domain.Car;
import com.sxt.bus.mapper.CarMapper;
import com.sxt.bus.service.CarService;
import com.sxt.bus.vo.CarVo;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.utils.AppFileUtils;
import com.sxt.sys.utils.DataGridView;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
	private CarMapper carMapper;

	@Override
	public DataGridView queryAllCar(CarVo carVo) {
		Page<Object> page=PageHelper.startPage(carVo.getPage(), carVo.getLimit());
		List<Car> data = this.carMapper.queryAllCar(carVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public void addCar(CarVo carVo) {
		this.carMapper.insertSelective(carVo);
	}

	@Override
	public void deleteCar(String carnumber) {
		//先删除图片
		Car car=this.carMapper.selectByPrimaryKey(carnumber);
		//如果不是默认图片就删除
		if(!car.getCarimg().equals(SysConstast.DEFAULT_CAR_IMG)) {
			AppFileUtils.deleteFileUsePath(car.getCarimg());
		}
		//删除数据库的数据
		this.carMapper.deleteByPrimaryKey(carnumber);
		
	}

	@Override
	public void deleteBatchCar(String[] carnumbers) {
		for (String carnumber : carnumbers) {
			this.deleteCar(carnumber);
		}
	}

	@Override
	public void updateCar(CarVo carVo) {
		this.carMapper.updateByPrimaryKeySelective(carVo);
	}

	@Override
	public Car queryCarByCarNumber(String carnumber) {
		return this.carMapper.selectByPrimaryKey(carnumber);
	}


}
