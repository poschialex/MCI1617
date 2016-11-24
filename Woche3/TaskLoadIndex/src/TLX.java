import java.util.LinkedList;


public class TLX {
	public MentalDemand md;
	public PhysicalDemand pd;
	public TemporalDemand td;
	public Frustration fr;
	public Effort ef;
	public Performance op;
	
	private LinkedList<Factor> factors;
	private Integer[] factors_combined = {0,1,0,2,0,3,0,4,0,5,1,2,1,3,1,4,1,5,2,3,2,4,2,5,3,4,3,5,4,5}; 
	
	private Double avg;
	private Double sum = 0.0;
	
	public TLX(){
		md = new MentalDemand();
		pd = new PhysicalDemand();
		td = new TemporalDemand();
		fr = new Frustration();
		ef = new Effort();
		op = new Performance();
		
		factors = new LinkedList<Factor>();
		factors.add(md);
		factors.add(pd);
		factors.add(td);
		factors.add(fr);
		factors.add(ef);
		factors.add(op);
	}
	
	public void calculateAVG(){
		Double weight = 0.0;
		
		for(Factor x : factors){
			sum += x.calculateProduct();
			weight += x.getWeight();
		}
		
		avg = sum / weight;
	}
	
	public Double getAVG(){
		return Math.floor(avg);
	}
	
	public LinkedList<Factor> getFactors(){
		return factors;
	}
	
	public Integer[] getFactorsCombined(){
		return factors_combined;
	}
	
	public Double getSum(){
		return Math.floor(sum);
	}
}
