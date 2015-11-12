package com.mgl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.ib.controller.Bar;

public class Data implements Serializable {

	private List<Bar> bars = new ArrayList<Bar>();
	private double high;
	private double low;

	public List<Bar> getBars() {
		return bars;
	}

	public void setBars(List<Bar> bars) {
		this.bars = bars;
	}
	
	public void addBar(Bar bar) {
		bars.add(bar);
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getLast() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getMva50() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getAvgVolume() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getAvg5DVolume() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getVolume() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setBid(double price) {
		// TODO Auto-generated method stub
		
	}

	public void setAsk(double price) {
		// TODO Auto-generated method stub
		
	}

	public void setLast(double price) {
		// TODO Auto-generated method stub
		
	}

	public void setClose(double price) {
		// TODO Auto-generated method stub
		
	}

	public void setBidSize(int size) {
		// TODO Auto-generated method stub
		
	}

	public void setAskSize(int size) {
		// TODO Auto-generated method stub
		
	}

	public void setVolume(int size) {
		// TODO Auto-generated method stub
		
	}

	public void setLastTimestamp(long l) {
		// TODO Auto-generated method stub
		
	}

	public void setAvgVolume(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setAvg5DVolume(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setMva50(double d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bars == null) ? 0 : bars.hashCode());
		long temp;
		temp = Double.doubleToLongBits(high);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(low);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Data other = (Data) obj;
		if (bars == null) {
			if (other.bars != null)
				return false;
		} else if (!bars.equals(other.bars))
			return false;
		if (Double.doubleToLongBits(high) != Double.doubleToLongBits(other.high))
			return false;
		if (Double.doubleToLongBits(low) != Double.doubleToLongBits(other.low))
			return false;
		return true;
	}
	
	
	
	
	
}
